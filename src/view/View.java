package view;

import java.util.Stack;

import view.windows.MyFrame;

public class View implements ViewInterface {

    private MyFrame parent;
    private Stack<MyFrame> stack;
    private static ViewInterface singleton;
    
    private View() {
        this.stack = new Stack<MyFrame>();
    }
    
    public static ViewInterface getView() {
        if (singleton == null) {
            synchronized (View.class) {
                if (singleton == null) {
                    singleton = new View();
                }
            }
        }
        return singleton;
    }
    
    @Override
    public void disposeCurrent() {
        stack.peek().disposeFrame();
    }
    
    @Override
    public void disposeParent() {
        parent.disposeFrame();
    }
    
    @Override
    public void showCurrent() {
        stack.peek().showFrame();
    }
    
    @Override
    public void showParent() {
        parent.showFrame();
    }
    
    @Override
    public void hideCurrent() {
        stack.peek().hideFrame();
    }
    
    @Override
    public void hideParent() {
        parent.hideFrame();
    }
    
    @Override
    public void resumeCurrent() {
        stack.peek().resumeFrame();
    }
    
    @Override
    public void resumeParent() {
        parent.resumeFrame();
    }
    
    @Override
    public MyFrame getCurrent() {
        return stack.peek();
    }
    
    @Override
    public MyFrame getParent() {
        return parent;
    }
    
    @Override
    public void removeCurrent() {
        stack.pop();
    }
    
    @Override
    public void addNew(MyFrame f) {
        if (!stack.isEmpty()) {
            parent = stack.peek();
        }
        stack.push(f);
    }
    
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}