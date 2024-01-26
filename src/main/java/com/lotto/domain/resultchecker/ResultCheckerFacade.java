package com.lotto.domain.resultchecker;

import com.lotto.domain.numbergenerator.NumberGeneratorFacade;
import com.lotto.domain.numberreceiver.NumberReceiverFacade;
import com.lotto.domain.numberreceiver.dto.TicketDto;
import com.lotto.domain.resultchecker.dto.PlayersDto;
import com.lotto.domain.resultchecker.dto.ResultDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class ResultCheckerFacade {

    NumberGeneratorFacade NumbersGeneratorFacade;
    NumberReceiverFacade numberReceiverFacade;
    PlayerRepository playerRepository;
    WinnersRetriever winnerGenerator;

    public PlayersDto generateResults() {
        List<TicketDto> allTicketsByDate = numberReceiverFacade.receiveNumbers();
        List<Ticket> tickets = ResultCheckerMapper.mapFromTicketDto(allTicketsByDate);
        WinningNumbersDto winningNumbersDto = winningNumbersGeneratorFacade.generateWinningNumbers();
        Set<Integer> winningNumbers = winningNumbersDto.getWinningNumbers();
        if (winningNumbers == null || winningNumbers.isEmpty()) {
            return PlayersDto.builder()
                    .message("Winners failed to retrieve")
                    .build();
        }
        List<Player> players = winnerGenerator.retrieveWinners(tickets, winningNumbers);
        playerRepository.saveAll(players);
        return PlayersDto.builder()
                .results(mapPlayersToResults(players))
                .message("Winners succeeded to retrieve")
                .build();
    }

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
