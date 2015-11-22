package com.company.domain;

/**
 * Team domain object.
 * 
 * @author vladimir.yushkevich
 *
 */
public class Team {

	private int id;
	private String name;
	private int rate;
	private int won;
	private int drawn;
	private int lost;
	private int goalsFor;
	private int goalsAgainst;
	private int points;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getDrawn() {
		return drawn;
	}

	public void setDrawn(int drawn) {
		this.drawn = drawn;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", rate=" + rate + ", won=" + won + ", drawn=" + drawn + ", lost="
				+ lost + ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst + ", points=" + points + "]";
	}

}