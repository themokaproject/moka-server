package fr.utc.nf28.moka.environment.items;

import java.util.ArrayList;
import java.util.List;

/**
 * A UML class item.
 */
public class UmlClass extends MokaItem {
	private static final String sType = "umlClass";
	protected static final String DEFAULT_TITLE = "Uml";
	private String mClassName;
	private List<String> mMembers = new ArrayList<String>();
	private List<String> mMethods = new ArrayList<String>();

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
		mMembers.clear();
	}

	public void clearMethods() {
		mMethods.clear();
	}

	public String toString() {
		return "umlclass:" + mClassName + " " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = "Class Uml " + String.valueOf(mId);
	}

	@Override
	public void update(String field, String newValue) {
		if ("className".equals(field)) {
			mClassName = newValue;
		} else {
			super.update(field, newValue);
		}
	}
}
