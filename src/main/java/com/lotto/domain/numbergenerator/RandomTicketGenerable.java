package com.lotto.domain.numbergenerator;

import com.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;

public interface RandomTicketGenerable {

        SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBand, int upperBand);
    }

