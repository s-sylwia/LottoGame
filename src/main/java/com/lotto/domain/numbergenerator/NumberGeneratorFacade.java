package com.lotto.domain.numbergenerator;

import com.lotto.domain.drawdategenerator.DrawDateFacade;
import com.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;
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
    private final RandomNumberGenerable randomGenerable;
    private final Clock clock;

    private final DrawDateFacade drawDateFacade;

    public NumberGeneratorFacade(WinningTicketValidator winningNumberValidator, WinningTicketRepository winningTicketRepository, NumberReceiverFacade numberReceiverFacade, Clock clock) {
        this.winningNumberValidator = winningNumberValidator;
        this.winningTicketRepository = winningTicketRepository;
        this.numberReceiverFacade = numberReceiverFacade;
        this.clock = clock;
        this.drawDateFacade = new DrawDateFacade(clock);
    }

    public WinningTicketDto generateWinningNumbers() {
        LocalDateTime nextDrawDate = drawDateFacade.nextDrawDate();
//        SixRandomNumbersDto sixRandomNumbersDto = randomGenerable.generateSixRandomNumbers(properties.count(), properties.lowerBand(), properties.upperBand());
        Set<Integer> winningNumbers = sixRandomNumbersDto.numbers();
        winningNumberValidator.validate(winningNumbers);
        WinningTicket winningNumbersDocument = WinningTicket.builder()
                .winningNumbersId(winningNumbers)
                .lotteryDate(nextDrawDate)
                .build();
        WinningTicket savedNumbers = winningTicketRepository.save(winningNumbersDocument);
        return WinningTicketDto.builder()
                .winningNumbers(savedNumbers.winningNumbers())
                .lotteryDate(savedNumbers.date())
                .build();
    public WinningTicketDto retrieveWinningTicketByDate(LocalDateTime date) {
        WinningTicket winningTicketByDate = winningTicketRepository.findWinningTicketsByDate(date)
        return WinningTicketDto.builder()
                .winningNumbers(winningTicketByDate.winningNumbers())
                .lotteryDate(winningTicketByDate.lotteryDate())
                .build();
    }

    public boolean isWinningTicketGeneratedByDate() {
        LocalDateTime nextDrawDate = drawDateFacade.nextDrawDate();
        return winningTicketRepository.existsByDate(nextDrawDate);
    }
}
