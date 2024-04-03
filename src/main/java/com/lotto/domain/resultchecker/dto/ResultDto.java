package com.lotto.domain.resultchecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ResultDto(Set<Integer> numbers,
                        Set<Integer> hitNumbers,
                        LocalDateTime drawDate,
                        boolean isWinner,
                        Set<Integer> wonNumbers
) {
}
