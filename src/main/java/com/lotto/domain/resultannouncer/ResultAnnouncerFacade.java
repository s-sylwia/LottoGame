package com.lotto.domain.resultannouncer;

import com.lotto.domain.resultchecker.ResultCheckerFacade;
import com.lotto.domain.resultchecker.ResultDto;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class ResultAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;
    private final ResponseRepository responseRepository;
    private final Clock clock;

    public ResultAnnouncerResponseDto checkResult(String hash) {
        Optional<ResultResponse> cachedResponse = responseRepository.findById(hash);
        if (cachedResponse.isPresent()) {
            return new ResultAnnouncerResponseDto(ResultMapper.mapToDto(cachedResponse.get()), MessageResponse.ALREADY_CHECKED.info);
        }

        ResultDto resultDto = resultCheckerFacade.findByTicketId(hash);
        if (resultDto == null) {
            return new ResultAnnouncerResponseDto(null, MessageResponse.HASH_DOES_NOT_EXIST_MESSAGE.info);
        }

        ResponseDto responseDto = buildResponseDto(resultDto);
        ResultResponse savedResponse = responseRepository.save(buildResponse(responseDto, LocalDateTime.now(clock)));

        if (!isAfterResultAnnouncementTime(resultDto)) {
            return new ResultAnnouncerResponseDto(responseDto, MessageResponse.WAIT_MESSAGE.info);
        }

        MessageResponse message = resultDto.isWinner() ? MessageResponse.WIN_MESSAGE : MessageResponse.LOSE_MESSAGE;
        return new ResultAnnouncerResponseDto(responseDto, message.info);
    }

    private ResultResponse buildResponse(ResponseDto responseDto, LocalDateTime now) {
        return ResultResponse.builder()
                .numbers(responseDto.numbers())
                .hitNumbers(responseDto.hitNumbers())
                .wonNumbers(responseDto.wonNumbers())
                .drawDate(responseDto.drawDate())
                .isWinner(responseDto.isWinner())
                .createdDate(now)
                .build();
    }

    private ResponseDto buildResponseDto(ResultDto resultDto) {
        return ResponseDto.builder()
                .numbers(resultDto.numbers())
                .hitNumbers(resultDto.hitNumbers())
                .drawDate(resultDto.drawDate())
                .isWinner(resultDto.isWinner())
                .wonNumbers(resultDto.wonNumbers())
                .build();
    }

    private boolean isAfterResultAnnouncementTime(ResultDto resultDto) {
        LocalDateTime announcementDateTime = resultDto.drawDate();
        return LocalDateTime.now(clock).isAfter(announcementDateTime);
    }
}
