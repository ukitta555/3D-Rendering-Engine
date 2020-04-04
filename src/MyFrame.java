import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.event.*;

public class MyFrame extends JFrame implements KeyListener
{

    MyJPanel renderPanel;
    int headingSlider;
    int rollSlider;
    int pitchSlider;
    int width;
    int height;

    public MyFrame (String s) { //переделанный под ООП вариант нашего окна
        super ("123");
        Container pane = getContentPane(); //контейнер для изображения
        pane.setLayout(new BorderLayout());
        this.headingSlider = 0;
        this.pitchSlider = 0;
        this.rollSlider = 0;
        addKeyListener (this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        //Cтавим курсор в центр экрана
        try {
            Robot robot = new Robot();
            robot.mouseMove((int)(width / 2), (int)(height / 2));
        } catch (Exception ex) {}
        makeCursorTransparent();
        renderPanel = new MyJPanel(headingSlider, pitchSlider, rollSlider, (int)(width / 2), (int)(height / 2));
        pane.add(renderPanel, BorderLayout.CENTER);
        setSize((int)width, (int)height);
        setVisible(true);
    }

    public  void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 82)
            renderPanel.resetValues();
        if (e.getKeyCode()==39 | e.getKeyCode()==68) {
            renderPanel.subtractHeading();
            renderPanel.subtractDeltaX ();
            renderPanel.rightRelativeChangeToFoV();
        }
        if (e.getKeyCode() == 38 | e.getKeyCode() == 87)
            renderPanel.addFoV(1);
        if (e.getKeyCode() == 37 | e.getKeyCode() == 65) {
            renderPanel.addHeading();
            renderPanel.addDeltaX ();
            renderPanel.leftRelativeChangeToFoV();
        }
        if (e.getKeyCode() == 40 | e.getKeyCode() == 83)
            renderPanel.subtractFoV(1);
        renderPanel.repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void makeCursorTransparent() {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        getContentPane().setCursor(blankCursor);
    }
}
