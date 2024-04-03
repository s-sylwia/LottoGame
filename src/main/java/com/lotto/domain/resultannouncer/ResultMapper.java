package com.lotto.domain.resultannouncer;

public class ResultMapper {
    static ResponseDto mapToDto(ResultResponse resultResponse) {
        return ResponseDto.builder()
                .drawDate(resultResponse.drawDate())
                .hitNumbers(resultResponse.hitNumbers())
                .numbers(resultResponse.numbers())
                .wonNumbers(resultResponse.wonNumbers())
                .isWinner(resultResponse.isWinner())
                .build();
    }
}
