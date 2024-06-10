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
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(numberReceiverFacade.retrieveAllTicketsByNextDrawDate()).thenReturn(
                List.of(TicketDto.builder()
                                .hash("001")
                                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("002")
                                .numbers(Set.of(1, 2, 7, 8, 9, 10))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("003")
                                .numbers(Set.of(7, 8, 9, 10, 11, 12))
                                .drawDate(drawDate)
                                .build())
        );
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().resultCheckerFacade(winningNumbersGeneratorFacade, numberReceiverFacade, playerRepository);
        //when
        PlayersDto playersDto = resultCheckerFacade.generateResults();
        //then
        List<ResultDto> results = playersDto.results();
        ResultDto resultDto = ResultDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build();
        ResultDto resultDto1 = ResultDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        ResultDto resultDto2 = ResultDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        assertThat(results).contains(resultDto, resultDto1, resultDto2);
        String message = playersDto.message();
        assertThat(message).isEqualTo("Winners succeeded to retrieve");
    }

    @Test
    public void shouldGenerateFailMessageWhenWinningNumbersEqualNull() {
        //given
        when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(null)
                .build());
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().resultCheckerFacade(winningNumbersGeneratorFacade, numberReceiverFacade, playerRepository);
        //when
        PlayersDto playersDto = resultCheckerFacade.generateResults();
        //then
        String message = playersDto.message();
        assertThat(message).isEqualTo("Winners failed to retrieve");

    }
        @Test
        public void shouldGenerateFailMessageWhenWinningNumbersIsEmpty () {
            //given
            when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                    .winningNumbers(Set.of())
                    .build());
            ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().resultCheckerFacade(winningNumbersGeneratorFacade, numberReceiverFacade, playerRepository);
            //when
            PlayersDto playersDto = resultCheckerFacade.generateResults();
            //then
            String message = playersDto.message();
            assertThat(message).isEqualTo("Winners failed to retrieve");
        }

        @Test
        public void shouldGenerateResultWithCorrectCredentials () {
            //given
            LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
            Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
            when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                    .winningNumbers(winningNumbers)
                    .build());
            String hash = "001";
            when(numberReceiverFacade.retrieveAllTicketsByNextDrawDate()).thenReturn(
                    List.of(TicketDto.builder()
                                    .hash(hash)
                                    .numbers(Set.of(7, 8, 9, 10, 11, 12))
                                    .drawDate(drawDate)
                                    .build(),
                            TicketDto.builder()
                                    .hash("002")
                                    .numbers(Set.of(7, 8, 9, 10, 11, 13))
                                    .drawDate(drawDate)
                                    .build(),
                            TicketDto.builder()
                                    .hash("003")
                                    .numbers(Set.of(7, 8, 9, 10, 11, 14))
                                    .drawDate(drawDate)
                                    .build())
            );
            ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().resultCheckerFacade(winningNumbersGeneratorFacade, numberReceiverFacade, playerRepository);
            resultCheckerFacade.generateResults();
            //when

            ResultDto resultDto = resultCheckerFacade.findByTicketId(hash);
            //then
            ResultDto expectedResult = ResultDto.builder()
                    .hash(hash)
                    .numbers(Set.of(7, 8, 9, 10, 11, 12))
                    .hitNumbers(Set.of())
                    .drawDate(drawDate)
                    .isWinner(false)
                    .wonNumbers(winningNumbers)
                    .build();
            assertThat(resultDto).isEqualTo(expectedResult);
        }
    }
