package com.lotto.drawdategenerator;

import lombok.Data;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

@Data
class DrawDateGenerator {

    DrawDateGenerator drawDateGenerator;

    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private  Clock clock;

    public DrawDateGenerator(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime nextDrawDate(){
        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime with = now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        return with;
    }
}
