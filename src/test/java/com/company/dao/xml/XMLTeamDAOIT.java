package com.company.dao.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import com.company.domain.Team;
import com.company.utils.PropertyHolder;

public class XMLTeamDAOIT {

	@Spy
	private XMLTeamDAO teamDAO = new XMLTeamDAO();

	@Before
	public void resetTeams() {
		teamDAO.setFile(
				new File(PropertyHolder.getInstance("application").getProperties().get("db.xml.source.team.origin")));
		List<Team> originalTeams = teamDAO.getTeams();
		teamDAO.setFile(new File(PropertyHolder.getInstance("application").getProperties().get("db.xml.source.team")));
		teamDAO.updateTeams(originalTeams);
	}

	@Test
	public void shouldReturnAllTeams() {
		teamDAO.setFile(
				new File(PropertyHolder.getInstance("application-test").getProperties().get("db.xml.source.team")));

		assertEquals(6, teamDAO.getTeams().size());
	}

	@Test
	public void shouldUpdateTeams() {
		List<Team> teams = teamDAO.getTeams();
		Team team = teams.get(0);
		team.setDrawn(1);
		team.setGoalsAgainst(2);
		team.setGoalsFor(3);
		team.setLost(2);
		team.setPoints(4);
		team.setRate(40);
		team.setWon(5);

		teamDAO.setFile(new File(getClass().getClassLoader().getResource("xml/teams.xml").getFile()));
		teamDAO.updateTeams(teams);

	}

}