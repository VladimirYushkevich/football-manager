package com.company.dao.xml;

import com.company.dao.PlayerDAO;
import com.company.domain.Player;
import com.company.enums.Position;
import com.company.utils.PropertyHolder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class XMLPlayerDAOIT {

	@Spy
	private PlayerDAO playerDAO = new XMLPlayerDAO();

	@Before
	@SuppressWarnings("ConstantConditions")
	public void setUp(){
		playerDAO.setFile(new File(getClass().getClassLoader()
				.getResource("xml/players.xml").getFile()));
	}

	@Test
	public void shouldReturnPlayer() {
		playerDAO.setFile(new File(PropertyHolder
				.getInstance("application-test").getProperties()
				.get("db.xml.source.player")));

		Player player = playerDAO.getPlayer();
		assertEquals(UUID.fromString("4b171b5b-857d-4444-b195-1a787e02eccd"),
				player.getId());
		assertEquals(Position.DF, player.getPosition());
		assertEquals(Integer.valueOf(1), player.getTeamId());
		assertEquals(0, player.getExperience());
		assertEquals(1, player.getMatchDay());
	}

	@Test
	@SuppressWarnings("ConstantConditions")
	public void shouldCreatePlayerWhenAllFieldArePopulated() {
		Player player = new Player();
		player.setPosition(Position.FW);
		player.setExperience(10);
		player.setTeamId(3);
		player.setMatchDay(2);

		playerDAO.createOrUpdatePlayer(player);

		Player createdPlayer = playerDAO.getPlayer();
		assertEquals(createdPlayer.getPosition(), player.getPosition());
		assertEquals(createdPlayer.getTeamId(), player.getTeamId());
		assertEquals(createdPlayer.getExperience(), player.getExperience());
		assertEquals(createdPlayer.getMatchDay(), player.getMatchDay());
	}

	@Test
	public void shouldCreatePlayerWhenTeamIdIsNotPopulated() {
		Player player = new Player();
		player.setPosition(Position.FW);
		player.setExperience(10);

		playerDAO.createOrUpdatePlayer(player);

		Player createdPlayer = playerDAO.getPlayer();
		assertEquals(createdPlayer.getPosition(), player.getPosition());
		assertNull(createdPlayer.getTeamId());
		assertEquals(createdPlayer.getExperience(), player.getExperience());
	}

	@Test
	public void shouldCreatePlayerWhenPositionIsNotPopulated() {
		Player player = new Player();
		player.setExperience(10);
		player.setTeamId(3);

		playerDAO.createOrUpdatePlayer(player);

		Player createdPlayer = playerDAO.getPlayer();
		assertNull(createdPlayer.getPosition());
		assertEquals(createdPlayer.getTeamId(), player.getTeamId());
		assertEquals(createdPlayer.getExperience(), player.getExperience());
	}

	@Test
	public void shouldCreatePlayerWheMatchDayIsNotPopulated() {
		Player player = new Player();
		player.setExperience(10);

		playerDAO.createOrUpdatePlayer(player);

		Player createdPlayer = playerDAO.getPlayer();
		assertNull(createdPlayer.getPosition());
		assertEquals(createdPlayer.getMatchDay(), 0);
		assertEquals(createdPlayer.getExperience(), player.getExperience());
	}

}