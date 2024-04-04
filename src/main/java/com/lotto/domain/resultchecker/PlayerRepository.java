package com.lotto.domain.resultchecker;

import java.util.List;
import java.util.Optional;


public interface PlayerRepository {

    Optional<Player> findById(String hash);

    void saveAll(List<Player> players);
}
