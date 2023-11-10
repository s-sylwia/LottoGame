package com.lotto.numberreceiver;

import java.util.Set;
import java.util.UUID;

public class NumberReceiverFacade {

  NumberValidator numberValidator = new NumberValidator();
    public static final String FAILED_VALIDATION_MESSAGE = "fail";
    public static final String SUCCEED_VALIDATION_MESSAGE = "success";

    public LotteryResponseDto receiveNumbers(Set<Integer> numbersFromUser) {
        if (numberValidator.validateNumbers(numbersFromUser)) {
            return new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE);
        }
        String lotteryTicketID = UUID.randomUUID().toString();
        return new LotteryResponseDto(lotteryTicketID, SUCCEED_VALIDATION_MESSAGE);
    }



}


