package com.lotto.domain.resultannouncer;


import java.util.Optional;

public interface ResponseRepository {

    Optional<ResultResponse> findById(String hash);

    ResultResponse save(ResultResponse buildResponse);
}
