package com.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

interface NumberReceiverRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllTicketsByDrawDate(LocalDateTime date);
}
