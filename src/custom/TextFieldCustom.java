package custom;

import javax.swing.*;
import java.awt.event.*;

public class TextFieldCustom extends JTextField {
    private String placeHolder;
    private boolean hasPlaceHolder;

    public TextFieldCustom(String placeHolder, int charLimit) {
        super();
        this.placeHolder =  placeHolder;

        hasPlaceHolder=true;

        setDocument(new limitText(charLimit));
        setText(this.placeHolder);

        addListeners();
    }
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (hasPlaceHolder){
                    hasPlaceHolder = false;
                    setText("");
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(hasPlaceHolder){
                    hasPlaceHolder =  false;
                    setText("");
                }
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {

            }
            @Override
           public void focusLost(FocusEvent e) {
                if(getText().toString().length()<=0){
                    hasPlaceHolder = true;
                    setText(placeHolder);
                }
            }
        });
    }

    public boolean isHasPlaceHolder() {
        return hasPlaceHolder;
    }

    public void setHasPlaceHolder(boolean hasPlaceHolder) {
        this.hasPlaceHolder = hasPlaceHolder;
    }
}
