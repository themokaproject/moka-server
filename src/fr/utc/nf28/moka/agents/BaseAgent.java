package fr.utc.nf28.moka.agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 * A base agent that can register skills
 * and retrieve agents based on their skills
 */
public class BaseAgent extends Agent {

	/**
	 * Register a skill with a type
	 *
	 * @param skillType
	 */
	private void registerSkill(String skillType) {
		registerSkill("", skillType);
	}

	/**
	 * Register a skill with a name and a type
	 *
	 * @param skillName
	 * @param skillType
	 */
	 private void registerSkill(String skillName, String skillType) {
		DFAgentDescription agentDescription = new DFAgentDescription();
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName(skillName);
		serviceDescription.setType(skillType);
		agentDescription.addServices(serviceDescription);

		try {
			DFService.register(this, agentDescription);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

}
