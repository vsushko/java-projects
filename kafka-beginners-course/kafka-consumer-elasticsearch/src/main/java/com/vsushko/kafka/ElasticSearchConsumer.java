package com.vsushko.kafka;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpHost;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author vsushko
 */
public class ElasticSearchConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConsumer.class);
    private static final String BOOTSTRAP_SERVERS = "127.0.0.1:9092";
    private static JsonParser jsonParser = new JsonParser();

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = createClient();

        KafkaConsumer<String, String> consumer = createConsumer("twitter_tweets");
        // poll for new data
        while (true) {
            // new in Kafka 2.0.0
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            int recordCount = records.count();
            LOGGER.info("Received " + recordCount + " records");

            BulkRequest bulkRequest = new BulkRequest();

            for (ConsumerRecord<String, String> record : records) {
                String recordData = record.value();
                // twitter feed specific id
                String id = extractIdFromTweet(recordData);

                try {
                    // insert data into ElasticSearch
                    String jsonString = retrieveMessage(recordData);
                    if (jsonString.isEmpty()) {
                        continue;
                    }
                    IndexRequest indexRequest =
                            new IndexRequest("twitter", "tweets", id)
                                    .source(jsonString, XContentType.JSON);
                    LOGGER.warn("Received record with id: " + id);
                    // we add to our bulk request (takes no time)
                    bulkRequest.add(indexRequest);
                } catch (NullPointerException e) {
                    LOGGER.warn("skipping bad data: " + recordData);
                }
            }
            if (recordCount > 0) {
                BulkResponse bulkItemResponses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
                LOGGER.info("Committing offsets...");
                consumer.commitSync();
                LOGGER.info("Offsets have been committed");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // close the client
        // client.close();
    }

    private static RestHighLevelClient createClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    private static String extractIdFromTweet(String tweetJson) {
        String result = "";
        try {
            result = jsonParser.parse(tweetJson).getAsJsonObject().get("id_str").getAsString();
        } catch (JsonSyntaxException e) {
            LOGGER.warn("Unterminated object");
        }
        return result;
    }

    private static String retrieveMessage(String value) {
        try {
            jsonParser.parse(value);
        } catch (JsonSyntaxException ex) {
            LOGGER.error("Received not a valid message");
            return "";
        }
        return value;
    }

    private static KafkaConsumer<String, String> createConsumer(String topic) {
        String groupId = "kafka-demo-elasticsearch";

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // manual commit of offsets
        // disable auto commit of offsets
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
        // earliest - read from the beginning of the topic
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));

        return consumer;
    }
}


