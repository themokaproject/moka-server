package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import jade.core.Agent;

/**
 * A generic Agent for Moka
 * Has a reference to its MokaEnvironment
 */
public class MokaAgent extends Agent {
    protected MokaEnvironment mEnvironment;

    public void setup() {
        final Object[] args = getArguments();
        mEnvironment = (MokaEnvironment)args[0];
    }

    public MokaEnvironment getEnvironment() {
        return mEnvironment;
    }


}
