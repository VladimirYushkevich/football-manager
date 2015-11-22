package com.company.model.impl;

import com.company.domain.Match;
import com.company.domain.Player;
import com.company.domain.Team;
import com.company.dto.MatchDayDTO;
import com.company.enums.Position;
import com.company.model.Model;
import com.company.model.event.ActionEvent;
import com.company.model.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Particular match day model.
 * 
 * @author vladimir.yushkevich
 *
 */
public class MatchDayModel implements Model {

	private List<ActionListener> matchDayListeners;

	private Map<Integer, Team> mappedTeams;
	private int matchDay;
	private List<MatchDayDTO> matchDayDTOs;

	public MatchDayModel() {
		this.matchDayListeners = new ArrayList<>();
	}

	public void setMappedTeams(Map<Integer, Team> mappedTeams) {
		this.mappedTeams = mappedTeams;
	}

	public void setMatchDay(int matchDay) {
		this.matchDay = matchDay;
	}

	@Override
	public void addEventListener(ActionListener l) {
		matchDayListeners.add(l);
	}

	@Override
	public void removeEventListener(ActionListener l) {
		matchDayListeners.remove(l);
	}

	public List<MatchDayDTO> getMatchDayDTOs() {

		List<Match> matches = generateScheduleByRoundRobinScheme(matchDay,
				mappedTeams.size());

		matchDayDTOs = new ArrayList<>();
		matches.stream().forEach(
				m -> matchDayDTOs.add(generateScore(matchDay,
						mappedTeams.get(m.getHomeTeamId()),
						mappedTeams.get(m.getAwayTeamId()))));

		return matchDayDTOs;
	}

	public Player updatePlayerExperience(Player player) {
		String teamName = mappedTeams.get(player.getTeamId()).getName();

		MatchDayDTO matchDayDTO = matchDayDTOs
				.stream()
				.filter(m -> m.getHomeTeamName().equals(teamName)
						|| m.getAwayTeamName().equals(teamName)).findAny()
				.get();
		int playerGoals = (matchDayDTO.getHomeTeamName().equals(teamName)) ? matchDayDTO
				.getHomeTeamResult() : matchDayDTO.getAwayTeamResult();
		int opponentGoals = (!matchDayDTO.getHomeTeamName().equals(teamName)) ? matchDayDTO
				.getHomeTeamResult() : matchDayDTO.getAwayTeamResult();

		Position position = player.getPosition();
		int experience = player.getExperience();
		switch (position) {
		case GK:
			if (opponentGoals == 0)
				experience += 8;
			break;
		case DF:
			if (opponentGoals == 0)
				experience += 6;
			break;
		case MF:
			if (opponentGoals == 0)
				experience += 4;
			if (playerGoals > 3)
				experience += 3;
			if (playerGoals > 0)
				experience += 1;
			break;
		case FW:
			if (playerGoals > 0)
				experience += 3;
			if (playerGoals > 3)
				experience += 5;
			break;
		default:
			throw new IllegalArgumentException("Player has not any postion.");
		}

		player.setExperience(experience);
		player.setMatchDay(matchDay);

		return player;
	}

	private MatchDayDTO generateScore(int matchDay, Team homeTeam, Team awayTeam) {
		int homeTeamResult;
		int awayTeamResult;

		int delta = homeTeam.getRate() - awayTeam.getRate();
		if (delta >= 6) {
			homeTeamResult = new Random().nextInt(3) + 3;
			awayTeamResult = new Random().nextInt(2);
		} else if (delta < 6 && delta >= 2) {
			homeTeamResult = new Random().nextInt(3) + 2;
			awayTeamResult = new Random().nextInt(3);
		} else if (delta < 2 && delta >= -2) {
			homeTeamResult = new Random().nextInt(3) + 1;
			awayTeamResult = new Random().nextInt(2);
		} else if (delta < -2 && delta >= -6) {
			homeTeamResult = new Random().nextInt(3);
			awayTeamResult = new Random().nextInt(2) + 1;
		} else if (delta < -6) {
			homeTeamResult = new Random().nextInt(3);
			awayTeamResult = new Random().nextInt(2) + 2;
		} else {
			homeTeamResult = new Random().nextInt(5);
			awayTeamResult = new Random().nextInt(5);
		}

		if (homeTeamResult > awayTeamResult) {
			homeTeam.setPoints(homeTeam.getPoints() + 3);
			homeTeam.setWon(homeTeam.getWon() + 1);
			awayTeam.setLost(awayTeam.getLost() + 1);
		} else if (homeTeamResult < awayTeamResult) {
			awayTeam.setPoints(awayTeam.getPoints() + 3);
			awayTeam.setWon(awayTeam.getWon() + 1);
			homeTeam.setLost(homeTeam.getLost() + 1);
		} else {
			homeTeam.setPoints(homeTeam.getPoints() + 1);
			awayTeam.setPoints(awayTeam.getPoints() + 1);
			homeTeam.setDrawn(homeTeam.getDrawn() + 1);
			awayTeam.setDrawn(awayTeam.getDrawn() + 1);
		}

		homeTeam.setGoalsFor(homeTeam.getGoalsFor() + homeTeamResult);
		awayTeam.setGoalsFor(awayTeam.getGoalsFor() + awayTeamResult);
		homeTeam.setGoalsAgainst(homeTeam.getGoalsAgainst() + awayTeamResult);
		awayTeam.setGoalsAgainst(awayTeam.getGoalsAgainst() + homeTeamResult);

		return new MatchDayDTO(matchDay, homeTeam.getName(),
				awayTeam.getName(), homeTeamResult, awayTeamResult);
	}

	@Override
	public void notifyListeners(ActionEvent ae) {
		matchDayListeners.forEach(l -> l.actionPerformed(new ActionEvent(
				getMatchDayDTOs())));
	}

	List<Match> generateScheduleByRoundRobinScheme(int matchDay,
			int numberOfTeams) {
		validate(matchDay, numberOfTeams);

		Integer[] upperTeams = IntStream.range(1, numberOfTeams / 2 + 1)
				.boxed().toArray(Integer[]::new);
		Integer[] lowerTeams = IntStream
				.range(numberOfTeams / 2 + 1, numberOfTeams + 1).boxed()
				.toArray(Integer[]::new);

		boolean isSecondRound = false;
		if (matchDay > numberOfTeams - 1) {
			matchDay = matchDay - numberOfTeams + 1;
			isSecondRound = true;
		}

		generateRoundPairs(matchDay, upperTeams, lowerTeams);

		List<Match> matches = new ArrayList<>();
		if (isSecondRound)
			IntStream.range(0, numberOfTeams / 2).forEach(n -> {
				Match match = new Match(lowerTeams[n], upperTeams[n]);
				matches.add(match);
			});
		else
			IntStream.range(0, numberOfTeams / 2).forEach(n -> {
				Match match = new Match(upperTeams[n], lowerTeams[n]);
				matches.add(match);
			});

		return matches;
	}

	private void validate(int matchDay, int numberOfTeams) {
		if (matchDay <= 0 || numberOfTeams <= 0)
			throw new IllegalArgumentException(
					"Match day id and number of teams should be positive and non zero.");

		if (matchDay > 2 * (numberOfTeams - 1))
			throw new IllegalStateException(
					"Match day id and number of teams should be consistent.");

		if (numberOfTeams % 2 != 0 || numberOfTeams <= 4)
			throw new IllegalArgumentException(
					"Number of teams should be double and more than 4.");
	}

	private void generateRoundPairs(int matchDay, Integer[] upperTeams,
			Integer[] lowerTeams) {

		IntStream.range(1, matchDay).forEach(n -> {
			int upperStart = lowerTeams[0];
			int lowerEnd = upperTeams[upperTeams.length / 2 + 1];

			// shift right upper by 1 starting from 2
				System.arraycopy(upperTeams, 0, upperTeams, 1,
						upperTeams.length - 1);
				upperTeams[1] = upperStart;

				// shift left lower by 1
				System.arraycopy(lowerTeams, 1, lowerTeams, 0,
						lowerTeams.length - 1);
				lowerTeams[lowerTeams.length - 1] = lowerEnd;
			});
	}
}
