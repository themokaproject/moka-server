package fr.utc.nf28.moka;

import fr.utc.nf28.moka.data.MokaEnvironment;
import jade.core.ProfileException;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Launcher {
    public static void main(String[] args) {
        final Runtime runtime = Runtime.instance();
        try {
            final AgentContainer container = runtime.createMainContainer(new ProfileImpl("profile.txt"));
            MokaEnvironment environment = new MokaEnvironment();
            container.createNewAgent("ConnectionAgent", "fr.utc.nf28.moka.agents.ConnectionAgent", new Object[]{environment}).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
