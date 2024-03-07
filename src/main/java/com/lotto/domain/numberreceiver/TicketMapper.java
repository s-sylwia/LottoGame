package com.lotto.domain.numberreceiver;

import com.lotto.domain.numberreceiver.dto.TicketDto;

public class TicketMapper {
    public static TicketDto mapFromTicket(Ticket ticket){
        return TicketDto.builder()
                .numbers(ticket.numbersFromUser())
                .ticketId(ticket.lotteryTicketID())
                .drawDate(ticket.drawDate())
                .build();
    }
}
