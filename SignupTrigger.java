import javax.swing.*;

public class SignupTrigger {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignupOperations form = new SignupOperations();
            form.setVisible(true);
        });
    }
}
