package fr.utc.nf28.moka.test;

import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.websocket.MokaWebSocketServer;
import org.java_websocket.WebSocketImpl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MokaWebSocketLauncher {

	public static void main(String[] args) throws UnknownHostException {
		WebSocketImpl.DEBUG = true;
		int port = 8887;

		MokaWebSocketServer test = new MokaWebSocketServer(port);
		test.start();

		System.out.println( "Server started on port: " + test.getPort() );

		try{
			readAction(test);
		} catch (IOException e){
			e.printStackTrace();;
		}

	}
	public static void readAction(MokaWebSocketServer server) throws IOException {
		String action;
		Scanner scanIn = new Scanner(System.in);
		while(true){
			System.out.println("action :");
			action = scanIn.nextLine();
			System.out.println(action);

			if(action.equals("1")){
				User vb = new User();
				vb.setFirstName("Vincent");
				vb.setIp("127.0.0.1");
				server.addUser(vb);
			}else if(action.equals("2")){
				User vb = new User();
				vb.setIp("127.0.0.1");
				server.removeUser(vb);
			}else if(action.equals("3")){
				UmlClass item = new UmlClass();
				item.setId(144);
				item.setType("umlClass");
				server.addItem(item);
			}else if(action.equals("4")){
				UmlClass item = new UmlClass();
				item.setId(144);
				server.removeItem(item);
			}
		}
	}

}
