package com.lotto.resultchecker;

public class PlayerResultNotFoundException extends RuntimeException {
    PlayerResultNotFoundException(String message) {
        super(message);
    }
}

