package com.lotto.domain.resultchecker;

import com.lotto.domain.numbergenerator.NumberGeneratorFacade;
import com.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

class ResultCheckerFacadeTest {

    @Test
    public void shouldGenerateAllPlayersWithCorrectMessage() {
        //given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(
                mock(NumberGeneratorFacade.class),
                mock(NumberReceiverFacade.class),
                mock(PlayerRepository.class),
                mock(WinnersRetriever.class)
        );

        //when

        //then
    }

    @Test
    public void shouldGenerateFailMessageWhenWinningNumbersEqualNull() {
        //given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(
                mock(NumberGeneratorFacade.class),
                mock(NumberReceiverFacade.class),
                mock(PlayerRepository.class),
                mock(WinnersRetriever.class)
        );

        //when
//        String failMessage = resultCheckerFacade.generateFailMessage(null);

        //then
    }

    @Test
    public void shouldGenerateFailMessageWhenWinningNumbersIsEmpty() {
        //given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(
                mock(NumberGeneratorFacade.class),
                mock(NumberReceiverFacade.class),
                mock(PlayerRepository.class),
                mock(WinnersRetriever.class)
        );

        //when
//        String failMessage = resultCheckerFacade.generateFailMessage(Arrays.asList());

        //then
    }

    @Test
    public void shouldGenerateResultWithCorrectCredentials() {
        //given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(
                mock(NumberGeneratorFacade.class),
                mock(NumberReceiverFacade.class),
                mock(PlayerRepository.class),
                mock(WinnersRetriever.class)
        );

        //when
        ResultDto resultDto = resultCheckerFacade.findByTicketId("someTicketId");

        //then
    }
}