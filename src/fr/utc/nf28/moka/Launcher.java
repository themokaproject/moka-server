package fr.utc.nf28.moka;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.environment.UmlClass;
import fr.utc.nf28.moka.environment.User;
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

            User user = new User("Alexandre", "Masciulli");
            user.setColor(Color.WHITE.getRGB());
            user.setIp("127.0.0.1");
            System.out.println(JSONParserUtils.serializeUser(user));

            UmlClass uml = new UmlClass(0,0,"MyClass");
            System.out.println(JSONParserUtils.serializeItem(uml));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
