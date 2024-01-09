package com.lotto.numbergenerator.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
public record WinningTicketDto (LocalDateTime lotteryDate, Set<Integer> winningNumbers){
}
