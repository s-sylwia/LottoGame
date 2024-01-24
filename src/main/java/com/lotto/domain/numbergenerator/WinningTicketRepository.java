package com.lotto.domain.numbergenerator;

import java.time.LocalDateTime;

interface WinningTicketRepository {

    WinningTicket save(WinningTicket winningTicket);

    WinningTicket findWinningTicketsByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);


}
