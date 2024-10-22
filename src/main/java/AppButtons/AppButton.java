package AppButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AppButton extends JButton {
    public AppButton(String text) {
        super(text);
        this.setPreferredSize(new Dimension(60, 25));
    }
}
