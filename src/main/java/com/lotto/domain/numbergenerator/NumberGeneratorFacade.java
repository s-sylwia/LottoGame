package com.lotto.domain.numbergenerator;

import com.lotto.domain.drawdategenerator.DrawDateFacade;
import com.lotto.domain.numbergenerator.dto.WinningTicketDto;
import com.lotto.domain.numberreceiver.NumberReceiverFacade;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
public class NumberGeneratorFacade {

    private final WinningTicketValidator winningNumberValidator;
    private final WinningTicketRepository winningTicketRepository;
    private final NumberReceiverFacade numberReceiverFacade;
    private final Clock clock;
//    private final RandomTicketGenerable;

    private final DrawDateFacade drawDateFacade;

    public NumberGeneratorFacade(WinningTicketValidator winningNumberValidator, WinningTicketRepository winningTicketRepository, NumberReceiverFacade numberReceiverFacade, Clock clock) {
        this.winningNumberValidator = winningNumberValidator;
        this.winningTicketRepository = winningTicketRepository;
        this.numberReceiverFacade = numberReceiverFacade;
        this.clock = clock;
        this.drawDateFacade = new DrawDateFacade(clock);
    }

    public WinningTicketDto generateWinningTicket() {
        LocalDateTime nextDrawDate = drawDateFacade.nexDrawDate();
        SixRandomNumbersDto sixRandomNumbersDto = randomGenerable.generateSixRandomNumbers(properties.count(), properties.lowerBand(), properties.upperBand());
        Set<Integer> winningNumbers = sixRandomNumbersDto.numbers();
        winningNumberValidator.validate(winningNumbers);
        WinningNumbers winningNumbersDocument = WinningNumbers.builder()
                .winningNumbers(winningNumbers)
                .date(nextDrawDate)
                .build();
        WinningNumbers savedNumbers = winningNumbersRepository.save(winningNumbersDocument);
        return WinningNumbersDto.builder()
                .winningNumbers(savedNumbers.winningNumbers())
                .date(savedNumbers.date())
                .build();
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
