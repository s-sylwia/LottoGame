package com.lotto.domain.drawdategenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Data
@Setter
class DrawDateGenerator {

    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DATE = TemporalAdjusters.next(DayOfWeek.SATURDAY);
    private Clock clock;

    public DrawDateGenerator(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime createNextDrawDate() {
        LocalDateTime currrentTime = LocalDateTime.now(clock);

        if (isSaturdayAndBeforeNoon(currrentTime)) {
            return LocalDateTime.of(currrentTime.toLocalDate(), DRAW_TIME);
        }
        LocalDateTime with = currrentTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        return with.with(DRAW_TIME);
    }

    private boolean isSaturdayAndBeforeNoon(LocalDateTime currentDateTime) {
        return currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentDateTime.toLocalTime().isBefore(DRAW_TIME);
    }

}
