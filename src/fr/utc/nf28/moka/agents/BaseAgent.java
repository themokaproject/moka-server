package fr.utc.nf28.moka.agents;

import fr.utc.nf28.moka.util.JSONParserUtils;
import fr.utc.nf28.moka.util.JadeUtils;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A base agent that can register skills
 * and retrieve agents based on their skills
 */
public class BaseAgent extends Agent {


	/**
	 * Retrieve all the agents AID that have a skill
	 * that matches the name
	 *
	 * @param skillName
	 * @return an arrayList of AIDs
	 */
	protected ArrayList<AID> getAgentsWithSkill(String skillName) {
		return getAgentsWithSkill(skillName, JadeUtils.JADE_SKILL_TYPE_DEFAULT);
	}

	/**
	 * Retrieve all the agents AID that have a skill
	 * that matches the name and the type
	 *
	 * @param skillName
	 * @param skillType
	 * @return an arrayList of AIDs
	 */
	protected ArrayList<AID> getAgentsWithSkill(String skillName, String skillType) {
		ArrayList<AID> result = new ArrayList<AID>();
		try {
			DFAgentDescription[] agentDescriptions = DFService.search(this, getAgentDescriptionWithService(skillName, skillType));
			for (DFAgentDescription ad : agentDescriptions) {
				result.add(ad.getName());
			}
		} catch (FIPAException e) {
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Register a skill with a name
	 *
	 * @param skillName
	 */
	protected void registerSkill(String skillName) {
		//use a default type
		// type is a mandatory field of a service-description
		registerSkill(skillName, JadeUtils.JADE_SKILL_TYPE_DEFAULT);
	}

	/**
	 * Register a skill with a name and a type
	 *
	 * @param skillName
	 * @param skillType
	 */
	protected void registerSkill(String skillName, String skillType) {
		try {
			DFService.register(this, getAgentDescriptionWithService(skillName, skillType));
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	protected void registerSkills(HashMap<String, String> skillNameAndType)  {
		try {
			DFService.register(this, getAgentDescriptionWithServices(skillNameAndType));
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	/**
	 * send JADE message
	 *
	 * @param receivers   use getAgentsWithSkill
	 * @param content     message content
	 * @param performatif ACL performatif
	 */
	protected void sendMessage(ArrayList<AID> receivers, String content, int performatif) {
		final ACLMessage connectionMessage = new ACLMessage(performatif);
		for (AID receiver : receivers) {
			connectionMessage.addReceiver(receiver);
		}
		connectionMessage.setContent(content);
		send(connectionMessage);
	}

	private DFAgentDescription getAgentDescriptionWithServices(HashMap<String, String> skillNameAndType) {
		DFAgentDescription agentDescription = new DFAgentDescription();
		for (Iterator iterator = skillNameAndType.entrySet().iterator(); iterator.hasNext(); ) {
			final Map.Entry entry = (Map.Entry)iterator.next();
			ServiceDescription serviceDescription = new ServiceDescription();
			serviceDescription.setName((String)entry.getKey());
			serviceDescription.setType((String)entry.getValue());
			agentDescription.addServices(serviceDescription);
		}
		return agentDescription;
	}

	private DFAgentDescription getAgentDescriptionWithService(String skillName, String skillType) {
		DFAgentDescription agentDescription = new DFAgentDescription();
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName(skillName);
		serviceDescription.setType(skillType);
		agentDescription.addServices(serviceDescription);
		return agentDescription;
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

	/**
	 * send ACL request to all android device to inform that a new item has been created
	 * refresh history and current list
	 *
	 * @throws java.io.IOException
	 */
	public void requestAndroidCurrentItemsListRefresh() throws IOException {
		final A2ATransaction refreshTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_REFRESH_CURRENT_ITEMS,
						"new item created, refresh list.");
		sendMessageToAndroidDevice(ACLMessage.REQUEST,
				JSONParserUtils.serializeA2ATransaction(refreshTransaction));
	}

	/**
	 * send ACL request to all android device to inform that item has been deleted
	 * refresh only history
	 *
	 * @throws IOException
	 */
	public void requestAndroidHistoryRefresh() throws IOException {
		final A2ATransaction refreshTransaction =
				new A2ATransaction(JadeUtils.TRANSACTION_TYPE_REFRESH_HISTORY,
						"new item created, refresh list.");
		sendMessageToAndroidDevice(ACLMessage.REQUEST,
				JSONParserUtils.serializeA2ATransaction(refreshTransaction));
	}

}
