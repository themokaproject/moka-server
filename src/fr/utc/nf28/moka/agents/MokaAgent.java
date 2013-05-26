package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.environment.MokaEnvironment;
import fr.utc.nf28.moka.util.JadeUtils;

/**
 * A generic Agent for Moka
 * Has a reference to its MokaEnvironment
 */
public class MokaAgent extends BaseAgent {
	protected MokaEnvironment mEnvironment;

	public void setup() {
		super.setup();
		final Object[] args = getArguments();
		mEnvironment = (MokaEnvironment) args[0];
	}

	public MokaEnvironment getEnvironment() {
		return mEnvironment;
	}

	/**
	 * send message to all android devices
	 *
	 * @param performatif message's performatif
	 * @param content     message's content
	 */
	protected void sendMessageToAndroidDevice(final int performatif, final String content) {
		sendMessage(getAgentsWithSkill(JadeUtils.JADE_SKILL_NAME_ANDROID),
				content,
				performatif);
	}


}
