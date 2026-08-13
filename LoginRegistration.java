import javax.swing.*;
import java.awt.*;

public class LoginRegistration extends JFrame {

    JTextField email, regEmail, dob, contact;
    JPasswordField password, regPassword;
    JPanel panel;

    // Colors
    Color lightBlue = new Color(225, 240, 255);
    Color darkBlue = new Color(25, 70, 120);
    Color buttonBlue = new Color(45, 120, 200);

    LoginRegistration() {

        setTitle("DhivyaMart - Login");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showLogin();

        setVisible(true);
    }

    // ---------------- LOGIN PAGE ----------------
    void showLogin() {

        getContentPane().removeAll();

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 30, 30
                )
        );

        panel.setBackground(lightBlue);

        // DhivyaMart Heading
        JLabel name = new JLabel(
                "DhivyaMart",
                SwingConstants.CENTER
        );

        name.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        name.setForeground(darkBlue);

        // Login Title
        JLabel title = new JLabel(
                "LOGIN",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        title.setForeground(darkBlue);

        // Email
        email = new JTextField();

        email.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(darkBlue),
                        "Email ID"
                )
        );

        email.setBackground(Color.WHITE);
        email.setForeground(Color.BLACK);

        // Password
        password = new JPasswordField();

        password.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(darkBlue),
                        "Password"
                )
        );

        password.setBackground(Color.WHITE);
        password.setForeground(Color.BLACK);

        // Login Button
        JButton login = new JButton("Login");

        login.setBackground(buttonBlue);
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);

        // Registration Button
        JButton register = new JButton(
                "New Registration"
        );

        register.setBackground(buttonBlue);
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);

        // Message
        JLabel message = new JLabel(
                "",
                SwingConstants.CENTER
        );

        // Login Action
        login.addActionListener(e -> {

            if (email.getText().isEmpty()
                    || password.getPassword().length == 0) {

                message.setText(
                        "Invalid Email or Password!"
                );

                message.setForeground(Color.RED);

            } else {

                message.setText(
                        "Login Successful!"
                );

                message.setForeground(
                        new Color(0, 150, 70)
                );
            }
        });

        // Registration Page
        register.addActionListener(
                e -> showRegistration()
        );

        // Add Components
        panel.add(name);
        panel.add(title);
        panel.add(email);
        panel.add(password);
        panel.add(login);
        panel.add(register);
        panel.add(message);

        add(panel);

        revalidate();
        repaint();
    }

    // ---------------- REGISTRATION PAGE ----------------
    void showRegistration() {

        getContentPane().removeAll();

        panel = new JPanel();

        panel.setLayout(
                new GridLayout(9, 1, 8, 8)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30
                )
        );

        panel.setBackground(lightBlue);

        // Registration Title
        JLabel title = new JLabel(
                "NEW REGISTRATION",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        title.setForeground(darkBlue);

        // Email
        regEmail = new JTextField();

        regEmail.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(darkBlue),
                        "Email ID"
                )
        );

        regEmail.setBackground(Color.WHITE);
        regEmail.setForeground(Color.BLACK);

        // Password
        regPassword = new JPasswordField();

        regPassword.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(darkBlue),
                        "Password"
                )
        );

        regPassword.setBackground(Color.WHITE);
        regPassword.setForeground(Color.BLACK);

        // Date of Birth
        dob = new JTextField();

        dob.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(darkBlue),
                        "Date of Birth"
                )
        );

        dob.setBackground(Color.WHITE);
        dob.setForeground(Color.BLACK);

        // Contact Number
        contact = new JTextField();

        contact.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(darkBlue),
                        "Contact No"
                )
        );

        contact.setBackground(Color.WHITE);
        contact.setForeground(Color.BLACK);

        // Register Button
        JButton register = new JButton(
                "Register"
        );

        register.setBackground(buttonBlue);
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);

        // Back Button
        JButton back = new JButton(
                "Back to Login"
        );

        back.setBackground(buttonBlue);
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);

        // Message
        JLabel message = new JLabel(
                "",
                SwingConstants.CENTER
        );

        // Registration Action
        register.addActionListener(e -> {

            String mail = regEmail.getText();
            String pass = new String(
                    regPassword.getPassword()
            );
            String date = dob.getText();
            String phone = contact.getText();

            if (mail.matches(
                    "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"
            )
                    && pass.length() >= 6
                    && !date.isEmpty()
                    && phone.matches("[0-9]{10}")) {

                message.setText(
                        "Registration Successful!"
                );

                message.setForeground(
                        new Color(0, 150, 70)
                );

            } else {

                message.setText(
                        "Invalid Details!"
                );

                message.setForeground(Color.RED);
            }
        });

        // Back to Login
        back.addActionListener(e -> {

            getContentPane().removeAll();

            showLogin();
        });

        // Add Components
        panel.add(title);
        panel.add(regEmail);
        panel.add(regPassword);
        panel.add(dob);
        panel.add(contact);
        panel.add(register);
        panel.add(back);
        panel.add(message);

        add(panel);

        revalidate();
        repaint();
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        new LoginRegistration();
    }
}