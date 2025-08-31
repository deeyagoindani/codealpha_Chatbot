import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Chatbot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private Map<String, String> knowledgeBase;

    public Chatbot() {
        // Knowledge Base (Rule-based answers)
        knowledgeBase = new HashMap<>();
        knowledgeBase.put("hi", "Hello! How can I help you today?");
        knowledgeBase.put("hello", "Hi there! What’s up?");
        knowledgeBase.put("how are you", "I’m just a bot, but I’m doing great! How about you?");
        knowledgeBase.put("what is your name", "I am a Java AI Chatbot 🤖");
        knowledgeBase.put("bye", "Goodbye! Have a great day!");

        // GUI Setup
        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                chatArea.append("You: " + userInput + "\n");
                String response = getResponse(userInput);
                chatArea.append("Bot: " + response + "\n\n");
                inputField.setText("");
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
    }

    // NLP + Rule-based Response
    private String getResponse(String userInput) {
        String input = userInput.toLowerCase().trim();

        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        return "I'm not sure I understand. Can you rephrase?";
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Chatbot().setVisible(true);
            }
        });
    }
}
