#!/bin/bash

# create input topic with one partition to get full ordering
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic bank-transactions --create --partitions 1 --replication-factor 1


# create output log compacted topic
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic bank-balance-exactly-once --create --partitions 1 --replication-factor 1 --config cleanup.policy=compact


# launch a Kafka consumer
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
    --topic bank-balance-exactly-once \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
