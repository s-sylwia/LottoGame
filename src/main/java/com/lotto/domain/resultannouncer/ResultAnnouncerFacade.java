package com.lotto.domain.resultannouncer;

import com.lotto.domain.resultchecker.ResultCheckerFacade;
import lombok.AllArgsConstructor;

import java.time.Clock;

@AllArgsConstructor
public class ResultAnnouncerFacade {


    private final ResultCheckerFacade resultCheckerFacade;
    private final ResponseRepository responseRepository;
    private final Clock clock;




}
