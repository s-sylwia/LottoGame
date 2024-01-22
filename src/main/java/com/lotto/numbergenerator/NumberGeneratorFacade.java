package com.lotto.numbergenerator;

import com.lotto.drawdategenerator.DrawDateFacade;
import com.lotto.numbergenerator.dto.WinningTicketDto;
import com.lotto.numberreceiver.NumberReceiverFacade;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;

@AllArgsConstructor
public class NumberGeneratorFacade {

    private final WinningTicketValidator winningNumberValidator;
    private final WinningTicketRepository winningTicketRepository;
    private final NumberReceiverFacade numberReceiverFacade;
    private final Clock clock;
    private final RandomTicketGenerable;

    private final DrawDateFacade drawDateFacade;

    public NumberGeneratorFacade(WinningTicketValidator winningNumberValidator, WinningTicketRepository winningTicketRepository, NumberReceiverFacade numberReceiverFacade, Clock clock) {
        this.winningNumberValidator = winningNumberValidator;
        this.winningTicketRepository = winningTicketRepository;
        this.numberReceiverFacade = numberReceiverFacade;
        this.clock = clock;
        this.drawDateFacade = new DrawDateFacade(clock);
    }

    public WinningTicketDto generateWinningTicket() {

        return null;
    }

    public WinningTicketDto retrieveWinningTicketByDate(LocalDateTime date) {
        WinningTicket winningTicketByDate = winningTicketRepository.findWinningTicketsByDate(date);
        return WinningTicketDto.builder()
                .lotteryDate(winningTicketByDate.lotteryDate())
                .winningNumbers(winningTicketByDate.winningNumbers())
                .build();
    }

    public boolean isWinningTicketGeneratedByDate() {

        return true;
    }
}
