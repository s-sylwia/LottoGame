package com.lotto.domain.numbergenerator.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Builder
public record WinningTicketDto (LocalDateTime lotteryDate, Set<Integer> winningNumbers){
}
