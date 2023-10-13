import custom.PasswordFieldCustom;
import custom.TextFieldCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class loginGui extends JFrame implements ActionListener {

    private TextFieldCustom usernameField;
    private PasswordFieldCustom passwordField;

    public loginGui() {
        super("Login");
        setSize(Parametry.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Parametry.GLOWNY_KOLOR);

        addGuiComponents();
    }

    private void addGuiComponents() {

        // ikonka
        JLabel loginImage = CustomTools.loadimage(Parametry.LOGIN_IMAGE_PATH);

        loginImage.setBounds((Parametry.FRAME_SIZE.width - loginImage.getPreferredSize().width) / 2, 25,
                Parametry.LOGIN_IMAGE_SIZE.width, Parametry.LOGIN_IMAGE_SIZE.height
        );

        // "Username"
        usernameField = new TextFieldCustom("Enter Username", 30);
        usernameField.setBounds(50, loginImage.getY() + 315, Parametry.TEXT_FIELD_SIZE.width, Parametry.TEXT_FIELD_SIZE.height);
        usernameField.setBackground(Parametry.DRUGI_KOLOR);


        // "Password"
        passwordField = new PasswordFieldCustom("Enter Password", 30);
        passwordField.setBackground(Parametry.DRUGI_KOLOR);
        passwordField.setBounds(50, usernameField.getY() + 100, Parametry.TEXT_FIELD_SIZE.width, Parametry.TEXT_FIELD_SIZE.height
        );

        // "LOGIN" button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Parametry.DRUGI_KOLOR);

        loginButton.setBounds(50, passwordField.getY() + 150, Parametry.BUTTON_SIZE.width, Parametry.BUTTON_SIZE.height);
        loginButton.addActionListener(this);


        // login -> register
        JLabel registerLabel = new JLabel("Haven't got account? Click here to register");
        registerLabel.setForeground(Color.RED);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds((Parametry.FRAME_SIZE.width - registerLabel.getPreferredSize().width) / 2,
                loginButton.getY() + 100, registerLabel.getPreferredSize().width
                , registerLabel.getPreferredSize().height);

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new RegisterGui().setVisible(true);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });



        // dodawanie do :FRAME:
        getContentPane().add(loginImage);
        getContentPane().add(usernameField);
        getContentPane().add(passwordField);
        getContentPane().add(loginButton);
        getContentPane().add(registerLabel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Login")) {
            JDialog result = new JDialog();
            result.setPreferredSize(Parametry.DIALOG_SIZE);
            result.pack();
            result.setLocationRelativeTo(this);
            result.setModal(true);

            //create label (display results)
            JLabel resultLabel = new JLabel();
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            result.add(resultLabel);

            String username = usernameField.getText();
            String password = passwordField.getText();


            if(UserDB.userDB.get(username) != null){
                // sprawdzam haslo
                String sprHaslo = UserDB.userDB.get(username);
                if (password.equals(sprHaslo)) {
                    resultLabel.setText("Login success!");
                }else{
                    resultLabel.setText("Wrong password!");
                }
            }else {
                resultLabel.setText("Wrong username!");
            }
            result.setVisible(true);
        }
    }
}