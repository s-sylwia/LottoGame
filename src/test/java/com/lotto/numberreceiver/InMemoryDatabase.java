package com.lotto.numberreceiver;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryDatabase implements NumberReceiverRepository {

    Map<String, Ticket> dataBase = new ConcurrentHashMap<>();

    @Override
    public Ticket save(Ticket ticket) {
        String iD = UUID.randomUUID().toString();
        Ticket toSave = new Ticket(iD, ticket.drawDate(), ticket.numbersFromUser());
        dataBase.put(iD, toSave);
        return toSave;
    }
}
