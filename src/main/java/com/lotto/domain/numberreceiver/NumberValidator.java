package com.lotto.domain.numberreceiver;

import java.util.Set;

class NumberValidator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 99;
    private static final int REQUIRED_SIZE = 6;

     boolean validateNumbers(Set<Integer> numbersFromUser) {
        return hasProvideSixNumbers(numbersFromUser) || areNumbersInTheRange(numbersFromUser);
    }

    private boolean hasProvideSixNumbers(Set<Integer> numbersFromUser) {
        return numbersFromUser.size() != REQUIRED_SIZE;
    }

     private boolean areNumbersInTheRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);
    }

}
