package com.lotto.numberreceiver.dto;

import java.time.LocalDateTime;

public record LotteryResponseDto (String lotteryID, String validationMessage, LocalDateTime drawDate){




}
