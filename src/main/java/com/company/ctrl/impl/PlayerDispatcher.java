package com.company.ctrl.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import com.company.ctrl.Dispatcher;
import com.company.domain.ConsoleNode;
import com.company.domain.Player;
import com.company.domain.Team;
import com.company.enums.Position;
import com.company.model.Model;
import com.company.model.event.ActionEvent;
import com.company.model.impl.ConsoleMenuModel;
import com.company.service.PlayerService;
import com.company.service.TeamService;
import com.company.service.impl.PlayerServiceImpl;
import com.company.service.impl.TeamServiceImpl;
import com.company.view.ConsoleView;
import com.company.view.View;

/**
 * Responsible for handling of all player events.
 * 
 * @author uyushkevich
 *
 */
public class PlayerDispatcher implements Dispatcher {

	private PlayerService playerService;
	private TeamService teamService;

	private ConsoleMenuModel model;
	private ConsoleView view;

	public PlayerDispatcher(Model model, View view) {
		this.playerService = new PlayerServiceImpl();
		this.teamService = new TeamServiceImpl();
		this.model = (ConsoleMenuModel) model;
		this.view = (ConsoleView) view;
	}

	@Override
	public void execute(ActionEvent ae) {
		ConsoleNode consoleNode = (ConsoleNode) ae.getSource();
		switch (consoleNode.getControllerAction()) {
		case UPDATE:
			try {
				create(consoleNode.getValue(), view);
			} catch (RuntimeException re) {
				view.showData(re.getMessage());
			}
			model.notifyListeners(ae);
			break;
		case GET:
			try {
				show(view);
			} catch (RuntimeException re) {
				view.showData(re.getMessage());
			}
			break;
		default:
			throw new IllegalArgumentException("Now disparcher's actions have been found in PlayerController");
		}
	}

	private void create(String parameter, View view) {
		String data = "You selected ";
		Player player = new Player();

		Optional<Position> positionResult = Optional.ofNullable(Position.findByValue(parameter));
		if (positionResult.isPresent()) {
			Position position = positionResult.get();
			player.setPosition(position);
			data += "position to " + position;
		}

		Optional<Team> teamResult = teamService.getTeams().stream().filter(t -> t.getName().equals(parameter))
				.findAny();
		if (teamResult.isPresent()) {
			Team team = teamResult.get();
			player.setTeamId(team.getId());
			data += "team to " + team.getName();
		}

		playerService.createPlayer(player);

		view.showData(data);
	}

	private void show(View view) {
		Optional<Player> playerResult = Optional.ofNullable(playerService.getPlayer());
		if (playerResult.isPresent()) {
			Player player = playerResult.get();
			Optional<Team> team = Optional.ofNullable(teamService.getTeams().stream()
					.collect(Collectors.toMap(Team::getId, (p) -> p)).get(player.getTeamId()));
			if (team.isPresent())
				player.setTeamName(team.get().getName());
			view.showData(player.toString());
		}
	}

}
