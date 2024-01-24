package com.lotto.domain.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record TicketDto(LocalDateTime drawDate, String ticketID, Set<Integer> numbersFromUser) {
}
