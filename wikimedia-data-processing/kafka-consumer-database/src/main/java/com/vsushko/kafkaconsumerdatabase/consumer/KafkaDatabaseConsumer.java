package com.vsushko.kafkaconsumerdatabase.consumer;

import com.vsushko.kafkaconsumerdatabase.entity.WikimediaData;
import com.vsushko.kafkaconsumerdatabase.repository.WikimediaDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author vsushko
 */
@AllArgsConstructor
@Slf4j
@Service
public class KafkaDatabaseConsumer {

    private WikimediaDataRepository wikimediaDataRepository;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String eventMessage) {
        log.info(String.format("Event message received -> %s", eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaDataRepository.save(wikimediaData);
    }
}
