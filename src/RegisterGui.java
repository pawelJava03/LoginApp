import custom.ErrorLabel;
import custom.PasswordFieldCustom;
import custom.TextFieldCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterGui extends JFrame implements ActionListener, FocusListener {

    private ErrorLabel errorUsernameLabel, errorPasswordLabel, errorConfirmPasswordLabel, errorEmailLabel;
    private TextFieldCustom username, email;
    private PasswordFieldCustom password, confirmPassword;


    public RegisterGui() {
        super("Register");
        setSize(Parametry.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Parametry.GLOWNY_KOLOR);

        addGuiComponents();

    }
    private void addGuiComponents() {
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setForeground(Parametry.DRUGI_KOLOR);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(0,0,Parametry.REGISTER_LABEL_SIZE.width,Parametry.REGISTER_LABEL_SIZE.height);

        //Username
        username = new TextFieldCustom("Username", 30);
        username.setBounds(50, registerLabel.getY()+150, Parametry.TEXT_FIELD_SIZE.width, Parametry.TEXT_FIELD_SIZE.height);
        username.addFocusListener(this);
        username.setBackground(Parametry.DRUGI_KOLOR);

        // Username error label
        errorUsernameLabel = new ErrorLabel("!Cannot be less than 5 characters!");
        errorUsernameLabel.setBounds(50, username.getY()+50, Parametry.TEXT_FIELD_SIZE.width, 25);

        //Password
        password = new PasswordFieldCustom("Password", 30);
        password.setBounds(50, username.getY()+100, Parametry.TEXT_FIELD_SIZE.width, Parametry.TEXT_FIELD_SIZE.height);
        password.addFocusListener(this);
        password.setBackground(Parametry.DRUGI_KOLOR);

        // Password error label
        errorPasswordLabel = new ErrorLabel("Password must be at least 8 characters long");
        errorPasswordLabel.setBounds(50, password.getY()+50, Parametry.TEXT_FIELD_SIZE.width, 25);

        //Confirm Password
        confirmPassword = new PasswordFieldCustom("Confirm password", 30);
        confirmPassword.setBounds(50, password.getY()+100, Parametry.TEXT_FIELD_SIZE.width, Parametry.TEXT_FIELD_SIZE.height);
        confirmPassword.addFocusListener(this);
        confirmPassword.setBackground(Parametry.DRUGI_KOLOR);

        //Confirm Password error label
        errorConfirmPasswordLabel = new ErrorLabel("Passwords must be the same!");
        errorConfirmPasswordLabel.setBounds(50, confirmPassword.getY()+50, Parametry.TEXT_FIELD_SIZE.width, 25);

        //Email
        email = new TextFieldCustom("Email", 30);
        email.setBounds(50, confirmPassword.getY()+100, Parametry.TEXT_FIELD_SIZE.width, Parametry.TEXT_FIELD_SIZE.height);
        email.addFocusListener(this);
        email.setBackground(Parametry.DRUGI_KOLOR);

        // Email error label
        errorEmailLabel = new ErrorLabel("Enter correct email");
        errorEmailLabel.setBounds(50, email.getY()+50, Parametry.TEXT_FIELD_SIZE.width, 25);

        //Register (Button)
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, email.getY()+100, Parametry.TEXT_FIELD_SIZE.width, Parametry.TEXT_FIELD_SIZE.height);
        registerButton.setBackground(Parametry.DRUGI_KOLOR);

        registerButton.addActionListener(this);


        //Register -> Login
        JLabel loginLabel = new JLabel("Got an account? Log in!");
        loginLabel.setForeground(Color.red);
        loginLabel.setBounds((Parametry.FRAME_SIZE.width - loginLabel.getPreferredSize().width)/2, registerButton.getY()+100, loginLabel.getPreferredSize().width, loginLabel.getPreferredSize().height);

        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new loginGui().setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }
        });

        //+ -> frame
        getContentPane().add(registerLabel);

        // username
        getContentPane().add(username);
        getContentPane().add(errorUsernameLabel);

        // password
        getContentPane().add(password);
        getContentPane().add(errorPasswordLabel);
        getContentPane().add(confirmPassword);
        getContentPane().add(errorConfirmPasswordLabel);

        // email
        getContentPane().add(email);
        getContentPane().add(errorEmailLabel);

        // register button
        getContentPane().add(registerButton);
        getContentPane().add(loginLabel);


    }


    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        Object fieldSource = e.getSource();
        if(fieldSource == username){
            if(username.getText().length() < 5 || username.isHasPlaceHolder()){
                errorUsernameLabel.setVisible(true);
            }
            else{
                errorUsernameLabel.setVisible(false);
            }
        }else if(fieldSource == password){
            if(password.getText().length() < 8 || password.isHasPlaceHolder()){
                errorPasswordLabel.setVisible(true);
            }
            else{
                errorPasswordLabel.setVisible(false);
            }
        }else if(fieldSource == confirmPassword){
            if(!password.getText().equals(confirmPassword.getText())){
                errorConfirmPasswordLabel.setVisible(true);
            }
            else{
                errorConfirmPasswordLabel.setVisible(false);
            }
        }else if(fieldSource == email){
            String eemail = email.getText();
            // spr czy email zawiera "@"
            if(!eemail.contains("@")){
                errorEmailLabel.setVisible(true);
            }
            else{
                errorEmailLabel.setVisible(false);
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Register")){
            boolean isPossible = !errorUsernameLabel.isVisible() && !errorPasswordLabel.isVisible() &&
                    !errorConfirmPasswordLabel.isVisible() && !errorEmailLabel.isVisible() && !username.isHasPlaceHolder() && !password.isHasPlaceHolder()
                    && !confirmPassword.isHasPlaceHolder() && !email.isHasPlaceHolder();

            JDialog result = new JDialog();
            result.setSize(550,100);
            result.setLocationRelativeTo(this);
            result.setModal(true);

            JLabel resultLabel = new JLabel("Registering...");
            resultLabel.setPreferredSize(Parametry.DIALOG_SIZE);
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

            result.add(resultLabel);

            if(isPossible){
                String uusername = username.getText();
                String ppassword = password.getText();

                UserDB.addUser(uusername, ppassword);

                // info o utworzonym użytkowniku
                resultLabel.setText("User has been added successfully!");

                // register -> login page (po zamknięciu onka)
                result.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                        new loginGui().setVisible(true);
                    }
                });
            }else{
                //error
                resultLabel.setText("Something went wrong! Check your inputs and try again!");
            }
            result.setVisible(true);
        }
    }
}
