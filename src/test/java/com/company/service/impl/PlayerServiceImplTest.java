package com.company.service.impl;

import com.company.dao.PlayerDAO;
import com.company.domain.Player;
import com.company.enums.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

public class PlayerServiceImplTest {

	@Spy
	private PlayerServiceImpl playerServiceImpl = new PlayerServiceImpl();

	@Mock
	private PlayerDAO playerDAO;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		playerServiceImpl.setPlayerDAO(playerDAO);
	}

	@Test
	public void shouldCreatePlayerWhenPlayerNotExistsAndTeamSelected() {

		when(playerDAO.getPlayer()).thenReturn(null);

		Player player = new Player();
		player.setTeamId(3);
		playerServiceImpl.createPlayer(player);

		verify(playerDAO, times(1)).createOrUpdatePlayer(player);

	}

	@Test
	public void shouldCreatePlayerWhenPlayerNotExistsAndPositionSelected() {

		when(playerDAO.getPlayer()).thenReturn(null);

		Player player = new Player();
		player.setPosition(Position.FW);
		playerServiceImpl.createPlayer(player);

		verify(playerDAO, times(1)).createOrUpdatePlayer(player);

	}

	@Test
	public void shouldUpdatePlayerWhenPlayerWithTeamExistsAndPositionSelected() {

		Player existedPlayer = new Player();
		existedPlayer.setTeamId(3);
		when(playerDAO.getPlayer()).thenReturn(existedPlayer);

		Player player = new Player();
		player.setPosition(Position.FW);
		playerServiceImpl.createPlayer(player);

		verify(playerDAO, times(1)).createOrUpdatePlayer(existedPlayer);

	}

	@Test
	public void shouldUpdatePlayerWhenPlayerWithPositionExistsAndTeamSelected() {

		Player existedPlayer = new Player();
		existedPlayer.setPosition(Position.FW);
		when(playerDAO.getPlayer()).thenReturn(existedPlayer);

		Player player = new Player();
		player.setTeamId(3);
		playerServiceImpl.createPlayer(player);

		verify(playerDAO, times(1)).createOrUpdatePlayer(existedPlayer);

	}

	@Test
	public void shouldUpdatePositionWhenPlayerWithPositionExistsAndPosistionSelected() {

		Player existedPlayer = new Player();
		existedPlayer.setPosition(Position.FW);
		when(playerDAO.getPlayer()).thenReturn(existedPlayer);

		Player player = new Player();
		player.setPosition(Position.GK);
		playerServiceImpl.createPlayer(player);

	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimeExceptionWhenPlayerWithTeamExistsAndTeamSelected() {

		Player existedPlayer = new Player();
		existedPlayer.setTeamId(3);
		when(playerDAO.getPlayer()).thenReturn(existedPlayer);

		Player player = new Player();
		player.setTeamId(1);
		playerServiceImpl.createPlayer(player);

	}

}