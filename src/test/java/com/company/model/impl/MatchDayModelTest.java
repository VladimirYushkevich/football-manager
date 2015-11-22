package com.company.model.impl;

import com.company.domain.Match;
import com.company.domain.Team;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MatchDayModelTest {

	@Spy
	private MatchDayModel matchDayModel = new MatchDayModel();

	@Mock
	private Map<Integer, Team> mappedTeams;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenMatchDayIsNotValid() {
		arrangeMatchDayAndAssert(0, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenNumberOfTeamsIsNotValid() {
		arrangeMatchDayAndAssert(1, 0);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowIllegalArgumentExceptionWhenInputParameterAreInconsistent() {
		arrangeMatchDayAndAssert(7, 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenNumberOfTeamsIsNotPaired() {
		arrangeMatchDayAndAssert(1, 5);
	}

	@Test
	public void shouldGenerateCorrectPairsWhenInputParameterAreValid() {
		matchDayModel.setMappedTeams(mappedTeams);
		matchDayModel.setMatchDay(2);

		when(mappedTeams.size()).thenReturn(6);

		// first round
		List<Match> matches = matchDayModel.generateScheduleByRoundRobinScheme(
				2, 6);

		assertEquals(Integer.valueOf(1), matches.get(0).getHomeTeamId());
		assertEquals(Integer.valueOf(5), matches.get(0).getAwayTeamId());

		assertEquals(Integer.valueOf(4), matches.get(1).getHomeTeamId());
		assertEquals(Integer.valueOf(6), matches.get(1).getAwayTeamId());

		assertEquals(Integer.valueOf(2), matches.get(2).getHomeTeamId());
		assertEquals(Integer.valueOf(3), matches.get(2).getAwayTeamId());

		matches = matchDayModel.generateScheduleByRoundRobinScheme(3, 6);

		assertEquals(Integer.valueOf(1), matches.get(0).getHomeTeamId());
		assertEquals(Integer.valueOf(6), matches.get(0).getAwayTeamId());

		assertEquals(Integer.valueOf(5), matches.get(1).getHomeTeamId());
		assertEquals(Integer.valueOf(3), matches.get(1).getAwayTeamId());

		assertEquals(Integer.valueOf(4), matches.get(2).getHomeTeamId());
		assertEquals(Integer.valueOf(2), matches.get(2).getAwayTeamId());

		// second round
		matches = matchDayModel.generateScheduleByRoundRobinScheme(6, 6);

		assertEquals(Integer.valueOf(4), matches.get(0).getHomeTeamId());
		assertEquals(Integer.valueOf(1), matches.get(0).getAwayTeamId());

		assertEquals(Integer.valueOf(5), matches.get(1).getHomeTeamId());
		assertEquals(Integer.valueOf(2), matches.get(1).getAwayTeamId());

		assertEquals(Integer.valueOf(6), matches.get(2).getHomeTeamId());
		assertEquals(Integer.valueOf(3), matches.get(2).getAwayTeamId());

		matches = matchDayModel.generateScheduleByRoundRobinScheme(7, 6);

		assertEquals(Integer.valueOf(5), matches.get(0).getHomeTeamId());
		assertEquals(Integer.valueOf(1), matches.get(0).getAwayTeamId());

		assertEquals(Integer.valueOf(6), matches.get(1).getHomeTeamId());
		assertEquals(Integer.valueOf(4), matches.get(1).getAwayTeamId());

		assertEquals(Integer.valueOf(3), matches.get(2).getHomeTeamId());
		assertEquals(Integer.valueOf(2), matches.get(2).getAwayTeamId());
	}

	private void arrangeMatchDayAndAssert(int matchDay, int numberOfTeams) {
		matchDayModel.setMappedTeams(mappedTeams);
		matchDayModel.setMatchDay(matchDay);

		when(mappedTeams.size()).thenReturn(numberOfTeams);

		matchDayModel.getMatchDayDTOs();
	}

}