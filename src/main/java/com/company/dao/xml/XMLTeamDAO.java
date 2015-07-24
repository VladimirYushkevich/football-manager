package com.company.dao.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.company.dao.TeamDAO;
import com.company.domain.Team;
import com.company.utils.PropertyHolder;

/**
 * XML team DAO implementation.
 * 
 * @author uyushkevich
 *
 */
public class XMLTeamDAO extends XMLAbstractDAO implements TeamDAO {

	public XMLTeamDAO() {
		setFile(new File(PropertyHolder.getInstance("application").getProperties().get("db.xml.source.team")));
	}

	@Override
	public List<Team> getTeams() {
		List<Team> teams = new ArrayList<>();

		NodeList nodeList = readNodeListFromFile("team");
		IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item).forEach(i -> {
			Element element = (Element) i;
			
			Team team = new Team();
			team.setId(Integer.valueOf(element.getAttribute("id")));
			team.setName(element.getAttribute("name"));
			team.setRate(Integer.valueOf(element.getElementsByTagName("rate").item(0).getTextContent()));
			team.setDrawn(Integer.valueOf(element.getElementsByTagName("drawn").item(0).getTextContent()));
			team.setGoalsAgainst(Integer.valueOf(element.getElementsByTagName("goalsAgainst").item(0).getTextContent()));
			team.setGoalsFor(Integer.valueOf(element.getElementsByTagName("goalsFor").item(0).getTextContent()));
			team.setLost(Integer.valueOf(element.getElementsByTagName("lost").item(0).getTextContent()));
			team.setPoints(Integer.valueOf(element.getElementsByTagName("points").item(0).getTextContent()));
			team.setWon(Integer.valueOf(element.getElementsByTagName("won").item(0).getTextContent()));
			
			teams.add(team);
		});

		return teams;
	}

	@Override
	public Node createNode(Document doc, Object object) {
		Element element = doc.createElement("team");
		Team team = (Team) object;
		element.setAttribute("id", String.valueOf(team.getId()));
		element.setAttribute("name", String.valueOf(team.getName()));
		element.appendChild(getPlayerElements(doc, element, "rate", String.valueOf(team.getRate())));
		element.appendChild(getPlayerElements(doc, element, "won", String.valueOf(team.getWon())));
		element.appendChild(getPlayerElements(doc, element, "drawn", String.valueOf(team.getDrawn())));
		element.appendChild(getPlayerElements(doc, element, "lost", String.valueOf(team.getLost())));
		element.appendChild(getPlayerElements(doc, element, "goalsFor", String.valueOf(team.getGoalsFor())));
		element.appendChild(getPlayerElements(doc, element, "goalsAgainst", String.valueOf(team.getGoalsAgainst())));
		element.appendChild(getPlayerElements(doc, element, "points", String.valueOf(team.getPoints())));
		return element;
	}

	@Override
	public void updateTeams(List<Team> teams) {
		List<Object> objects = new ArrayList<>();
		teams.forEach(t -> {
			objects.add(t);
		});
		wrideNodeListToFile(objects, getFile());
	}

}
