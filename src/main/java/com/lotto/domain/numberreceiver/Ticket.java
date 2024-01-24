package com.lotto.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;

record Ticket(String lotteryTicketID, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
