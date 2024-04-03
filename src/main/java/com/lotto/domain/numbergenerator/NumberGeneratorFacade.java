package com.lotto.domain.numbergenerator;

import com.lotto.domain.drawdategenerator.DrawDateFacade;
import com.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;
import com.lotto.domain.numbergenerator.dto.WinningTicketDto;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
public class NumberGeneratorFacade {

    private final RandomNumberGenerable randomNumberGenerable;
    private final WinningTicketValidator winningNumberValidator;
    private final WinningTicketRepository winningTicketRepository;
    private final DrawDateFacade drawDateFacade;

    public WinningTicketDto generateWinningTicket(int count, int lowerBand, int upperBand) {
        LocalDateTime nextDrawDate = drawDateFacade.nextDrawDate();
        SixRandomNumbersDto sixRandomNumbersDto = randomNumberGenerable.generateSixRandomNumbers(count, lowerBand, upperBand);
        Set<Integer> winningNumbers = sixRandomNumbersDto.numbers();
        winningNumberValidator.validate(winningNumbers);
        WinningTicket winningNumbersDocument = WinningTicket.builder()
                .winningNumbersId("UniqueID")
                .lotteryDate(nextDrawDate)
                .winningNumbers(winningNumbers)
                .build();
        WinningTicket savedNumbers = winningTicketRepository.save(winningNumbersDocument);
        return WinningTicketDto.builder()
                .winningNumbers(savedNumbers.winningNumbers())
                .lotteryDate(savedNumbers.lotteryDate())
                .build();
    }

    public WinningTicketDto retrieveWinningTicketByDate(LocalDateTime date) {
        WinningTicket winningTicketByDate = winningTicketRepository.findWinningTicketsByDate(date);
        if (winningTicketByDate == null) {
            throw new WinningTicketNotFoundException("Winning ticket for this date does not exist.");
        }
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
