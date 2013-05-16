package fr.utc.nf28.moka.agents.itemcreation;

/**
 * Used for item creation or destruction
 */
public class CreationRequest {
    /**
     * examples of type : creation, destruction
     */
    private String mType;
    private String mRequest;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getRequest() {
        return mRequest;
    }

    public void setRequest(String request) {
        this.mRequest = request;
    }
}
