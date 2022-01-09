#!/bin/bash

# create input topic for user purchases
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic user-purchases --create --partitions 3 --replication-factor 1

# create table of user information - log compacted for optimisation
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic user-table --create --partitions 2 --replication-factor 1 --config cleanup.policy=compact

# create out topic for user purchases enriched with user data (left join)
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic user-purchases-enriched-left-join --create --partitions 3 --replication-factor 1

# create out topic for user purchases enriched with user data (inner join)
bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic user-purchases-enriched-inner-join --create --partitions 3 --replication-factor 1


# start a consumer on the output topic (left join)
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
    --topic user-purchases-enriched-left-join \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer


# start a consumer on the output topic (inner join)
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
    --topic user-purchases-enriched-inner-join \
    --from-beginning \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property print.value=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer
