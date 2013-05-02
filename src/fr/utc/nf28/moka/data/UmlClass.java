package fr.utc.nf28.moka.data;

import java.util.ArrayList;
import java.util.List;

public class UmlClass extends MokaItem {
    private String mClassName;
    private List<String> mMembers = new ArrayList<String>();
    private List<String> mMethods = new ArrayList<String>();


    public UmlClass(int x, int y, String className) {
        super(x, y);
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
}
