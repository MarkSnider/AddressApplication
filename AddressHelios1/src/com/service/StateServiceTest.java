package com.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.dao.State;

public class StateServiceTest {

	@Test
	public void testSelectAllStates() {
		List<State> theStatesList = StateService.selectAllStates();
		assert(theStatesList != null);
		assert(theStatesList.size() != 0);
	}

}
