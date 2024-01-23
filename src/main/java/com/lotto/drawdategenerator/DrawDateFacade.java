package com.lotto.drawdategenerator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Clock;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class DrawDateFacade {

    private final DrawDateGenerator drawDateGenerator;
    private final Clock clock;


    public DrawDateFacade(Clock clock) {
        this.drawDateGenerator = new DrawDateGenerator(clock);
        this.clock = clock;
    }

    public LocalDateTime nexDrawDate() {
        LocalDateTime localDateTime = drawDateGenerator.createNextDrawDate();
        return localDateTime;
    }
}
