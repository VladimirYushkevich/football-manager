package com.company.ctrl;

import com.company.ctrl.impl.MatchDispatcher;
import com.company.domain.ConsoleNode;
import com.company.domain.Player;
import com.company.enums.ControllerAction;
import com.company.enums.Position;
import com.company.model.event.ActionEvent;
import com.company.service.PlayerService;
import com.company.service.TeamService;
import com.company.service.impl.PlayerServiceImpl;
import com.company.service.impl.TeamServiceImpl;
import com.company.utils.PropertyHolder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.File;

import static org.mockito.Mockito.when;

public class MatchControllerTest {

	@Spy
	private PlayerService playerService = new PlayerServiceImpl();

	@Spy
	private TeamService teamService = new TeamServiceImpl();

	@Mock
	private ConsoleNode consoleNode;

	@Mock
	private ActionEvent actionEvent;

	@Spy
	private MatchDispatcher matchController = new MatchDispatcher();

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Before
	public void before() throws Exception {
		MockitoAnnotations.initMocks(this);

		matchController.setPlayerService(playerService);
		matchController.setTeamService(teamService);

		// reset player file
		File file = new File(PropertyHolder.getInstance("application").getProperties().get("db.xml.source.player"));
		file.delete();
		file.createNewFile();
	}

	@Test(expected = RuntimeException.class)
	public void shouldShowRuntimeExceptionWhenPlayerIsNotCreatedAndPlayMatchExecuted() {
		arrangeAndExecute(null);
	}

	@Test(expected = RuntimeException.class)
	public void shouldShowRuntimeExceptionWhenPlayerWithPositionIsCreatedAndPlayMatchExecuted() {
		Player player = new Player();
		player.setPosition(Position.FW);
		arrangeAndExecute(player);
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimeExceptionWhenPlayerWithTeamIsCreatedAndPlayMatchExecuted() {
		Player player = new Player();
		player.setTeamId(3);
		arrangeAndExecute(player);
	}

	@Test(expected = RuntimeException.class)
	public void shouldPlayMatchWhenPlayerWithTeamAndPositionIsCreatedAndPlayMatchExecuted() {
		Player player = new Player();
		player.setPosition(Position.FW);
		player.setTeamId(3);
		arrangeAndExecute(player);
	}

	public void arrangeAndExecute(Player player) {
		when(actionEvent.getSource()).thenReturn(consoleNode);
		when(consoleNode.getControllerAction()).thenReturn(ControllerAction.UPDATE);
		when(playerService.getPlayer()).thenReturn(player);

		matchController.execute(actionEvent);
	}

}
