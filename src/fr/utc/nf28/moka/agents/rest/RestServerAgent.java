package fr.utc.nf28.moka.agents.rest;


import fr.utc.nf28.moka.agents.BaseAgent;
import fr.utc.nf28.moka.rest.RestServerManager;

import java.io.IOException;

public class RestServerAgent extends BaseAgent {
	@Override
	public void setup() {
		super.setup();
		try {
			RestServerManager.getServer().start();
			System.out.println("Rest Server start: success!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Rest Server start: fail!");
		}
	}
}
