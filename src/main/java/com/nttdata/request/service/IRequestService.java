package com.nttdata.request.service;

import com.nttdata.request.entity.Request;
import com.nttdata.request.model.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRequestService {

    Flux<Request> getAll();

    Mono<Request> getRequestById(String id);

    //Mono<Request> getRequestByPhoneNumber(String phone);

    Mono<Request> makeRequest(Request request);

    Mono<Request> validateRequest(String requestId, String status);

    void delete(String id);

    Mono<Operation> transferYanki(Operation operation);


}
