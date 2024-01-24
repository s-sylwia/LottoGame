package com.lotto.domain.numbergenerator;

public class WinningTicketNotFoundException extends RuntimeException {

    WinningTicketNotFoundException(String message) {

        super(message);
    }
}
