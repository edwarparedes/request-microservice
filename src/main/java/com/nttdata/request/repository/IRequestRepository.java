package com.nttdata.request.repository;

import com.nttdata.request.entity.Request;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IRequestRepository extends ReactiveMongoRepository<Request, String> {
    //Mono<Request> findByPhoneNumber(String phone);
}
