package com.company.dao;

import com.company.dao.xml.XMLDAO;
import com.company.domain.Player;

/**
 * Player DAO.
 * 
 * @author vladimir.yushkevich
 *
 */
public interface PlayerDAO extends XMLDAO {

	void createPlayer(Player player);

	Player getPlayer();

}
