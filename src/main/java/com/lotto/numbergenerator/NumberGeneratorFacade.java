package com.lotto.numbergenerator;

import com.lotto.numbergenerator.dto.WinningTicketDto;
import com.lotto.numberreceiver.NumberReceiverFacade;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class NumberGeneratorFacade {

        private final WinningTicketValidator winningNumberValidator;
        private final WinningTicketRepository winningTicketRepository;
        private final NumberReceiverFacade numberReceiverFacade;


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


