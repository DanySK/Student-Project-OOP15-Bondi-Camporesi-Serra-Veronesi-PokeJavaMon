package view;

import java.util.Stack;

import view.windows.MyFrame;

public class View {

    private MyFrame parent;
    private Stack<MyFrame> stack;
    private static View singleton;
    
    private View() {
        this.stack = new Stack<MyFrame>();
    }
    
    public static View getView() {
        if (singleton == null) {
            synchronized (View.class) {
                if (singleton == null) {
                    singleton = new View();
                }
            }
        }
        return singleton;
    }
    
    public void disposeCurrent() {
        stack.peek().disposeFrame();
    }
    
    public void disposeParent() {
        parent.disposeFrame();
    }
    
    public void showCurrent() {
        stack.peek().showFrame();
    }
    
    public void showParent() {
        parent.showFrame();
    }
    
    public void hideCurrent() {
        stack.peek().hideFrame();
    }
    
    public void hideParent() {
        parent.hideFrame();
    }
    
    public void resumeCurrent() {
        stack.peek().resumeFrame();
    }
    
    public void resumeParent() {
        parent.resumeFrame();
    }
    
    public MyFrame getCurrent() {
        return stack.peek();
    }
    
    public MyFrame getParent() {
        return parent;
    }
    
    public void removeCurrent() {
        stack.pop();
    }
    
    public void addNew(MyFrame f) {
        if (!stack.isEmpty()) {
            parent = stack.peek();
        }
        stack.push(f);
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}