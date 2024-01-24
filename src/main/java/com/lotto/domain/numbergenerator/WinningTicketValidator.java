package com.lotto.domain.numbergenerator;

import java.util.Set;

public class WinningTicketValidator {

        private final int MINIMUM_VALUE = 0;
        private final int MAXIMUM_VALUE = 99;


        public Set<Integer> validate(Set<Integer> winningTicket) {
            if (outOfRange(winningTicket)) {
                throw new IllegalStateException("Out-of-bounds number!");
            }
            return winningTicket;
        }

        private boolean outOfRange(Set<Integer> winningTicket) {
            return winningTicket.stream()
                    .anyMatch(number -> number < MINIMUM_VALUE || number > MAXIMUM_VALUE);
        }
}
