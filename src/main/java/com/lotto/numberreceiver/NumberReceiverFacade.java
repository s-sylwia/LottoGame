package com.lotto.numberreceiver;

import com.lotto.drawdategenerator.DrawDateFacade;
import com.lotto.numberreceiver.dto.LotteryResponseDto;
import com.lotto.numberreceiver.dto.TicketDto;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class NumberReceiverFacade {

    NumberValidator numberValidator = new NumberValidator();
    public static final String FAILED_VALIDATION_MESSAGE = "fail";
    public static final String SUCCEED_VALIDATION_MESSAGE = "success";
    private NumberReceiverRepository repository;
    private DrawDateFacade drawDateFacade;
    private final Clock clock;


    public LotteryResponseDto receiveNumbers(Set<Integer> numbersFromUser) {
        if (numberValidator.validateNumbers(numbersFromUser)) {
            return new LotteryResponseDto(null, FAILED_VALIDATION_MESSAGE, null, numbersFromUser);
        }
        String lotteryTicketID = UUID.randomUUID().toString();
        LocalDateTime drawDate = drawDateFacade.nexDrawDate();
        Ticket save = repository.save(new Ticket(lotteryTicketID, drawDate, numbersFromUser));

        return new LotteryResponseDto(save.lotteryTicketID(), SUCCEED_VALIDATION_MESSAGE, save.drawDate(), save.numbersFromUser());

    }

    public List<TicketDto> userNumbers(LocalDateTime date) {
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate
                .stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }
}



