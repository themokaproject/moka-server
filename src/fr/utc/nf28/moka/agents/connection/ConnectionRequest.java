package fr.utc.nf28.moka.agents.connection;

/**
 * Used to hold any connection/disconnection message
 */
public class ConnectionRequest {

    /**
     * examples of mType : connect, disconnect
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
