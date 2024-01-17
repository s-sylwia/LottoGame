package com.lotto.drawdategenerator.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DrawDateDto(LocalDateTime drawDate, int lotteryNumber) {
}
