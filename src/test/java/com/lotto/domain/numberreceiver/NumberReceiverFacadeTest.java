package com.lotto.domain.numberreceiver;

import com.lotto.AdjustableClock;
import com.lotto.domain.drawdategenerator.DrawDateFacade;
import com.lotto.domain.numberreceiver.dto.LotteryResponseDto;
import com.lotto.domain.numberreceiver.dto.TicketDto;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static com.lotto.domain.numberreceiver.NumberReceiverFacade.FAILED_VALIDATION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NumberReceiverFacadeTest {

    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2023,12,14,12,0,0)
            .toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    DrawDateFacade drawDateFacade = mock(DrawDateFacade.class);
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumberValidator(),
            new InMemoryDatabase(),
            drawDateFacade,
            clock
    );

    @Test
    public void shouldReturnFailWhenUserProvideLessThanSixNumbers() {
        Set<Integer> numbersFromUser = Set.of(2, 4, 5, 7, 6);
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(numbersFromUser);
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null, numbersFromUser), lotteryResponseDto);
    }

    @Test
    public void shouldReturnSuccessWhenUserProvideSixNumbers() {
        Set<Integer> numbersFromUser = Set.of(2, 4, 5, 7, 6, 12);
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(numbersFromUser);
        assertEquals(lotteryResponseDto.validationMessage(), "success");
    }

    @Test
    public void shouldReturnFailWhenUserProvideMoreThanSixNumbers() {
        Set<Integer> numbersFromUser = Set.of(2, 4, 5, 7, 6, 23, 34);
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(numbersFromUser);
        assertEquals(new LotteryResponseDto(null,
                FAILED_VALIDATION_MESSAGE,
                null,
                numbersFromUser), lotteryResponseDto);
    }

    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfRangeLargerThanNinetyNine() {
        Set<Integer> numbersFromUser = Set.of(45, 34, 124, 125, 200, 100);
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(numbersFromUser);
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null, numbersFromUser), lotteryResponseDto);
    }

    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfOfRangeLowerThenOne() {
        Set<Integer> numbersFromUser = Set.of(0, 4, 5, 7, 6, 100);
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(numbersFromUser);
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null, numbersFromUser), lotteryResponseDto);
    }

    @Test
    public void shouldReturnUniqueLotteryID() {
        Set<Integer> numbersFromUser = Set.of(2, 4, 5, 7, 6, 12);
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(numbersFromUser);
        assertNotNull(lotteryResponseDto.lotteryID());
        assertFalse(lotteryResponseDto.lotteryID().isEmpty());
    }



    @Test
    public void shouldReturnSaveToDatabaseWhenUserProvideSixNumbers() {
//        given
        LocalDateTime drawDate = LocalDateTime.of(2023,12,14,13,0,0);
        when(drawDateFacade.nextDrawDate()).thenReturn(drawDate);
        Set<Integer> numbersFromUser = Set.of(2, 4, 5, 7, 6, 12);
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(numbersFromUser);
//        when
        clock.advanceInTimeBy(Duration.ofDays(8));
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(drawDate);
//        then
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .drawDate(drawDate)
                        .ticketId(lotteryResponseDto.lotteryID())
                        .numbers(lotteryResponseDto.numbersFromUser())
                        .build());
    }
}
