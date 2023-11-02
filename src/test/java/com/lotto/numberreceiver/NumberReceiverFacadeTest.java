package com.lotto.numberreceiver;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NumberReceiverFacadeTest {

    @Test
    public void shouldReturnFailWhenUserProvideLessThanSixNumbers (){
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        String result = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6));

        assertEquals("fail",result);


    }

    @Test
    public void shouldReturnSuccessWhenUserProvideSixNumbers (){
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        String result = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6,12));

        assertEquals("success",result);



    }
    @Test
    public void shouldReturnFailWhenUserProvideMoreThanSixNumbers (){
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        String result = numberReceiverFacade.receiveNumbers(Set.of(2, 4, 5, 7, 6,23,34));

        assertEquals("fail",result);


    }

    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfOfRangeLangerThan99 (){


    }

    @Test
    public void shouldReturnFailWhenUserProvideTheNumberOfOfRangeLowerThen1 (){


    }
}