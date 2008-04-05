package edu.apollogrp.jug.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.apollogrp.jug.model.GameState;

public class TestGameState extends BaseRuleTestCase {

	@Override
	protected List<String> getPackageNames() {
		List<String> names = new ArrayList<String>();
		names.add("/rules/game-state.drl");
		return names;
	}

	public void testItNow() {
		GameState state = new GameState();

		Collection list = new ArrayList();
		list.add(state);

		rulesService.execute(list);

		assertEquals("", state.getSayThis());
		assertEquals(3, state.getStep());
	}
}
