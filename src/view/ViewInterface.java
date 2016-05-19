package view;

import view.windows.MyFrame;

public interface ViewInterface {

    void disposeCurrent();

    void disposeParent();

    void showCurrent();

    void showParent();

    void hideCurrent();

    void hideParent();

    void resumeCurrent();

    void resumeParent();

    MyFrame getCurrent();

    MyFrame getParent();

    void removeCurrent();

    void addNew(MyFrame f);

    boolean isEmpty();
}