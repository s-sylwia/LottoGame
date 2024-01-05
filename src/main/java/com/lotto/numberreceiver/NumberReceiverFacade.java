package com.lotto.numberreceiver;

import com.lotto.numberreceiver.dto.LotteryResponseDto;
import java.time.LocalDateTime;
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

        return new LotteryResponseDto(lotteryTicketID, SUCCEED_VALIDATION_MESSAGE, LocalDateTime.now());

    }

}


