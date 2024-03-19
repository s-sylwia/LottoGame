package com.lotto.domain.drawdategenerator;

import lombok.Data;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Data
class DrawDateGenerator {

    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DATE = TemporalAdjusters.next(DayOfWeek.SATURDAY);
    private Clock clock;

    public DrawDateGenerator(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime createNextDrawDate() {
        LocalDateTime currentTime = LocalDateTime.now(clock);

        if (isSaturdayAndBeforeNoon(currentTime)) {
            return LocalDateTime.of(currentTime.toLocalDate(), DRAW_TIME);
        }
        LocalDateTime with = currentTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        return with.with(DRAW_TIME);
    }

    private boolean isSaturdayAndBeforeNoon(LocalDateTime currentDateTime) {
        return currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentDateTime.toLocalTime().isBefore(DRAW_TIME);
    }
}
