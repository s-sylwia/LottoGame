package com.lotto.domain.drawdategenerator;

import com.lotto.domain.numberreceiver.dto.LotteryResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DrawDateFacadeTest {

    Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 1, 15, 10, 0).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    DrawDateGenerator drawDateGenerator1 = new DrawDateGenerator(fixedClock);

    private DrawDateFacade drawDateFacade = new DrawDateFacade(drawDateGenerator1, fixedClock);


    @Test
    public void shouldReturnLotteryDate_9_03_2024whenTodayIs_7_03_2024() {
//        given
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 3, 7, 10, 0).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        DrawDateGenerator drawDateGenerator1 = new DrawDateGenerator(fixedClock);
        drawDateFacade = new DrawDateFacade(drawDateGenerator1, fixedClock);
//when
        LocalDateTime drawDate = drawDateFacade.nextDrawDate();
//        then
        assertEquals(LocalDateTime.of(2024, 3, 9, 12, 0, 0), drawDate);

    }
    @Test
    public void shouldReturnLotteryDate_16_03_2024whenTodayIs_9_03_2024_12_hour_00_minutes() {
//        given
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 3, 9, 12, 0).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        DrawDateGenerator drawDateGenerator1 = new DrawDateGenerator(fixedClock);
        drawDateFacade = new DrawDateFacade(drawDateGenerator1, fixedClock);
//when
        LocalDateTime drawDate = drawDateFacade.nextDrawDate();
//        then
        assertEquals(LocalDateTime.of(2024, 3, 16, 12, 0, 0), drawDate);

    }
    @Test
    public void shouldReturnLotteryDate_16_03_2024whenTodayIs_9_03_2024_12_hour_00_minutes_01_seconds() {
//        given
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 3, 9, 12, 0,1).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        DrawDateGenerator drawDateGenerator1 = new DrawDateGenerator(fixedClock);
        drawDateFacade = new DrawDateFacade(drawDateGenerator1, fixedClock);
//when
        LocalDateTime drawDate = drawDateFacade.nextDrawDate();
//        then
        assertEquals(LocalDateTime.of(2024, 3, 16, 12, 0, 0), drawDate);

    }

    @Test
    public void shouldReturnLotteryDate_9_03_2024whenTodayIs_9_03_2024_11_hour_59_minutes_59_seconds() {
//        given
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2024, 3, 9, 11, 59, 59).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        DrawDateGenerator drawDateGenerator1 = new DrawDateGenerator(fixedClock);
        drawDateFacade = new DrawDateFacade(drawDateGenerator1, fixedClock);
//when
        LocalDateTime drawDate = drawDateFacade.nextDrawDate();
//        then
        assertEquals(LocalDateTime.of(2024, 3, 9, 12, 0, 0), drawDate);

    }
    @Test
    public void shouldSetLotteryDateToSaturday() {

        LocalDateTime nextDrawDate = drawDateFacade.nextDrawDate();
        assertEquals(DayOfWeek.SATURDAY, nextDrawDate.getDayOfWeek(), "Lottery date should be set to Saturday");
    }

    @Test
    public void shouldRetrieveNextDrawDate() {

        LocalDateTime nextDrawDate = drawDateFacade.nextDrawDate();
        assertNotNull(nextDrawDate, "Next draw date should not be null");
    }




    @Test
    public void shouldReturnTrueIfDrawDateIsSet() {
        LocalDateTime drawDate = LocalDateTime.of(2024, 1, 22, 12, 0);
        LocalDateTime nextSaturday = drawDateGenerator1.createNextDrawDate();
        assertNotNull(nextSaturday);

    }




}
