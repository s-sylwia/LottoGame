package com.lotto.domain.numbergenerator;

import com.lotto.domain.drawdategenerator.DrawDateFacade;
import com.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;
import com.lotto.domain.numbergenerator.dto.WinningTicketDto;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NumberGeneratorFacadeTest {

    @Test
    public void shouldReturnWinningTicketByGivenDate() {
        // given
        LocalDateTime date = LocalDateTime.now();
        WinningTicketRepository winningTicketRepository = mock(WinningTicketRepository.class);
        DrawDateFacade drawDateFacade = mock(DrawDateFacade.class);
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(null, null, winningTicketRepository, drawDateFacade);
        WinningTicket expectedTicket = new WinningTicket("testId", date, Set.of(1, 2, 3, 4, 5, 6));
        when(winningTicketRepository.findWinningTicketsByDate(date)).thenReturn(expectedTicket);

        // when
        WinningTicketDto result = numberGeneratorFacade.retrieveWinningTicketByDate(date);
        // then
        assertNotNull(result);
        assertEquals(expectedTicket.winningNumbers(), result.winningNumbers());
        assertEquals(expectedTicket.lotteryDate(), result.lotteryDate());
    }
    @Test
    public void shouldReturnUniqueLotteryID() {
        // given
        RandomNumberGenerable randomNumberGenerable = mock(RandomNumberGenerable.class);
        WinningTicketValidator winningTicketValidator = mock(WinningTicketValidator.class);
        WinningTicketRepository winningTicketRepository = mock(WinningTicketRepository.class);
        DrawDateFacade drawDateFacade = mock(DrawDateFacade.class);
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(randomNumberGenerable, winningTicketValidator, winningTicketRepository, drawDateFacade);
        // when
        WinningTicketDto result = numberGeneratorFacade.generateWinningTicket(6, 1, 99);

        // then
        assertNotNull(result);
        assertNotNull(result.winningNumbers());
        assertNotNull(result.lotteryDate());
    }
    @Test
    public void shouldReturnSaveToDatabaseWhenTheWinningTicketHasSixNumbers() {
        // given
        RandomNumberGenerable randomNumberGenerable = mock(RandomNumberGenerable.class);
        when(randomNumberGenerable.generateSixRandomNumbers(6, 1, 99)).thenReturn(new SixRandomNumbersDto(Set.of(1, 2, 3, 4, 5, 6)));
        WinningTicketValidator winningTicketValidator = mock(WinningTicketValidator.class);
        WinningTicketRepository winningTicketRepository = mock(WinningTicketRepository.class);
        DrawDateFacade drawDateFacade = mock(DrawDateFacade.class);
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(randomNumberGenerable, winningTicketValidator, winningTicketRepository, drawDateFacade);

        // when
        WinningTicketDto result = numberGeneratorFacade.generateWinningTicket(6, 1, 99);

        // then
        assertNotNull(result);
        assertNotNull(result.winningNumbers());
        assertNotNull(result.lotteryDate());
        verify(winningTicketRepository, times(1)).save(any(WinningTicket.class));
    }
    @Test
    public void shouldReturnNumbersInRangeOneToNinetyNine() {
        // given
        RandomNumberGenerable randomNumberGenerable = mock(RandomNumberGenerable.class);
        Set<Integer> generatedNumbers = Set.of(1, 2, 3, 4, 5, 6);
        when(randomNumberGenerable.generateSixRandomNumbers(6, 1, 99)).thenReturn(new SixRandomNumbersDto(generatedNumbers));
        WinningTicketValidator winningTicketValidator = mock(WinningTicketValidator.class);
        WinningTicketRepository winningTicketRepository = mock(WinningTicketRepository.class);
        DrawDateFacade drawDateFacade = mock(DrawDateFacade.class);
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(randomNumberGenerable, winningTicketValidator, winningTicketRepository, drawDateFacade);

        // when
        WinningTicketDto result = numberGeneratorFacade.generateWinningTicket(6, 1, 99);

        // then
        assertNotNull(result);
        assertNotNull(result.winningNumbers());
        for (int num : result.winningNumbers()) {
            assertTrue(num >= 1 && num <= 99);
        }
    }
    @Test
    public void shouldReturnEmptyListWhenGeneratingZeroNumbers() {
        // given
        RandomNumberGenerable randomNumberGenerable = mock(RandomNumberGenerable.class);
        WinningTicketValidator winningTicketValidator = mock(WinningTicketValidator.class);
        WinningTicketRepository winningTicketRepository = mock(WinningTicketRepository.class);
        DrawDateFacade drawDateFacade = mock(DrawDateFacade.class);
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(randomNumberGenerable, winningTicketValidator, winningTicketRepository, drawDateFacade);

        // when
        WinningTicketDto result = numberGeneratorFacade.generateWinningTicket(0, 1, 99);

        // then
        assertNotNull(result);
        assertTrue(result.winningNumbers().isEmpty());
    }
}
