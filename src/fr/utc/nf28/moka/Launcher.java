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
            final MokaEnvironment environment = MokaEnvironment.getInstance();
            container.createNewAgent("ConnectionAgent", "fr.utc.nf28.moka.agents.connection.ConnectionAgent", new Object[]{environment}).start();
            container.createNewAgent("ItemCreationAgent", "fr.utc.nf28.moka.agents.itemcreation.ItemCreationAgent", new Object[]{environment}).start();
            container.createNewAgent("ItemEditionAgent", "fr.utc.nf28.moka.agents.itemedition.ItemEditionAgent", new Object[]{environment}).start();
            container.createNewAgent("ItemLockingAgent", "fr.utc.nf28.moka.agents.itemedition.ItemLockingAgent", new Object[]{environment}).start();
			container.createNewAgent("WebSocketAgent", "fr.utc.nf28.moka.agents.websocket.WebSocketAgent", null).start();
			container.createNewAgent("RestServerAgent", "fr.utc.nf28.moka.agents.rest.RestServerAgent", null).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
