package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.DateRangeRequest;
import com.example.demo.Domain.TraceMsg;
import com.example.demo.Service.MetricsService;
import com.example.demo.Service.TraceMsgService;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/trace-msgs")
public class TraceMsgController {

    private TraceMsgService _service;
    private final MetricsService _metricsService;
    
    @Autowired
    public TraceMsgController(TraceMsgService service, MetricsService metricsService){
        _service = service;
        _metricsService = metricsService;
    }

   @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TraceMsg> createTraceMsg(@RequestBody TraceMsg traceMsg) {
        _metricsService.incrementInsertRequest();
        return _service.createTraceMsgWithTemplate(traceMsg);
    }

    @PostMapping("/searchByDateRange")
    public Flux<TraceMsg> searchTraceMsgsByDateRange(@RequestBody DateRangeRequest dateRangeRequest) {
        return _service.searchTraceMsgsByDateRangeTemplate(dateRangeRequest.from, dateRangeRequest.to);
    }
}
