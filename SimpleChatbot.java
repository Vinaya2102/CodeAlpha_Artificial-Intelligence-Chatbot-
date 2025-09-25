import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class SimpleChatbot extends JFrame implements ActionListener {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private HashMap<String, String> knowledgeBase;

    public SimpleChatbot() {
        // Setup GUI
        setTitle("AI Chatbot");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        inputField.addActionListener(this); // Enter key sends

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Initialize knowledge base
        knowledgeBase = new HashMap<>();
        trainBot();

        appendChat("Bot: Hello! Ask me anything.");
    }

    private void trainBot() {
        // Sample Q&A pairs
        knowledgeBase.put("hi", "Hello! How can I help you?");
        knowledgeBase.put("hello", "Hi there! What can I do for you?");
        knowledgeBase.put("how are you", "I'm a bot, but I'm doing great!");
        knowledgeBase.put("what is your name", "I'm SimpleChatbot, your assistant.");
        knowledgeBase.put("bye", "Goodbye! Have a great day!");
        knowledgeBase.put("help", "You can ask me about Java, programming, or just chat!");
        knowledgeBase.put("java", "Java is a popular programming language used for many applications.");
        knowledgeBase.put("thanks", "You're welcome!");
    }

    private void appendChat(String text) {
        chatArea.append(text + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private String getResponse(String input) {
        input = input.toLowerCase();

        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }

        return "Sorry, I don't understand that. Can you ask something else?";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        appendChat("You: " + userText);
        inputField.setText("");

        String response = getResponse(userText);
        appendChat("Bot: " + response);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleChatbot().setVisible(true);
        });
    }
}
