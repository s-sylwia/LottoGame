package com.lotto.domain.resultannouncer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.lotto.domain.resultchecker.ResultCheckerFacade;
import pl.lotto.domain.resultchecker.dto.ResultDto;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResultAnnouncerFacadeTest {

    @Mock
    private ResultCheckerFacade resultCheckerFacade;
    @Mock
    private ResponseRepository responseRepository;
    @Mock
    private Clock clock;

    private ResultAnnouncerFacade resultAnnouncerFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resultAnnouncerFacade = new ResultAnnouncerFacade(resultCheckerFacade, responseRepository, clock);
    }


    @Test
    public void testCheckResultWithWinningResult() {
        // given
        String hash = "winningHash";
        ResultDto resultDto = ResultDto.builder()
                .isWinner(true)
                .build();
        when(resultCheckerFacade.findByTicketId(hash)).thenReturn(resultDto);

        // when
        ResultAnnouncerResponseDto responseDto = resultAnnouncerFacade.checkResult(hash);

        // then
        assertEquals(MessageResponse.WIN_MESSAGE.info, responseDto.message());
        assertEquals(true, responseDto.responseDto().isWinner());
    }

    @Test
    public void testCheckResultWithLosingResult() {
        // given
        String hash = "losingHash";
        ResultDto resultDto = ResultDto.builder()
                .isWinner(false)
                .build();
        when(resultCheckerFacade.findByTicketId(hash)).thenReturn(resultDto);

        // when
        ResultAnnouncerResponseDto responseDto = resultAnnouncerFacade.checkResult(hash);

        // then
        assertEquals(MessageResponse.LOSE_MESSAGE.info, responseDto.message());
        assertEquals(false, responseDto.responseDto().isWinner());
    }


}
