package com.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.Set;

record WinningTicket(String winningNumbersId, LocalDateTime lotteryDate, Set<Integer> winningNumbers) {


}
