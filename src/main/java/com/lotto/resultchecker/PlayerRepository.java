package com.lotto.resultchecker;


import java.util.Optional;


public interface PlayerRepository {

    Optional<Player> findById(String hash);

}
