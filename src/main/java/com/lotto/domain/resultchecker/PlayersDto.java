package com.lotto.domain.resultchecker;

import com.lotto.domain.resultchecker.ResultDto;
import lombok.Builder;

import java.util.List;

@Builder
public record PlayersDto(
        List<ResultDto> results,
        String message
) {

}
