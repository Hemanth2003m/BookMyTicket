package src;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginOperations extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginOperations() {
        setTitle("Ticket Booking - Login");
        setSize(400, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("SansSerif", Font.PLAIN, 16);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        usernameField = new RoundedTextField(15);
        passwordField = new RoundedPasswordField(15);

        JTextField[] fields = {usernameField, passwordField};
        for (JTextField field : fields) {
            field.setBackground(Color.DARK_GRAY);
            field.setForeground(Color.WHITE);
            field.setCaretColor(Color.WHITE);
            field.setFont(labelFont);
        }

        loginButton = new RoundedButton("Login", 20);
        loginButton.setFont(labelFont);
        loginButton.setBackground(Color.GRAY);
        loginButton.setForeground(Color.BLACK);
        loginButton.setPreferredSize(new Dimension(100, 25));

        panel.add(usernameLabel); panel.add(usernameField);
        panel.add(passwordLabel); panel.add(passwordField);
        panel.add(new JLabel()); panel.add(loginButton);

        add(panel);
        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Both fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String url = "jdbc:mysql://localhost:3306/application_user_logins";
        String dbUser = "root";
        String dbPass = "7776";
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            // ✅ Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (
                    Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
                    PreparedStatement stmt = conn.prepareStatement(sql)
            ) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close login window
                    // TODO: Open dashboard or home page here
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }

            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "MySQL JDBC Driver not found!", "Driver Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Custom UI Components

    class RoundedTextField extends JTextField {
        private int radius;

        public RoundedTextField(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        }
    }

    class RoundedPasswordField extends JPasswordField {
        private int radius;

        public RoundedPasswordField(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        }
    }

    class RoundedButton extends JButton {
        private int radius;

        public RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setOpaque(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginOperations().setVisible(true));
    }
}
