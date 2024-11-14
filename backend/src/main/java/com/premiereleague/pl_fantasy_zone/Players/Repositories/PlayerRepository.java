package com.premiereleague.pl_fantasy_zone.Players.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.premiereleague.pl_fantasy_zone.Players.Entities.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {

    Optional<Player> findByName(String name);

    void deletePlayer(String playerName);

}
