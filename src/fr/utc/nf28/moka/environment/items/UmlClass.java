package fr.utc.nf28.moka.environment.items;

import java.util.ArrayList;
import java.util.List;

/**
 * A UML class item.
 */
public class UmlClass extends MokaItem {
    private String mClassName;
    private List<String> mMembers = new ArrayList<String>();
    private List<String> mMethods = new ArrayList<String>();
    private static final String sType = "umlClass";

    public UmlClass() {
    }
    public UmlClass(String title, int x, int y, String className) {
        super(title, x, y, sType);
        mClassName = className;
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String className) {
        mClassName = className;
    }

    public List<String> getMembers() {
        return mMembers;
    }

    public List<String> getMethods() {
        return mMethods;
    }

    public void addMember(String member) {
        mMembers.add(member);
    }

    public void addMethod(String method) {
        mMethods.add(method);
    }

    public void removeMember(String member) {
        mMembers.remove(member);
    }

    public void removeMethod(String method) {
        mMethods.remove(method);
    }

    public void clearMembers() {
        mMembers = new ArrayList<String>();
    }

    public void clearMethods() {
        mMethods = new ArrayList<String>();
    }

    public String toString() {
        return "umlclass:" + mClassName + " " + super.toString();
    }
}
