package com.lotto.drawdategenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DrawDateFacadeTest {
    private DrawDateFacade drawDateFacade;

    @Mock
    private DrawDateGenerator drawDateGenerator;

    @InjectMocks
    private DrawDateFacade drawDateFacade;

    @BeforeEach
    public void setUp() {
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 1, 15, 10, 0).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        drawDateFacade = new DrawDateFacade(drawDateGenerator, fixedClock);
    }


    @BeforeEach
    public void setUp() {
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 1, 15, 10, 0).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        drawDateFacade = new DrawDateFacade(new DrawDateGenerator(fixedClock), fixedClock);
    }

    @Test
    public void shouldSetLotteryDateToSaturday() {
        LocalDateTime nextDrawDate = drawDateFacade.nexDrawDate();
        assertEquals(DayOfWeek.SATURDAY, nextDrawDate.getDayOfWeek(), "Lottery date should be set to Saturday");
    }

    @Test
    public void shouldRetrieveNextDrawDate() {
        LocalDateTime nextDrawDate = drawDateFacade.nexDrawDate();
        assertNotNull(nextDrawDate, "Next draw date should not be null");
    }

    @Test
    public void shouldNotAllowInvalidDrawDate() {
    }

    @Test
    public void shouldReturnTrueIfDrawDateIsSet() {
        LocalDateTime drawDate = LocalDateTime.of(2024, 1, 22, 12, 0);
        drawDateFacade.setDrawDate(drawDate);

        assertTrue(drawDateFacade.isDrawDateSet(), "Draw date should be set");
    }

    @Test
    public void shouldReturnFalseIfDrawDateIsNotSet() {
        assertFalse(drawDateFacade.isDrawDateSet(), "Draw date should not be set initially");
    }

    @Test
    public void shouldHandleDrawDateConflictGracefully() {
        LocalDate existingDrawDate = LocalDate.of(2024, 1, 15);
        LocalDate conflictingDrawDate = LocalDate.of(2024, 1, 15);

        drawDateFacade.setDrawDate(existingDrawDate);

        boolean result = drawDateFacade.setDrawDate(conflictingDrawDate);

        assertFalse(result, "Setting conflicting draw date should return false");

        LocalDateTime currentDrawDate = drawDateFacade.nexDrawDate();
        assertEquals(existingDrawDate.atTime(12, 0), currentDrawDate, "Draw date should remain unchanged after conflict");
    }
}
