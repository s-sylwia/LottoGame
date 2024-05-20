package com.lotto.domain.drawdategenerator;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DrawDateRepositoryImpl implements DrawDateRepository {

    private List<DrawDateLog> drawDateLogs = new ArrayList<>();

    @Override
    public void save(DrawDateLog drawDateLog) {
        drawDateLogs.add(drawDateLog);
    }

    @Override
    public DrawDateLog findByDate(LocalDateTime currentTime) {

        return null;
    }
}

