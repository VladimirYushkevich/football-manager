package com.company.service.impl;

import com.company.dao.TeamDAO;
import com.company.dao.xml.XMLTeamDAO;
import com.company.domain.Team;
import com.company.service.TeamService;

import java.util.List;

/**
 * Team service implementation.
 * 
 * @author vladimir.yushkevich
 *
 */
public class TeamServiceImpl implements TeamService {

	private TeamDAO teamDAO;

	public TeamServiceImpl() {
		this.teamDAO = new XMLTeamDAO();
	}

	@Override
	public List<Team> getTeams() {
		return teamDAO.getTeams();
	}

	@Override
	public void updateTeams(List<Team> teams) {
		teamDAO.updateTeams(teams);
	}

}