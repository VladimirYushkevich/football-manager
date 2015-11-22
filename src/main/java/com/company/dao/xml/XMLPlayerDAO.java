package com.company.dao.xml;

import com.company.dao.PlayerDAO;
import com.company.domain.Player;
import com.company.enums.Position;
import com.company.utils.PropertyHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * XML player DAO implementation.
 * 
 * @author vladimir.yushkevich
 *
 */
public class XMLPlayerDAO extends XMLAbstractDAO implements PlayerDAO {

	public XMLPlayerDAO() {
		setFile(new File(PropertyHolder.getInstance("application").getProperties().get("db.xml.source.player")));
	}

	@Override
	public void createOrUpdatePlayer(Player player) {
		List<Object> players = new ArrayList<>();
		player.setId(UUID.randomUUID());
		players.add(player);
		wrideNodeListToFile(players, getFile());
	}

	@Override
	public Player getPlayer() {

		Optional<NodeList> result = Optional.ofNullable(readNodeListFromFile("player"));
		if (result.isPresent()) {
			NodeList nodeList = result.get();
			Player player = new Player();
			IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item).forEach(i -> {
				Element element = (Element) i;
				player.setId(UUID.fromString(element.getAttribute("id")));
				player.setPosition(
						Position.findByValue(element.getElementsByTagName("position").item(0).getTextContent()));
				player.setTeamId(element.getElementsByTagName("teamId").item(0).getTextContent().isEmpty() ? null
						: Integer.valueOf(element.getElementsByTagName("teamId").item(0).getTextContent()));
				player.setMatchDay(Integer.valueOf(element.getElementsByTagName("matchDay").item(0).getTextContent()));
				player.setExperience(
						Integer.valueOf(element.getElementsByTagName("experience").item(0).getTextContent()));
			});

			return player;
		}

		return null;
	}

	@Override
	public Node createNode(Document doc, Object object) {
		Element player = doc.createElement("player");
		player.setAttribute("id", String.valueOf(UUID.randomUUID()));
		player.appendChild(getPlayerElements(doc, player, "position", String.valueOf(((Player) object).getPosition())));
		player.appendChild(getPlayerElements(doc, player, "teamId",
				((Player) object).getTeamId() != null ? String.valueOf(((Player) object).getTeamId()) : ""));
		player.appendChild(getPlayerElements(doc, player, "matchDay", String.valueOf(((Player) object).getMatchDay())));
		player.appendChild(
				getPlayerElements(doc, player, "experience", String.valueOf(((Player) object).getExperience())));
		return player;
	}

}
