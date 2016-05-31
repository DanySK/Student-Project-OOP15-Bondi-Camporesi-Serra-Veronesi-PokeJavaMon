package view;

import javax.swing.JWindow;

import view.windows.MyFrame;
/**
 * 
 * This interface explains all the methods that can be called on {@link View}.
 *
 */
public interface ViewInterface {
	/**
	 * It disposes the current {@link JWindow} of the stack.
	 */
    void disposeCurrent();
	/**
	 * It disposes the previous {@link JWindow} of the stack.
	 */
    void disposeParent();
	/**
	 * It shows the current {@link JWindow} of the stack.
	 */
    void showCurrent();
	/**
	 * It shows the previous {@link JWindow} of the stack.
	 */
    void showParent();
	/**
	 * It hides the current {@link JWindow} of the stack.
	 */
    void hideCurrent();
	/**
	 * It hides the previous {@link JWindow} of the stack.
	 */
    void hideParent();
	/**
	 * It resumes the current {@link JWindow} of the stack.
	 */
    void resumeCurrent();
	/**
	 * It resumes the previous {@link JWindow} of the stack.
	 */
    void resumeParent();
	/**
	 * @return the current frame of the stack.
	 */
    MyFrame getCurrent();
	/**
	 * @return the previous frame of the stack.
	 */
    MyFrame getParent();
	/**
	 * It removes the current window of the stack.
	 */
    void removeCurrent();
	/**
	 * It adds a new Frame in the stacks.
	 * @param f frame
	 */
    void addNew(MyFrame f);
	/**
	 * @return if the stack is empty or not.
	 */
    boolean isEmpty();
}