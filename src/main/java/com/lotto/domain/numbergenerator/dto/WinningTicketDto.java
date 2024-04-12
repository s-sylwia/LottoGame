package com.lotto.domain.numbergenerator.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;


@Builder
public record WinningTicketDto (LocalDateTime lotteryDate, Set<Integer> winningNumbers){
    public Set<Integer> getWinningNumbers() {
        return null;
    }
}
