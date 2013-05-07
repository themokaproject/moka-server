package fr.utc.nf28.moka.environment.items;

/**
 * Small holder that contains the information needed to lock an item. Used for serialization
 */
public class LockingRequest {
    public String userIp;
    public int itemId;

    public LockingRequest() {
    }
}
