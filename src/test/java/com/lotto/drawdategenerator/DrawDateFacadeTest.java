package com.lotto.drawdategenerator;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DrawDateFacadeTest {

    @Test
    public void shouldSetLotteryDateToSaturday() {
    }

    @Test
    public void shouldRetrieveNextDrawDate() {
    }

    @Test
    public void shouldNotAllowInvalidDrawDate() {
    }

    @Test
    public void shouldReturnTrueIfDrawDateIsSet() {
    }

    @Test
    public void shouldReturnFalseIfDrawDateIsNotSet() {
    }

    @Test
    public void shouldHandleDrawDateConflictGracefully() {
        LocalDate existingDrawDate = LocalDate.of(2024, 1, 15);
        LocalDate conflictingDrawDate = LocalDate.of(2024, 1, 15);

        drawDateFacade.setDrawDate(existingDrawDate);

        boolean result = drawDateFacade.setDrawDate(conflictingDrawDate);

        assertFalse(result, "Setting conflicting draw date should return false");

        LocalDate currentDrawDate = drawDateFacade.getDrawDate();
        assertEquals(existingDrawDate, currentDrawDate, "Draw date should remain unchanged after conflict");


    }
}
