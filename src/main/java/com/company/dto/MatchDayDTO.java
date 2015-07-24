package com.company.dto;

/**
 * Data transfer object to show match day results.
 * 
 * @author uyushkevich
 *
 */
public class MatchDayDTO {

	private int matchDay;
	private String homeTeamName;
	private String awayTeamName;
	private int homeTeamResult;
	private int awayTeamResult;

	public MatchDayDTO(int matchDay, String homeTeamName, String awayTeamName, int homeTeamResult, int awayTeamResult) {
		this.matchDay = matchDay;
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
		this.homeTeamResult = homeTeamResult;
		this.awayTeamResult = awayTeamResult;
	}

	public int getMatchDay() {
		return matchDay;
	}

	public void setMatchDay(int matchDay) {
		this.matchDay = matchDay;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public int getHomeTeamResult() {
		return homeTeamResult;
	}

	public void setHomeTeamResult(int homeTeamResult) {
		this.homeTeamResult = homeTeamResult;
	}

	public int getAwayTeamResult() {
		return awayTeamResult;
	}

	public void setAwayTeamResult(int awayTeamResult) {
		this.awayTeamResult = awayTeamResult;
	}

	@Override
	public String toString() {
		return "MatchDayDTO [matchDay=" + matchDay + ", homeTeamName=" + homeTeamName + ", awayTeamName=" + awayTeamName
				+ ", homeTeamResult=" + homeTeamResult + ", awayTeamResult=" + awayTeamResult + "]";
	}

}