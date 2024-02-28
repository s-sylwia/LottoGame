package com.lotto.domain.drawdategenerator;

import com.lotto.domain.drawdategenerator.DrawDateFacade;
import com.lotto.domain.drawdategenerator.DrawDateGenerator;
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

        when(drawDateGenerator.createNextDrawDate()).thenReturn(LocalDateTime.of(2024, 1, 20, 12, 0));


    }


    @Test
    public void shouldReturnTrueIfDrawDateIsSet() {
        LocalDateTime drawDate = LocalDateTime.of(2024, 1, 22, 12, 0);
        LocalDateTime nextSaturday= drawDateGenerator.createNextDrawDate();
        assertNotNull(nextSaturday);
    }

    @Test
    public void shouldReturnFalseIfDrawDateIsNotSet() {
        LocalDateTime nextSaturday= null;
        when(drawDateGenerator.createNextDrawDate()).thenReturn(nextSaturday);
        LocalDateTime result = drawDateFacade.nexDrawDate();
        assertNull(result, "Draw date should be null");
    }

    @Test
    public void shouldHandleDrawDateConflictGracefully() {
        LocalDate existingDrawDate = LocalDate.of(2024, 1, 15);
        LocalDate conflictingDrawDate = LocalDate.of(2024, 1, 15);



        LocalDateTime currentDrawDate = drawDateFacade.nexDrawDate();
        assertEquals(existingDrawDate.atTime(12, 0), currentDrawDate, "Draw date should remain unchanged after conflict");
    }
}
