package com.company.service;

import com.company.domain.Player;

/**
 * Contains player specific business logic. Probably can be used in future for
 * transaction handling.
 * 
 * @author vladimir.yushkevich
 *
 */
public interface PlayerService {

	Player getPlayer();

	void createPlayer(Player player);

	void updatePlayer(Player player);

	int getMatchDay();

}