package fr.utc.nf28.moka.agents;

/**
 * common Transaction between two agents
 */
public class A2ATransaction {
	private String mType;
	private Object mContent;

	public A2ATransaction(String type) {
		this.mType = type;
	}

	public A2ATransaction(String type, Object content) {
		this.mType = type;
		this.mContent = content;
	}

	public void setType(String type){
		this.mType = type;
	}

	public String getType(){
		return mType;
	}

	public void setContent(Object content){
		this.mContent = content;
	}

	public Object getContent(){
		return mContent;
	}
}
