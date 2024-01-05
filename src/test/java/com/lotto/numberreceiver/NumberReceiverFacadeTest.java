package com.lotto.numberreceiver;

import com.lotto.numberreceiver.dto.LotteryResponseDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import static com.lotto.numberreceiver.NumberReceiverFacade.FAILED_VALIDATION_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class NumberReceiverFacadeTest {
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(new InMemoryDatabase());

    @Test
    public void shouldReturnFailWhenUserProvideLessThanSixNumbers() {
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6));
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null), lotteryResponseDto);
    }

        @Test
    public void shouldReturnSuccessWhenUserProvideSixNumbers() {
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6, 12));
//        assertEquals();
    }

    @Test
    public void shouldReturnFailWhenUserProvideMoreThanSixNumbers() {
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6, 23, 34));
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null), lotteryResponseDto);
    }

    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfRangeLargerThanNinetyNine() {
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(45, 34, 124, 125, 200, 100));
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null), lotteryResponseDto);
    }

    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfOfRangeLowerThenOne() {
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(0, 4, 5, 7, 6, 100));
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null), lotteryResponseDto);
    }

    @Test
    public void shouldReturnUniqueLotteryID() {
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6, 12));
        assertNotNull(lotteryResponseDto.lotteryID());
        assertFalse(lotteryResponseDto.lotteryID().isEmpty());
    }

    @Test
    public void shouldReturnLotteryDate_16_12_2023whenTodayIs_14_12_2023() {
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6, 12));
        assertNotNull(lotteryResponseDto.drawDate());
        assertEquals(LocalDateTime.of(2023, 12,16, 12,0,0),lotteryResponseDto.drawDate());
    }
}