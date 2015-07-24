package com.company.view;

import java.util.List;

import com.company.dto.MatchDayDTO;
import com.company.model.event.ActionEvent;

/**
 * Match day view.
 * 
 * @author uyushkevich
 *
 */
public class MatchDayView implements View {

	@Override
	public void update(ActionEvent ae) {
		@SuppressWarnings("unchecked")
		List<MatchDayDTO> matchDayDTOs = (List<MatchDayDTO>) ae.getSource();
		System.out.format("------------------ MatchDay %s ------------------\n", matchDayDTOs.get(0).getMatchDay());
		matchDayDTOs.stream().forEach(m -> {
			System.out.format("%1$-23s%2$-1s:%3$-5s%4$-20s\n", m.getHomeTeamName(), m.getHomeTeamResult(),
					m.getAwayTeamResult(), m.getAwayTeamName());
		});

	}

	@Override
	public void showData(String data) {
		System.out.println("***************************************************************");
		System.out.println(data);
		System.out.println("***************************************************************");

	}

}