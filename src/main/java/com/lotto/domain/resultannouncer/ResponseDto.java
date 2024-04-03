package com.lotto.domain.resultannouncer;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ResponseDto(
        Set<Integer> numbers,
        Set<Integer> hitNumbers,
        Set<Integer> wonNumbers,
        LocalDateTime drawDate,
        boolean isWinner) {
}
