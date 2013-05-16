package fr.utc.nf28.moka.agents.itemedition;

/**
 * Class used to hold the edition type and info
 */
public class EditionRequest {
    /**
     * examples of edit types : rotation, move, ...
     */
    private String mEditType;
    private String mRequest;

    public String getEditType() {
        return mEditType;
    }

    public void setEditType(String editType) {
        this.mEditType = editType;
    }

    public String getRequest() {
        return mRequest;
    }

    public void setRequest(String request) {
        this.mRequest = request;
    }

}
