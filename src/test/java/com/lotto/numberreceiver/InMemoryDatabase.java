package com.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryDatabase implements NumberReceiverRepository {

    Map<String, Ticket> dataBase = new ConcurrentHashMap<>();

    @Override
    public Ticket save(Ticket ticket) {
        String iD = UUID.randomUUID().toString();
        Ticket toSave = new Ticket(iD, ticket.drawDate(), ticket.numbersFromUser());
        dataBase.put(iD, toSave);
        return toSave;
    }

    @Override
    public List<Ticket> findAllTicketsByDrawDate(LocalDateTime date) {
        return dataBase.values()
                .stream()
                .filter(ticket-> ticket.drawDate().equals(date))
                .toList();
    }
}
