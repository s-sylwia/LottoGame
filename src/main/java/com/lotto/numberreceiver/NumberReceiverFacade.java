package com.lotto.numberreceiver;

import java.util.Set;

public class NumberReceiverFacade {

    public String receiveNumbers(Set<Integer> numbersFromUser) {
        if ((numbersFromUser.size() != 6) || numbersFromUser.stream().anyMatch(number -> number < 1 || number > 99)) {
            return "fail";
        }
        return "success";
    }
}


