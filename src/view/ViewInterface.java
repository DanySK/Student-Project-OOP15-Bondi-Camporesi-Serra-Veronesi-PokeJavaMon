package view;

import view.windows.MyFrame;
/**
 * 
 * ViewInterface
 * 
 * @author User
 *
 */
public interface ViewInterface {
	/**
	 * disposeCurrent
	 */
    void disposeCurrent();
	/**
	 * disposeParent
	 */
    void disposeParent();
	/**
	 * showCurrent
	 */
    void showCurrent();
	/**
	 * showParent
	 */
    void showParent();
	/**
	 * hideCurrent
	 */
    void hideCurrent();
	/**
	 * hideParent
	 */
    void hideParent();
	/**
	 * resumeCurrent
	 */
    void resumeCurrent();
	/**
	 * resumeParent
	 */
    void resumeParent();
	/**
	 * getCurrent
	 */
    MyFrame getCurrent();
	/**
	 * getParent
	 */
    MyFrame getParent();
	/**
	 * removeCurrent
	 */
    void removeCurrent();
	/**
	 * addNew
	 */
    void addNew(MyFrame f);
	/**
	 * isEmpty
	 */
    boolean isEmpty();
}