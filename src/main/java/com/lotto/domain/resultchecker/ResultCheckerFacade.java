package com.lotto.domain.resultchecker;

import com.lotto.domain.numbergenerator.NumberGeneratorFacade;
import com.lotto.domain.numbergenerator.dto.WinningTicketDto;
import com.lotto.domain.numberreceiver.NumberReceiverFacade;
import com.lotto.domain.numberreceiver.dto.TicketDto;
import com.lotto.domain.resultchecker.dto.PlayersDto;
import com.lotto.domain.resultchecker.dto.ResultDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

import static com.lotto.domain.resultchecker.ResultCheckerMapper.mapPlayersToResults;

@AllArgsConstructor
public class ResultCheckerFacade {

    NumberGeneratorFacade NumbersGeneratorFacade;
    NumberReceiverFacade numberReceiverFacade;
    PlayerRepository playerRepository;
    WinnersRetriever winnerGenerator;



    public ResultDto findByTicketId(String ticketId) {
        Player player = playerRepository.findById(ticketId)
                .orElseThrow(() -> new PlayerResultNotFoundException("Not found for id: " + ticketId));
        return ResultDto.builder()
                .hash(ticketId)
                .numbers(player.numbers())
                .hitNumbers(player.hitNumbers())
                .drawDate(player.drawDate())
                .wonNumbers(player.wonNumbers())
                .isWinner(player.isWinner())
                .build();
    }
}
