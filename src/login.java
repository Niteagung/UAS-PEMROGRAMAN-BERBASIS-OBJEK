import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {
    private JPanel login;
    private JTextField usernamefld;
    private JButton loginButton;
    private JPanel inputLogin;
    private JPasswordField passwordfld;
    private JPanel username;



    public login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String userName = "operator";
                String password = "123";
            if(usernamefld.getText().equals(userName)&& passwordfld.getText().equals(password)){
                JOptionPane.showMessageDialog(null, "Login Berhasil");
                dashboard.main(null);
                login.setVisible(true);

            }else{
                JOptionPane.showMessageDialog(null, "Username/Password Salah");
            }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}



