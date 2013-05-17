package fr.utc.nf28.moka;

import fr.utc.nf28.moka.agents.connection.ConnectionRequest;
import fr.utc.nf28.moka.agents.itemcreation.CreationRequest;
import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.items.ImageLink;
import fr.utc.nf28.moka.environment.items.LockingRequest;
import fr.utc.nf28.moka.environment.items.UmlClass;
import fr.utc.nf28.moka.environment.users.User;
import fr.utc.nf28.moka.util.JSONParserUtils;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;

import java.awt.*;

public class Launcher {
    public static void main(String[] args) {
        final Runtime runtime = Runtime.instance();
        try {
            final AgentContainer container = runtime.createMainContainer(new ProfileImpl("profile.txt"));
            MokaEnvironment environment = new MokaEnvironment();
            container.createNewAgent("ConnectionAgent", "fr.utc.nf28.moka.agents.connection.ConnectionAgent", new Object[]{environment}).start();
            container.createNewAgent("ItemCreationAgent", "fr.utc.nf28.moka.agents.itemcreation.ItemCreationAgent", new Object[]{environment}).start();
            container.createNewAgent("ItemEditionAgent", "fr.utc.nf28.moka.agents.itemedition.ItemEditionAgent", new Object[]{environment}).start();
            container.createNewAgent("ItemLockingAgent", "fr.utc.nf28.moka.agents.itemedition.ItemLockingAgent", new Object[]{environment}).start();
			container.createNewAgent("WebSocketAgent", "fr.utc.nf28.moka.agents.websocket.WebSocketAgent", null).start();

            // USER
            User user = new User("Alexandre", "Masciulli");
            user.setColor(Color.WHITE.getRGB());
            user.setIp("127.0.0.1");

            ConnectionRequest connectionRequest = new ConnectionRequest();
            connectionRequest.setType("connection");
            connectionRequest.setRequest(JSONParserUtils.serializeUser(user));
            System.out.println("connection == ");
            System.out.println(JSONParserUtils.serializeConnectionRequest(connectionRequest));

            ConnectionRequest disconnectionRequest = new ConnectionRequest();
            disconnectionRequest.setType("disconnection");
            disconnectionRequest.setRequest(user.getIp());

            System.out.println("disconnection == ");
            System.out.println(JSONParserUtils.serializeConnectionRequest(disconnectionRequest));

            // ITEMS
            UmlClass uml = new UmlClass("MyClass", 100, 100, "MyClass");

            ImageLink image = new ImageLink("Image1", 200, 200, "http://masciulli.fr/img.png");


            CreationRequest creationRequest = new CreationRequest();
            creationRequest.setType("creation");
            creationRequest.setRequest(JSONParserUtils.serializeItem(uml));
            System.out.println("UML creation == ");
            System.out.println(JSONParserUtils.serializeCreationRequest(creationRequest));

            CreationRequest destructionRequest = new CreationRequest();
            destructionRequest.setType("destruction");
            destructionRequest.setRequest(String.valueOf(uml.getId()));
            System.out.println("UML destruction == ");
            System.out.println(JSONParserUtils.serializeCreationRequest(destructionRequest));




            LockingRequest lockingRequest = new LockingRequest();
            lockingRequest.itemId = 0;
            lockingRequest.userIp = "127.0.0.1";
            System.out.println(JSONParserUtils.serializeLockingRequest(lockingRequest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
