package com.premiereleague.pl_fantasy_zone.Players.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.premiereleague.pl_fantasy_zone.Players.Entities.Player;
import com.premiereleague.pl_fantasy_zone.Players.Repositories.PlayerRepository;

import jakarta.transaction.Transactional;

@Component
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayer() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayersByTeams(String teamName) {
        return playerRepository.findAll().stream()
            .filter(player -> teamName.equals(player.getTeam()))
            .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String searchText) {
        return playerRepository.findAll().stream()
            .filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPosition(String searchText) {
        return playerRepository.findAll().stream()
            .filter(player -> player.getPos().toLowerCase().contains(searchText.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Player> getPlayerByTeamAndPosition(String team, String position) {
        return playerRepository.findAll().stream()
            .filter(player -> team.equals(player.getTeam()) && position.equals(player.getPos()))
            .collect(Collectors.toList());
    }

    public Player addPlayer(Player player) {
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player player) {
        Optional<Player> existingPlayer = playerRepository.findByName(player.getName());
        
        if(existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setName(player.getName());
            playerToUpdate.setPos(player.getPos());
            playerToUpdate.setTeam(player.getTeam());
            playerToUpdate.setNation(player.getNation());
            playerRepository.save(playerToUpdate);
            return playerToUpdate;
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String playerName) {
        playerRepository.deletePlayer(playerName);
    }
    
}
