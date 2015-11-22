package com.company.service.impl;

import com.company.dao.PlayerDAO;
import com.company.dao.xml.XMLPlayerDAO;
import com.company.domain.Player;
import com.company.service.PlayerService;

import java.util.Optional;

/**
 * Player service implementation.
 * 
 * @author vladimir.yushkevich
 *
 */
public class PlayerServiceImpl implements PlayerService {

	private PlayerDAO playerDAO;

	public PlayerServiceImpl() {
		this.playerDAO = new XMLPlayerDAO();
	}

	@Override
	public Player getPlayer() {

		Player player = playerDAO.getPlayer();
		validate(player);

		return player;
	}

	@Override
	public int getMatchDay() {
		Optional<Player> result = Optional.ofNullable(playerDAO.getPlayer());
		if (result.isPresent())
			return result.get().getMatchDay();
		return 0;
	}

	private void validate(Player player) {
		Optional<Player> result = Optional.ofNullable(player);
		if (!result.isPresent())
			throw new RuntimeException("You should select position or team before.");
	}

	@Override
	public void createPlayer(Player player) {
		Optional<Player> result = Optional.ofNullable(playerDAO.getPlayer());

		if (result.isPresent()) {

			Player existedPlayer = result.get();

			if (existedPlayer.getTeamId() != null && player.getTeamId() != null)
				throw new RuntimeException(
						String.format("Player with [TeamId=%s] already exists.", existedPlayer.getTeamId()));

			// merge objects
			if (existedPlayer.getTeamId() != null) {
				existedPlayer.setPosition(player.getPosition());

				playerDAO.createOrUpdatePlayer(existedPlayer);

				return;
			}
		}

		playerDAO.createOrUpdatePlayer(player);
	}

	@Override
	public void updatePlayer(Player player) {
		playerDAO.createOrUpdatePlayer(player);
	}

	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

}