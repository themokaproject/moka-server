package fr.utc.nf28.moka.agents.rest;


import com.sun.net.httpserver.HttpServer;
import fr.utc.nf28.moka.agents.BaseAgent;
import fr.utc.nf28.moka.rest.RestServerManager;

import java.io.IOException;

public class RestServerAgent extends BaseAgent {
	public void setup() {
		super.setup();
		try {
			HttpServer server = RestServerManager.getServer();
			server.start();
			System.out.println("Rest Server start: success!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Rest Server start: fail!");
		}

	}
}
