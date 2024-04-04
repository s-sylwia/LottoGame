package com.lotto.domain.drawdategenerator;

import lombok.Data;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.UUID;

@Data
class DrawDateGenerator {

    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DATE = TemporalAdjusters.next(DayOfWeek.SATURDAY);
    private Clock clock;

    DrawDateRepository drawDateRepository;

    public DrawDateGenerator(Clock clock) {
        this.clock = clock;
    }

     LocalDateTime createNextDrawDate() {
        LocalDateTime currentTime = LocalDateTime.now(clock);

        if (isSaturdayAndBeforeNoon(currentTime)) {
            LocalDateTime of = LocalDateTime.of(currentTime.toLocalDate(), DRAW_TIME);
            drawDateRepository.save(new DrawDateLog(of, 1L));
            return of;
        }
        LocalDateTime with = currentTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalDateTime with1 = with.with(DRAW_TIME);
        drawDateRepository.save(new DrawDateLog(with1, 2L));
        return with1;
    }

     LocalDateTime findPreviousDrawDate(){
        LocalDateTime currentTime = LocalDateTime.now(clock);
        LocalDateTime byDate = drawDateRepository.findByDate(currentTime).drawDate;
        return byDate;
    }

    private boolean isSaturdayAndBeforeNoon(LocalDateTime currentDateTime) {
        return currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentDateTime.toLocalTime().isBefore(DRAW_TIME);
    }
}
