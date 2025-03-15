package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.example.demo.Domain.TraceMsg;
import java.util.Date;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TraceMsgRepository extends ReactiveMongoRepository<TraceMsg, String> {
    Flux<TraceMsg> findByTsBetween(Date from, Date to);
}