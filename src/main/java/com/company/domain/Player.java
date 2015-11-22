package com.company.domain;

import com.company.enums.Position;

import java.util.UUID;

/**
 * Player domain object.
 * 
 * @author vladimir.yushkevich
 *
 */
public class Player {

	private UUID id;
	private Position position;
	private Integer teamId;
	private int matchDay;
	private int experience;
	private String teamName;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public int getMatchDay() {
		return matchDay;
	}

	public void setMatchDay(int matchDay) {
		this.matchDay = matchDay;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "Position: "
				+ ((position == null) ? " Not specified" : position) + "\n"
				+ "Team: " + ((teamName == null) ? " Not specified" : teamName)
				+ "\n" + "Experience: " + experience
				+ "\n" + "Games played: " + matchDay;
	}

}