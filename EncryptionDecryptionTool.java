import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptionDecryptionTool {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Encryption-Decryption Tool");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel inputLabel = new JLabel("Enter your message:");
        JTextArea inputArea = new JTextArea(5, 30);
        JLabel keyLabel = new JLabel("Enter shift key:");
        JTextField keyField = new JTextField(5);
        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        JLabel resultLabel = new JLabel("Result:");
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(inputLabel, gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(inputArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(keyLabel, gbc);
        gbc.gridx = 1;
        panel.add(keyField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(encryptButton, gbc);
        gbc.gridx = 1;
        panel.add(decryptButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(resultLabel, gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(resultArea), gbc);

        // Add panel to the frame
        frame.add(panel);
        frame.setVisible(true);

        // Encryption logic
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputArea.getText();
                String keyText = keyField.getText();

                if (message.isEmpty() || keyText.isEmpty() || !keyText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid message and numeric key.");
                    return;
                }

                int key = Integer.parseInt(keyText);
                String encryptedMessage = encrypt(message, key);
                resultArea.setText(encryptedMessage);
            }
        });

        // Decryption logic
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputArea.getText();
                String keyText = keyField.getText();

                if (message.isEmpty() || keyText.isEmpty() || !keyText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid message and numeric key.");
                    return;
                }

                int key = Integer.parseInt(keyText);
                String decryptedMessage = decrypt(message, key);
                resultArea.setText(decryptedMessage);
            }
        });
    }

    // Caesar Cipher Encryption
    private static String encrypt(String message, int shift) {
        StringBuilder encrypted = new StringBuilder();
        shift = shift % 26; // Ensure shift stays within alphabet bounds

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift) % 26 + base);
            }
            encrypted.append(c);
        }
        return encrypted.toString();
    }

    // Caesar Cipher Decryption
    private static String decrypt(String message, int shift) {
        return encrypt(message, 26 - (shift % 26)); // Decryption is reverse of encryption
    }
}
