package com.lotto.numbergenerator;

import com.lotto.numbergenerator.dto.WinningTicketDto;

class WinningTicketMapper {

    public static WinningTicketDto mapFromWinningTicket(WinningTicket winningTicket){
        return WinningTicketDto.builder()
                .lotteryDate(winningTicket.lotteryDate())
                .winningNumbers(winningTicket.winningNumbers())
                .build();
    }
}
