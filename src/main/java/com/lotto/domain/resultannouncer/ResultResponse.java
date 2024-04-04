package com.lotto.domain.resultannouncer;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record ResultResponse(
        Set<Integer> numbers,
        Set<Integer> wonNumbers,
        Set<Integer> hitNumbers,
        LocalDateTime drawDate,
        boolean isWinner,
        LocalDateTime createdDate) {
}
