package com.lotto.domain.numbergenerator;

import com.lotto.domain.numbergenerator.dto.WinningTicketDto;
import com.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NumberGeneratorFacadeTest {

    private final WinningTicketRepository winningNumbersRepository = new WinningNumbersRepositoryTestImpl();
    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);

    @Test
    public void shouldReturnWinningTicketByGivenDate() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        Set<Integer> generatedWinningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        String id = UUID.randomUUID().toString();
        WinningTicket winningTicket = WinningTicket.builder()
                .id(id)
                .date(drawDate)
                .winningNumbers(generatedWinningNumbers)
                .build();
        winningNumbersRepository.save(wi);
        RandomTicketGenerable generator = new WinningNumberGeneratorTestImpl();
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(drawDate);
        WinningTicketGeneratorFacade numbersGenerator = new NumberGeneratorConfiguration().createForTest(generator, winningNumbersRepository, numberReceiverFacade);
        //when
        WinningTicketDto winningNumbersDto = numbersGenerator.retrieveWinningNumberByDate(drawDate);
        //then
        WinningTicketDto expectedWinningNumbersDto = WinningTicketDto.builder()
                .date(drawDate)
                .winningNumbers(generatedWinningNumbers)
                .build();
        assertThat(expectedWinningNumbersDto).isEqualTo(winningNumbersDto);
    }



    @Test
    public void shouldReturnUniqueLotteryID() {

    }

    @Test
    public void shouldReturnSaveToDatabaseWhenTheWinningTicketHasSixNumbers() {
//        given
//        when
//        then
    }
    @Test
    public void shouldReturnNumbersInRangeOneToNinetyNine() {
//        given
//        when
//        then
    }
    @Test
    public void shouldReturnEmptyListWhenGeneratingZeroNumbers() {
//        given
//        when
//        then
    }






}