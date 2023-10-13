package custom;

import javax.swing.*;
import java.awt.event.*;

public class PasswordFieldCustom extends JPasswordField {
    private String placeHolder;
    private boolean hasPlaceHolder;

    public PasswordFieldCustom(String placeHolder, int charLimit) {
        super();
        this.placeHolder =  placeHolder;

        hasPlaceHolder=true;

        setDocument(new limitText(charLimit));
        setText(this.placeHolder);
        setEchoChar((char)0);

        addListeners();
    }
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (hasPlaceHolder){
                    hasPlaceHolder = false;
                    setText("");
                    setEchoChar('*');
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(hasPlaceHolder){
                    hasPlaceHolder =  false;
                    setText("");
                    setEchoChar('*');
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
                    setEchoChar((char)0);
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