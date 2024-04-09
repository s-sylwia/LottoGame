package com.lotto.domain.drawdategenerator;

import java.time.LocalDateTime;

public interface DrawDateRepository {
    void save(DrawDateLog drawDateLog);
    DrawDateLog findByDate(LocalDateTime currentTime);
}
