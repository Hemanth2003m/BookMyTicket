import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;

public class SignupOperations extends JFrame {
    private static final HashMap<String, String> users = new HashMap<>();

    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton signupButton;
    private JComboBox<String> roleComboBox; // ✅ Added dropdown

    public SignupOperations() {
        setTitle("Ticket Booking - Signup");
        setSize(400, 350); // increased height
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // 6 rows instead of 5
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("SansSerif", Font.PLAIN, 16);

        JLabel userLabel = new JLabel("Username:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passLabel = new JLabel("Password:");
        JLabel confirmLabel = new JLabel("Confirm Password:");
        JLabel roleLabel = new JLabel("Select Role:"); // ✅ Label for role

        userLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);
        passLabel.setForeground(Color.WHITE);
        confirmLabel.setForeground(Color.WHITE);
        roleLabel.setForeground(Color.WHITE); // ✅

        userLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        passLabel.setFont(labelFont);
        confirmLabel.setFont(labelFont);
        roleLabel.setFont(labelFont); // ✅

        usernameField = new RoundedTextField(15);
        emailField = new RoundedTextField(15);
        passwordField = new RoundedPasswordField(15);
        confirmPasswordField = new RoundedPasswordField(15);

        // Role dropdown setup ✅
        roleComboBox = new JComboBox<>(new String[]{
                "Admin", "Regular", "Senior Citizen", "Differently-abled"
        });
        roleComboBox.setFont(labelFont);
        roleComboBox.setBackground(Color.DARK_GRAY);
        roleComboBox.setForeground(Color.WHITE);

        JTextField[] fields = {usernameField, emailField, passwordField, confirmPasswordField};
        for (JTextField field : fields) {
            field.setBackground(Color.DARK_GRAY);
            field.setForeground(Color.WHITE);
            field.setCaretColor(Color.WHITE);
            field.setFont(labelFont);
        }

        signupButton = new RoundedButton("Sign Up", 20);
        signupButton.setFont(labelFont);
        signupButton.setBackground(Color.GRAY);
        signupButton.setForeground(Color.BLACK);
        signupButton.setPreferredSize(new Dimension(100, 25));

        // Layout
        panel.add(userLabel); panel.add(usernameField);
        panel.add(emailLabel); panel.add(emailField);
        panel.add(passLabel); panel.add(passwordField);
        panel.add(confirmLabel); panel.add(confirmPasswordField);
        panel.add(roleLabel); panel.add(roleComboBox); // ✅ Add to layout

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        buttonPanel.add(signupButton);

        panel.add(buttonPanel);
        panel.add(new JLabel()); // Empty cell

        add(panel);

        signupButton.addActionListener(e -> handleSignup());
    }

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


    private void handleSignup() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem(); // ✅ Get selected role

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add to DB
        String url = "jdbc:mysql://localhost:3306/application_user_logins"; // update this
        String dbUser = "root"; // update this
        String dbPass = "7776"; // update this

        String sql = "INSERT INTO users (username, email, password, role, created_at) VALUES (?, ?, ?, ?, NOW())";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password); // consider hashing in future
            stmt.setString(4, role); // ✅ Use selected role

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Signup successful and stored in DB!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Signup failed to insert into DB.", "DB Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        users.put(username, password);

        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }
}
