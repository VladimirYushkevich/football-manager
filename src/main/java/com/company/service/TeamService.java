package com.company.service;

import java.util.List;

import com.company.domain.Team;

/**
 * Contains team specific business logic. Probably can be used in future for
 * transaction handling.
 * 
 * @author uyushkevich
 *
 */
public interface TeamService {

	public List<Team> getTeams();

	public void updateTeams(List<Team> teams);

}
