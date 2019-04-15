import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame{

    private String name, pword;
    private static final String OK_COMMAND = "ok";
    private static final String CANCEL_COMMAND = "cancel";


    public LoginScreen(){
        this.setTitle("RSA Verschlüsselung");

        //Login Screen
        JPanel loginScreen = new JPanel(new BorderLayout());
        JPanel logInCenter = new JPanel(new GridLayout(0,2));
        JPanel logInSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ((GridLayout)logInCenter.getLayout()).setVgap(10);
        ((GridLayout)logInCenter.getLayout()).setHgap(20);

        //create & assign elements for LogInCenter
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        logInCenter.add(new JLabel("Username: "));
        logInCenter.add(username);
        logInCenter.add(new JLabel(("Password")));
        logInCenter.add(password);

        //create & assign elements for logInSouth
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("CANCEL");

        logInSouth.add(ok);
        logInSouth.add(cancel);

        ok.setActionCommand(OK_COMMAND);
        cancel.setActionCommand(CANCEL_COMMAND);

        ActionListener  loginListener = b ->{
            if(b.getActionCommand().equals(OK_COMMAND)){
                System.out.println("VERIFYING DATA");
                name = username.getText();
                pword = String.valueOf(password.getPassword());
                System.out.println(password.getPassword());

                if( name.equals("test")){
                    UI login = new UI();
                    login.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,"Wrong Password / Username");
                    username.setText("");
                    password.setText("");
                    username.requestFocus();
                }
            }else if(b.getActionCommand().equals(CANCEL_COMMAND)){
                System.out.println("CANCELING");
                System.exit(0);
            }
        };

        ok.addActionListener(loginListener);
        cancel.addActionListener(loginListener);

        //create & assign borders
        Border etchedBorder = BorderFactory.createEtchedBorder();
        Border logInBorder = BorderFactory.createTitledBorder(etchedBorder, "Login");
        loginScreen.setBorder(logInBorder);

        //combine Panels
        loginScreen.add(logInCenter, BorderLayout.CENTER);
        loginScreen.add(logInSouth, BorderLayout.SOUTH);

        this.setContentPane(loginScreen);

        // set JFrame behavior
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }

}
