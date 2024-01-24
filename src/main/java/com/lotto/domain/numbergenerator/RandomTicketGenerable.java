package com.lotto.domain.numbergenerator;

public interface RandomTicketGenerable {

        SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBand, int upperBand);
    }

