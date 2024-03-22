package com.lotto.domain.numbergenerator;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
interface WinningTicketRepository {

    WinningTicket save(WinningTicket winningTicket);

    WinningTicket findWinningTicketsByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);


}
