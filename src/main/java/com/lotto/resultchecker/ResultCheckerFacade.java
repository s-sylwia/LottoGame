package com.lotto.resultchecker;

import com.lotto.numbergenerator.NumberGeneratorFacade;
import com.lotto.numberreceiver.NumberReceiverFacade;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResultCheckerFacade {

    NumberGeneratorFacade NumbersGeneratorFacade;
    NumberReceiverFacade numberReceiverFacade;
    PlayerRepository playerRepository;
    WinnersRetriever winnerGenerator;


}
