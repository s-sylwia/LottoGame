package com.lotto.numberreceiver;

import com.lotto.numberreceiver.dto.LotteryResponseDto;
import com.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class NumberReceiverFacade {

    NumberValidator numberValidator = new NumberValidator();
    public static final String FAILED_VALIDATION_MESSAGE = "fail";
    public static final String SUCCEED_VALIDATION_MESSAGE = "success";
    private NumberReceiverRepository repository;
    private DrawDateGenerator drawDateGenerator;

    public NumberReceiverFacade(NumberReceiverRepository repository) {
        this.repository = repository;
    }

    public LotteryResponseDto receiveNumbers(Set<Integer> numbersFromUser) {
        if (numberValidator.validateNumbers(numbersFromUser)) {
            return new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null);
        }
        String lotteryTicketID = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.now();
        Ticket save = repository.save(new Ticket(lotteryTicketID, drawDate, numbersFromUser));

        return new LotteryResponseDto(save.lotteryTicketID(), SUCCEED_VALIDATION_MESSAGE, save.drawDate());

    }

    public List<TicketDto> userNumbers(LocalDateTime date) {
        return List.of(
                TicketDto.builder()
                        .ticketID("1")
                        .drawDate(LocalDateTime.now())
                        .numbersFromUser(Set.of(1, 2, 3, 4, 5, 6))
                        .build());
    }
}



