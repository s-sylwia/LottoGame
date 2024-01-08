package com.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record LotteryResponseDto (String lotteryID, String validationMessage, LocalDateTime drawDate, Set<Integer> numbersFromUser){




}
