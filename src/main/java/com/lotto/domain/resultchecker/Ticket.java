package com.lotto.domain.resultchecker;


import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
record Ticket(
        Set<Integer> numbers,
        LocalDateTime drawDate
) {
}
