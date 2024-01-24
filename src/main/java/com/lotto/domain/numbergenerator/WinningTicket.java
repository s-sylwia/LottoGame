package com.lotto.domain.numbergenerator;

import lombok.Builder;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
record WinningTicket(String winningNumbersId, LocalDateTime lotteryDate, Set<Integer> winningNumbers) {


}
