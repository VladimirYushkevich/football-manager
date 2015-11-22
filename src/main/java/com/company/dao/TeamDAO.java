package com.company.dao;

import com.company.dao.xml.XMLDAO;
import com.company.domain.Team;

import java.util.List;

/**
 * Team DAO.
 * 
 * @author vladimir.yushkevich
 *
 */
public interface TeamDAO extends XMLDAO {

	List<Team> getTeams();

	void updateTeams(List<Team> teams);

}