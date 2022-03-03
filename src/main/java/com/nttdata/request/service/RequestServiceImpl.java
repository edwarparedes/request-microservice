package com.nttdata.request.service;

import com.nttdata.request.entity.Request;
import com.nttdata.request.model.Operation;
import com.nttdata.request.repository.IRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestServiceImpl implements IRequestService {

    @Autowired
    IRequestRepository repository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Flux<Request> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Request> getRequestById(String id) {
        return repository.findById(id);
    }

    /*@Override
    public Mono<Request> getRequestByPhoneNumber(String phone) {
        return repository.findByPhoneNumber(phone);
    }*/

    @Override
    public Mono<Request> makeRequest(Request request) {
        request.setStatus("Pending");
        return repository.save(request);
    }

    @Override
    public Mono<Request> validateRequest(String requestId, String status) {
        return repository.findById(requestId).flatMap(a -> {
            if(status.equalsIgnoreCase("Accept")){
                a.setStatus("Accept");
            }
            else
                a.setStatus("Reject");
            return repository.save(a);
        });
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id).subscribe();
    }

    @Override
    public Mono<Operation> transferYanki(Operation operation) {
        Mono<Operation> yankiMono = webClientBuilder.build()
                .post()
                .uri("http://localhost:8010/operation/transferYanki")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(operation))
                .retrieve()
                .bodyToMono(Operation.class);
        return yankiMono;
    }

}
