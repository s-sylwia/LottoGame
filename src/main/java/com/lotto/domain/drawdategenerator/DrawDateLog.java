package com.lotto.domain.drawdategenerator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class DrawDateLog {
    LocalDateTime drawDate;
    Long id;
}
