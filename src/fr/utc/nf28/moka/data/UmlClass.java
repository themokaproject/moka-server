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

    public void addMember(String member) {
        mMembers.add(member);
    }

    public void addMethod(String method) {
        mMethods.add(method);
    }
}
