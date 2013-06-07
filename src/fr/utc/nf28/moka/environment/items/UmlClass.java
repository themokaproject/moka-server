package fr.utc.nf28.moka.environment.items;

import java.util.ArrayList;
import java.util.List;

/**
 * A UML class item.
 */
public class UmlClass extends MokaItem {
	private static final String sType = "umlClass";
	private static final String DEFAULT_TITLE = "Classe Uml";
	private List<String> mMembers = new ArrayList<String>();
	private List<String> mMethods = new ArrayList<String>();

	public UmlClass() {
	}

	public UmlClass(int x, int y) {
		super(DEFAULT_TITLE, x, y, sType);
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
		return "umlclass: " + super.toString();
	}

	@Override
	public void makeDefaultTitle() {
		mTitle = DEFAULT_TITLE + " " + String.valueOf(mId);
	}

	@Override
	public void update(String field, String newValue) {
		super.update(field, newValue);
	}

}
