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

	private static User user;
	private static UmlClass item;

	public static void main(String[] args) throws UnknownHostException {
		WebSocketImpl.DEBUG = true;
		int port = 8887;

		MokaWebSocketServer test = new MokaWebSocketServer(port);
		test.start();

		System.out.println( "Server started on port: " + test.getPort() );

		try{
			initTest();
			readAction(test);
		} catch (IOException e){
			e.printStackTrace();;
		}

	}


	private static void initTest(){
		user = new User("Vincent", "Barthélémy");
		user.setIp("127.0.0.1");
		item = new UmlClass();
		item.setId(144);
		item.setType("umlClass");
		item.setX(300);
		item.setY(450);
	}


	public static void readAction(MokaWebSocketServer server) throws IOException {
		String action;
		Scanner scanIn = new Scanner(System.in);
		while(true){
			System.out.println("action :");
			action = scanIn.nextLine();
			System.out.println(action);

			if(action.equals("1")){
				server.addUser(user.getIp(), user.getFirstName());
			}else if(action.equals("2")){
				server.removeUser(user.getIp());
			}else if(action.equals("3")){
				server.addItem(item.getType(), String.valueOf(item.getId()));
			}else if(action.equals("4")){
				server.removeItem(String.valueOf(item.getId()));
			}else if(action.equals("5")){
				server.moveItem(String.valueOf(item.getId()), item.getX(), item.getY());
			}else if(action.equals("6")){
				server.selectItem(user.getIp(), String.valueOf(item.getId()));
			}
		}
	}

}
