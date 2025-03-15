package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Domain.TraceMsg;
import com.example.demo.Repository.TraceMsgRepository;
import java.util.Date;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class TraceMsgService {
    

  private final TraceMsgRepository traceMsgRepository;
  private final ReactiveMongoTemplate mongoTemplate;
  private static final Logger logger = LoggerFactory.getLogger(TraceMsgService.class);
  
   @Autowired
    public TraceMsgService(TraceMsgRepository traceMsgRepository, ReactiveMongoTemplate mongoTemplate) {
        this.traceMsgRepository = traceMsgRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<TraceMsg> createTraceMsg(TraceMsg traceMsg) {
        logger.info("Received request to create TraceMsg: {}", traceMsg);
        return traceMsgRepository.save(traceMsg)
                .doOnSuccess(saved -> logger.info("Successfully saved TraceMsg in MongoDB: {}", saved))
                .doOnError(error -> logger.error("Error saving TraceMsg", error));
    }

    public Flux<TraceMsg> searchTraceMsgsByDateRange(Date from, Date to) {
        logger.info("Received request to search TraceMsgs from {} to {}", from, to);
        return traceMsgRepository.findByTsBetween(from, to)
                .doOnComplete(() -> logger.info("Search completed"))
                .doOnError(error -> logger.error("Error searching TraceMsgs", error));
    }
    
    
    public Mono<TraceMsg> createTraceMsgWithTemplate(TraceMsg traceMsg) {
        logger.info("Received request to create TraceMsg using MongoTemplate: {}", traceMsg);
    
        return mongoTemplate.insert(traceMsg)
                .doOnSuccess(saved -> logger.info("Successfully saved TraceMsg with MongoTemplate: {}", saved))
                .doOnError(error -> logger.error("Error saving TraceMsg with MongoTemplate", error));
    }
    
     public Flux<TraceMsg> searchTraceMsgsByDateRangeTemplate(Date from, Date to) {
        logger.info("Searching TraceMsgs from {} to {} using MongoTemplate", from, to);
        
        Query query = new Query();
        query.addCriteria(Criteria.where("ts").gte(from).lte(to));

        return mongoTemplate.find(query, TraceMsg.class)
                .doOnComplete(() -> logger.info("Search completed"))
                .doOnError(error -> logger.error("Error searching TraceMsgs", error));
    }
}
