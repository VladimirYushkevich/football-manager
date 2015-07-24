package com.company.dao;

import java.util.List;

import com.company.dao.xml.XMLDAO;
import com.company.domain.Team;

/**
 * Team DAO.
 * 
 * @author uyushkevich
 *
 */
public interface TeamDAO extends XMLDAO {

	List<Team> getTeams();

	void updateTeams(List<Team> teams);

}