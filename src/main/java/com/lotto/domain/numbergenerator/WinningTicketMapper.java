package com.lotto.domain.numbergenerator;

import com.lotto.domain.numbergenerator.dto.WinningTicketDto;

class WinningTicketMapper {

    public static WinningTicketDto mapFromWinningTicket(WinningTicket winningTicket){
        return WinningTicketDto.builder()
                .lotteryDate(winningTicket.lotteryDate())
                .winningNumbers(winningTicket.winningNumbers())
                .build();
    }
}
