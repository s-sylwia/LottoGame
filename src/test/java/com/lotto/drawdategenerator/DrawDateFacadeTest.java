package com.lotto.drawdategenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DrawDateFacadeTest {

    @Mock
    private DrawDateGenerator drawDateGenerator;

    @InjectMocks
    private DrawDateFacade drawDateFacade;

    @BeforeEach
    public void setUp() {
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 1, 15, 10, 0).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        drawDateFacade = new DrawDateFacade(drawDateGenerator, fixedClock);
    }


    @Test
    public void shouldSetLotteryDateToSaturday() {
        when(drawDateGenerator.createNextDrawDate()).thenReturn(LocalDateTime.of(2024, 1, 20, 12, 0));

        LocalDateTime nextDrawDate = drawDateFacade.nexDrawDate();
        assertEquals(DayOfWeek.SATURDAY, nextDrawDate.getDayOfWeek(), "Lottery date should be set to Saturday");
    }

    @Test
    public void shouldRetrieveNextDrawDate() {
        when(drawDateGenerator.createNextDrawDate()).thenReturn(LocalDateTime.of(2024, 1, 20, 12, 0));

        LocalDateTime nextDrawDate = drawDateFacade.nexDrawDate();
        assertNotNull(nextDrawDate, "Next draw date should not be null");
    }

    @Test
    public void shouldNotAllowInvalidDrawDate() {
        LocalDateTime invalidDrawDate = LocalDateTime.of(2020, 1, 1, 12, 0);

        // Ustawienie zachowania mocka - zawsze zwracaj przykładową datę (sobotę)
        when(drawDateGenerator.createNextDrawDate()).thenReturn(LocalDateTime.of(2024, 1, 20, 12, 0));

        // Sprawdzenie, czy metoda setDrawDate nie pozwala na ustawienie nieprawidłowej daty
//        boolean result = drawDateFacade.setDrawDate(invalidDrawDate);
//
//        assertFalse(result, "Setting invalid draw date should return false");
//        assertFalse(drawDateFacade.isDrawDateSet(), "Draw date should not be set");
    }


    @Test
    public void shouldReturnTrueIfDrawDateIsSet() {
        LocalDateTime drawDate = LocalDateTime.of(2024, 1, 22, 12, 0);
        LocalDateTime nextSaturday= drawDateGenerator.createNextDrawDate();
        assertNotNull(nextSaturday);
    }

    @Test
    public void shouldReturnFalseIfDrawDateIsNotSet() {
//        assertFalse(drawDateFacade.isDrawDateSet(), "Draw date should not be set initially");
    }

    @Test
    public void shouldHandleDrawDateConflictGracefully() {
        LocalDate existingDrawDate = LocalDate.of(2024, 1, 15);
        LocalDate conflictingDrawDate = LocalDate.of(2024, 1, 15);

//        drawDateFacade.setDrawDate(existingDrawDate);
//
//        boolean result = drawDateFacade.setDrawDate(conflictingDrawDate);
//
//        assertFalse(result, "Setting conflicting draw date should return false");

        LocalDateTime currentDrawDate = drawDateFacade.nexDrawDate();
        assertEquals(existingDrawDate.atTime(12, 0), currentDrawDate, "Draw date should remain unchanged after conflict");
    }
}
