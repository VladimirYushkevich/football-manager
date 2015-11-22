package com.company.service;

import com.company.domain.Team;

import java.util.List;

/**
 * Contains team specific business logic. Probably can be used in future for
 * transaction handling.
 * 
 * @author vladimir.yushkevich
 *
 */
public interface TeamService {

	List<Team> getTeams();

	void updateTeams(List<Team> teams);

}
