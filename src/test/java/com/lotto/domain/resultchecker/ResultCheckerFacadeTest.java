package com.lotto.domain.resultchecker;

import com.lotto.domain.numberreceiver.dto.TicketDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Switch.CaseOperator.when;

class ResultCheckerFacadeTest {

    @Test
    public void shouldGenerateAllPlayersWithCorrectMessage() {


    }

    @Test
    public void shouldGenerateFailMessageWhenWinningNumbersEqualNull() {
//        //given
//        when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
//                .winningNumbers(null)
//                .build());
//        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().resultCheckerFacade(winningNumbersGeneratorFacade, numberReceiverFacade, playerRepository);
//        //when
//        PlayersDto playersDto = resultCheckerFacade.generateResults();
//        //then
//        String message = playersDto.message();
//        assertThat(message).isEqualTo("Winners failed to retrieve");

    }
        @Test
        public void shouldGenerateFailMessageWhenWinningNumbersIsEmpty () {
//            //given
//            when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
//                    .winningNumbers(Set.of())
//                    .build());
//            ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().resultCheckerFacade(winningNumbersGeneratorFacade, numberReceiverFacade, playerRepository);
//            //when
//            PlayersDto playersDto = resultCheckerFacade.generateResults();
//            //then
//            String message = playersDto.message();
//            assertThat(message).isEqualTo("Winners failed to retrieve");
        }

        @Test
        public void shouldGenerateResultWithCorrectCredentials () {

        }

        //then


    }
