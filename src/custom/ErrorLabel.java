package custom;

import javax.swing.*;
import java.awt.*;

public class ErrorLabel extends JLabel {
    public ErrorLabel(String text) {
        super(text);
        setForeground(Color.RED);
        setVisible(false);

    }
}
