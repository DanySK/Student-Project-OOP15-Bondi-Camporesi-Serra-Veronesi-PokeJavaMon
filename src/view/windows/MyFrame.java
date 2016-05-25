package view.windows;
/**
 * An interface used to manage frame's actions.
 * 
 * @author Daniel Veronesi
 */
public interface MyFrame {
	/**
	 * It shows the frame.
	 */
    public void showFrame();
	/**
	 * It disposes the frame.
	 */
    public void disposeFrame();
	/**
	 * It hides theframe.
	 */
    public void hideFrame();
	/**
	 * It resumes the frame.
	 */
    public void resumeFrame();
}
