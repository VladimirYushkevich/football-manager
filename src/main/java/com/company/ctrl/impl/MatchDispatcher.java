package com.company.ctrl.impl;

import com.company.ctrl.Dispatcher;
import com.company.domain.ConsoleNode;
import com.company.domain.Player;
import com.company.domain.Team;
import com.company.enums.Position;
import com.company.model.Model;
import com.company.model.event.ActionEvent;
import com.company.model.impl.MatchDayModel;
import com.company.service.PlayerService;
import com.company.service.TeamService;
import com.company.service.impl.PlayerServiceImpl;
import com.company.service.impl.TeamServiceImpl;
import com.company.view.MatchDayView;
import com.company.view.View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Responsible for handling of all match events.
 * 
 * @author vladimir.yushkevich
 *
 */
public class MatchDispatcher implements Dispatcher {

	private PlayerService playerService;
	private TeamService teamService;

	private MatchDayModel model;
	private MatchDayView view;

	public MatchDispatcher() {
	}

	public MatchDispatcher(Model model, View view) {
		this.playerService = new PlayerServiceImpl();
		this.teamService = new TeamServiceImpl();
		this.model = (MatchDayModel) model;
		this.view = (MatchDayView) view;
	}

	@Override
	public void execute(ActionEvent ae) {
		ConsoleNode consoleNode = (ConsoleNode) ae.getSource();
		switch (consoleNode.getControllerAction()) {
		case UPDATE:
			try {
				play(ae);
			} catch (IllegalStateException re) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Your result: ").append(playerService.getPlayer().getExperience()).append("\n")
						.append("Game over");
				view.showData(String.valueOf(stringBuilder));
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			} catch (RuntimeException re) {
				view.showData(re.getMessage());
			}
			break;
		case GET:
			showTable();
			break;
		default:
			throw new IllegalArgumentException("Now disparcher's actions have been found in MatchController.");
		}
	}

	private void showTable() {
		int matchDay = playerService.getMatchDay();

		Comparator<Team> teamByPoints = (t1, t2) -> Integer.compare(t2.getPoints(), t1.getPoints());

		StringBuilder stringBuilder = new StringBuilder();
		String format = "%1$-8s|%2$-23s|%3$-3s|%4$-3s|%5$-3s|%6$-3s|%7$-3s|%8$-3s|%9$-3s";
		stringBuilder.append(String.format(format, "Position", "Team", "P", "W", "D", "L", "F", "A", "P")).append("\n");
		AtomicInteger index = new AtomicInteger();
		teamService.getTeams().stream().sorted(teamByPoints).forEach(t -> stringBuilder
                .append(String.format(format, index.incrementAndGet(), t.getName(), matchDay, t.getWon(),
						t.getDrawn(), t.getLost(), t.getGoalsFor(), t.getGoalsAgainst(), t.getPoints()))
                .append("\n"));
		view.showData(String.valueOf(stringBuilder));
	}

	private void play(ActionEvent ae) {

		Optional<Player> resultPlayer = Optional.ofNullable(playerService.getPlayer());
		int matchDay = getNextMatchDay(resultPlayer);

		Map<Integer, Team> mappedTeams = teamService.getTeams().stream()
				.collect(Collectors.toMap(Team::getId, (p) -> p));

		model.setMappedTeams(mappedTeams);
		model.setMatchDay(matchDay);
		model.notifyListeners(ae);

		teamService.updateTeams(new ArrayList<>(mappedTeams.values()));
		playerService.updatePlayer(model.updatePlayerExperience(resultPlayer.get()));
	}

	private int getNextMatchDay(Optional<Player> resultPlayer) {
		Player player;

		if (resultPlayer.isPresent()) {
			player = resultPlayer.get();

			Optional<Position> resultPosition = Optional.ofNullable(player.getPosition());
			if (!resultPosition.isPresent())
				throw new RuntimeException("You should select a position before.");

			Optional<Integer> resultTeam = Optional.ofNullable(player.getTeamId());
			if (!resultTeam.isPresent())
				throw new RuntimeException("You should select a team before.");

		} else
			throw new RuntimeException("You should select a position and team before.");

		return player.getMatchDay() + 1;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public void setView(MatchDayView view) {
		this.view = view;
	}

}