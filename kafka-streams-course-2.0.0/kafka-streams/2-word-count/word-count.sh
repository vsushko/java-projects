#!/bin/bash

# create input topic with two partitions
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic word-count-input --create --partitions 1 --replication-factor 1

# create output topic
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic word-count-output --create --partitions 2 --replication-factor 1

# launch a Kafka consumer
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
    --topic word-count-output \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer

# launch the streams application

# then produce data to it
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic word-count-input

# package your application as a fat jar
mvn clean package

# run your fat jar
java -jar <your jar here>.jar

# list all topics that we have in Kafka (so we can observe the internal topics)
bin/kafka-topics.sh --list --zookeeper localhost:2181
