#!/bin/bash

# create input topic with one partition to get full ordering
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic favourite-colour-input --create --partitions 1 --replication-factor 1


# create intermediary log compacted topic
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic user-keys-and-colours --create --partitions 1 --replication-factor 1 --config cleanup.policy=compact

# create output log compacted topic
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic favourite-colour-output --create --partitions 1 --replication-factor 1 --config cleanup.policy=compact

# launch a Kafka consumer
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
    --topic favourite-colour-output \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer

# launch the streams application

# then produce data to it
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic favourite-colour-input
#
stephane,blue
john,green
stephane,red
alice,red


# list all topics that we have in Kafka (so we can observe the internal topics)
bin/kafka-topics.sh --list --zookeeper localhost:2181
