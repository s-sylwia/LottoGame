package com.lotto.numbergenerator.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public record WinningTicketDto (LocalDateTime lotteryDate, Set<Integer> winningNumbers){
}
