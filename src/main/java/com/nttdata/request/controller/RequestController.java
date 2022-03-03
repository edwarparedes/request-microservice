package com.nttdata.request.controller;

import com.nttdata.request.entity.Request;
import com.nttdata.request.model.Operation;
import com.nttdata.request.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    IRequestService service;

    @GetMapping
    public Flux<Request> getRequests(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Request>> getRequestById(@PathVariable("id") String id){
        return service.getRequestById(id)
                .map(savedMessage -> ResponseEntity.ok(savedMessage))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

//    @GetMapping("/phone/{phone}")
//    public Mono<ResponseEntity<Request>> findByPhoneNumberId(@PathVariable("phone") String phone){
//        return service.getRequestByPhoneNumber(phone)
//                .map(savedMessage -> ResponseEntity.ok(savedMessage))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }

    @PostMapping("/makerequest")
    Mono<Request> makeRequest(@RequestBody Request request){
        return service.makeRequest(request);
    }

    /*@PutMapping
    Mono<Request> validateRequest(@PathVariable("requestId") String requestId, @PathVariable("status") String status){
        return service.validateRequest(requestId, status);
    }*/

    @PutMapping
    Mono<Request> validate(@RequestParam String requestId, @RequestParam String status){
        return service.validateRequest(requestId, status);
    }

    @DeleteMapping("/{id}")
    void dltRequest(@PathVariable("id") String id){
        service.delete(id);
    }

    @PostMapping("/transferYanki")
    Mono<Operation> transferYanki(@RequestBody Operation operation){
        return service.transferYanki(operation);
    }

}
