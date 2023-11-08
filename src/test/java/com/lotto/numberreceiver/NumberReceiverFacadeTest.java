package com.lotto.numberreceiver;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberReceiverFacadeTest {

    @Test
    public void shouldReturnFailWhenUserProvideLessThanSixNumbers() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        String result = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6));
        assertEquals("fail", result);
    }

    @Test
    public void shouldReturnSuccessWhenUserProvideSixNumbers() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        String result = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6, 12));
        assertEquals("success", result);
    }

    @Test
    public void shouldReturnFailWhenUserProvideMoreThanSixNumbers() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        String result = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6, 23, 34));
        assertEquals("fail", result);
    }

    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfOfRangeLargerThanNinetyNine() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        String result = numberReceiverFacade.receiveNumbers(Set.of(45, 34, 124, 125, 200, 100));
        assertEquals("fail", result);
    }

    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfOfRangeLowerThenOne() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        String result = numberReceiverFacade.receiveNumbers(Set.of(0, 4, 5, 7, 6, 100));
        assertEquals("fail", result);
    }



}