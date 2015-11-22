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

	void createOrUpdatePlayer(Player player);

	Player getPlayer();

}
