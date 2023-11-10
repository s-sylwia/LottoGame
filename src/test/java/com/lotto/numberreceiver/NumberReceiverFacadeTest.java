package com.lotto.numberreceiver;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.lotto.numberreceiver.NumberReceiverFacade.FAILED_VALIDATION_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class NumberReceiverFacadeTest {
    @Test
    public void shouldReturnFailWhenUserProvideLessThanSixNumbers() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6));
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE), lotteryResponseDto);
    }
        @Test
    public void shouldReturnSuccessWhenUserProvideSixNumbers() {
//
//        numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6, 12));
//

    }
    @Test
    public void shouldReturnFailWhenUserProvideMoreThanSixNumbers() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6, 23, 34));
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE), lotteryResponseDto);
    }
    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfRangeLargerThanNinetyNine() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(45, 34, 124, 125, 200, 100));
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE), lotteryResponseDto);
    }
    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfOfRangeLowerThenOne() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        LotteryResponseDto lotteryResponseDto = numberReceiverFacade.receiveNumbers(Set.of(0, 4, 5, 7, 6, 100));
        assertEquals(new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE), lotteryResponseDto);
    }
    @Test
    public void shouldReturnUniqueLotteryID() {
//        given
//        when
//        then
    }

    @Test
    public void shouldReturnLotteryDate_11_11_2023whenTodayIs_09_11_2023() {
//        given
//        when
//        then
    }
}