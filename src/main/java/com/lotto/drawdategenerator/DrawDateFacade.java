package com.lotto.drawdategenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Clock;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class DrawDateFacade {

    private final DrawDateGenerator drawDateGenerator;
    private Clock clock;


    public DrawDateFacade(Clock clock) {
        this.drawDateGenerator = new DrawDateGenerator(clock);
        this.clock = clock;
    }

    public LocalDateTime nexDrawDate() {
        LocalDateTime localDateTime = drawDateGenerator.nextDrawDate();
        return localDateTime;
    }
}
