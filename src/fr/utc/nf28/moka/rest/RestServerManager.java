package fr.utc.nf28.moka.rest;


import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class RestServerManager {
	private static final String HOST = "http://127.0.0.1/api/";
	private static final int PORT = 80;
	private static final URI BASE_URI = UriBuilder.fromUri(HOST).port(PORT).build();

	public static HttpServer getServer() throws IOException {
		return HttpServerFactory.create(BASE_URI, new PackagesResourceConfig("fr.utc.nf28.moka.rest.resource"));
	}
}
