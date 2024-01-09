package com.lotto.numbergenerator;

import java.time.LocalDateTime;

interface WinningTicketRepository {

    WinningTicket save(WinningTicket winningTicket);

    WinningTicket findWinningTicketsByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);


}
