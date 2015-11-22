package com.company.domain;

/**
 * Match domain object.
 * 
 * @author vladimir.yushkevich
 *
 */
public class Match {

	private Integer homeTeamId;
	private Integer awayTeamId;
	private Integer homeTeamResult;
	private Integer awayTeamResult;

	public Match(Integer homeTeamId, Integer awayTeamId) {
		super();
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
	}

	public Integer getHomeTeamResult() {
		return homeTeamResult;
	}

	public void setHomeTeamResult(Integer homeTeamResult) {
		this.homeTeamResult = homeTeamResult;
	}

	public Integer getAwayTeamResult() {
		return awayTeamResult;
	}

	public void setAwayTeamResult(Integer awayTeamResult) {
		this.awayTeamResult = awayTeamResult;
	}

	public Integer getHomeTeamId() {
		return homeTeamId;
	}

	public Integer getAwayTeamId() {
		return awayTeamId;
	}

	@Override
	public String toString() {
		return "Match [homeTeamId=" + homeTeamId + ", awayTeamId=" + awayTeamId + ", homeTeamResult=" + homeTeamResult
				+ ", awayTeamResult=" + awayTeamResult + "]";
	}

}