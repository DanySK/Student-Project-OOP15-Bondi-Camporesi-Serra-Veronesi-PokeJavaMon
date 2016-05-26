package view;

import view.windows.MyFrame;
/**
 * 
 * This interface explains all the methods that can be called on {@link View}.
 *
 */
public interface ViewInterface {
	/**
	 * It disposes the current window of the stack.
	 */
    void disposeCurrent();
	/**
	 * It disposes the previous window of the stack.
	 */
    void disposeParent();
	/**
	 * It shows the current window of the stack.
	 */
    void showCurrent();
	/**
	 * It shows the previous window of the stack.
	 */
    void showParent();
	/**
	 * It hides the current window of the stack.
	 */
    void hideCurrent();
	/**
	 * It hides the previous window of the stack.
	 */
    void hideParent();
	/**
	 * It resumes the current window of the stack.
	 */
    void resumeCurrent();
	/**
	 * It resumes the previous window of the stack.
	 */
    void resumeParent();
	/**
	 * It gets the current frame of the stack.
	 */
    MyFrame getCurrent();
	/**
	 * It gets the previous frame of the stack.
	 */
    MyFrame getParent();
	/**
	 * It removes the current window of the stack.
	 */
    void removeCurrent();
	/**
	 * It adds a new Frame in the stacks.
	 */
    void addNew(MyFrame f);
	/**
	 * It verifies if the stack is empty or not.
	 */
    boolean isEmpty();
}