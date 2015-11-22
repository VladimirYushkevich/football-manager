package com.company.service;

import java.util.List;

import com.company.domain.Team;

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
