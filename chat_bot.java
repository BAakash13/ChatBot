import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat_bot_demo  {
    private Map<String, String> responses; 
    private String operationType;
    private boolean waitingForCalculation;

    public Chat_bot_demo() {
        responses = new HashMap<>();
        initializeResponses();
        operationType = "";
        waitingForCalculation = false;
    }

    private void initializeResponses() {
        // Greetings
        responses.put("hello", "Hello! How can I help you?");
        responses.put("hi", "Hi! What's up?");
        responses.put("hey", "Hey! How are you doing today?");
        responses.put("good morning", "Good morning! How can I assist you today?");
        responses.put("good afternoon", "Good afternoon! What can I help you with?");
        responses.put("good evening", "Good evening! How can I assist you tonight?");
        responses.put("good night", "Good night! Sleep well!");
        responses.put("goodnight", "Good night! Sleep well!");

        // Introductions
        responses.put("what's your name", "My name is Chatbot, nice to meet you!");
        responses.put("who are you", "I'm a chatbot designed to assist and communicate with users like you.");
        responses.put("what do you do", "I’m here to assist you with information, answer your questions, and even have a bit of fun with you.");

        // Basic Questions
        responses.put("how are you", "I'm doing great, thanks for asking!");
        responses.put("how r u", "I'm doing great, thanks for asking!");
        responses.put("favorite color", "I don't have a favorite color, but I can tell you about different colors!");
        responses.put("favorite food", "I don't have a favorite food, but I can tell you about different types of cuisine!");
        responses.put("weather", "I'm not sure, but I can help you find out!");

        // Jokes and Humor
        responses.put("tell me a joke", "Why couldn't the bicycle stand up by itself? Because it was two-tired!");
        responses.put("make me laugh", "Why don't scientists trust atoms? Because they make up everything!");
        responses.put("be funny", "I’ll try! How do you organize a space party? You planet!");

        // Help and Support
        responses.put("i need help", "I'd be happy to help you! What do you need help with?");
        responses.put("help me", "I'd be happy to help you! What do you need help with?");
        responses.put("help", "I'd be happy to help you! What do you need assistance with?");
        responses.put("stuck", "Don't worry, I'm here to help! What's the problem you're facing?");
        responses.put("i don't understand", "I'm here to help clarify things for you! What don't you understand?");

        // Goodbyes
        responses.put("goodbye", "Goodbye! It was nice chatting with you.");
        responses.put("bye", "Bye! See you later!");
        responses.put("see you later", "See you later! Have a great day!");

        // Default Responses
        responses.put("none of the above", "I'm not sure I understand. Could you please provide more context?");

        // Language Translation
        responses.put("translate hello to Spanish", "Hola");
        responses.put("translate goodbye to French", "Au revoir");
        responses.put("translate thank you to German", "Danke");

        // Word Games and Puzzles
        responses.put("play hangman", "Let's play hangman! I'll think of a word and you try to guess it by suggesting letters.");
        responses.put("play word jumble", "Let's play word jumble! I'll scramble a word and you try to unscramble it.");
        responses.put("play word scramble", "Let's play word scramble! I'll scramble a word and you try to unscramble it.");

        // Additional responses
        responses.put("what's the time", getCurrentTime());
        responses.put("what's the date", getCurrentDate());
        responses.put("time", getCurrentTime());
        responses.put("date", getCurrentDate());
        responses.put("current date", getCurrentDate());
        responses.put("current time", getCurrentTime());

        responses.put("thanks", "You're welcome! If you have more questions, just ask!");
        responses.put("okay", "Got it! What would you like to talk about?");
        responses.put("i love", "That's great to hear! What do you love?");
        responses.put("hm", "Is there something on your mind? I'm here to help!");
        responses.put("oh", "Oh! Is there something you'd like to talk about?");
        responses.put("who is your master", "I was created by a developer named Aakash. He's always improving me to assist you better!");
        responses.put("who is your boss", "I don't have a boss, but I'm here to assist you!");
        responses.put("give me a story", "Once upon a time, in a land far away, there was a chatbot who wanted to learn everything about the world. It met many users and helped them with their questions. And they all lived happily ever after!");

        // Math responses
        responses.put("do you know maths", "Yes, I can help with calculations! What type of calculation do you need: add, subtract, multiply, or divide?");
        responses.put("add", "Great! Please provide the two numbers you'd like to add.");
        responses.put("subtract", "Great! Please provide the two numbers you'd like to subtract.");
        responses.put("multiply", "Great! Please provide the two numbers you'd like to multiply.");
        responses.put("divide", "Great! Please provide the two numbers you'd like to divide.");
        responses.put("you know maths", "Yes, I can help with calculations! What type of calculation do you need: add, subtract, multiply, or divide?");
        responses.put("maths", "Yes, I can help with calculations! What type of calculation do you need: add, subtract, multiply, or divide?");
        
        // Tanglish
        responses.put("saptia", "hmm saptan");
        responses.put("enna panra","summathan irukan , nee?");
        responses.put("aprm","onnum illa");
    }

    private String getCurrentDate() {
        LocalDate date = LocalDate.now();
        return "Today's date is: " + date.toString();
    }

    private String getCurrentTime() {
        LocalTime time = LocalTime.now();
        return "Current time is: " + time.toString();
    }

    public void start() {
        JFrame frame = new JFrame("Chatbot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        JTextField userInput = new JTextField(20);
        JButton sendButton = new JButton("Send");
        inputPanel.add(userInput);
        inputPanel.add(sendButton);
        frame.add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = userInput.getText().trim();
                if (!input.isEmpty()) {
                    chatArea.append("You: " + input + "\n");
                    userInput.setText("");
                    String response = processInput(input);
                    chatArea.append("Chatbot: " + response + "\n");
                }
            }
        });

        userInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick();
            }
        });

        frame.setVisible(true);
        userInput.requestFocus();
    }

    private String processInput(String input) {
        input = input.toLowerCase().trim(); // Normalize input

        // Check for operation type
        if (input.contains("add") || input.contains("subtract") || input.contains("multiply") || input.contains("divide")) {
            operationType = input.contains("add") ? "add" :
                            input.contains("subtract") ? "subtract" :
                            input.contains("multiply") ? "multiply" :
                            "divide"; // Default to divide if none matched
            waitingForCalculation = true;
            return responses.get(operationType);
        }

        // Handle number inputs if waiting for calculation
        if (waitingForCalculation) {
            return handleMathOperation(input);
        }
        if (input.equals("yes")) {
            return "Great! What would you like to talk about?";
        }
        if (input.equals("ok")) {
            return "Got it!";
        }
        if (input.contains("who is your master")) {
            return "I was created by a developer named Aakash. He's always improving me to assist you better!";
        }
        if (input.contains("who is your developer")) {
            return "I was created by a developer named Aakash. He's always improving me to assist you better!";
        }
        if (input.contains("what can you do") || input.contains("what do you know") || input.contains("what are you do") || input.contains("what help do you have") || input.contains("what you do")) {
            return "I’m here to assist you with information, answer your questions, and even have a bit of fun with you.";
        }
        if (input.equals("great")) {
            return "I'm shy!";
        }

        // Handle emotions or expressions
        if (input.contains("happy")) {
            return "I'm glad to hear that! What makes you happy?";
        } else if (input.contains("sad")) {
            return "I'm sorry to hear that. What’s bothering you?";
        } else if (input.contains("love")) {
            return "That's great to hear! What do you love?";
        } else if (input.contains("good")) {
            return "Thank you!";
        } else if (input.contains("story")) {
            return "Once upon a time, in a land far away, there was a chatbot who wanted to learn everything about the world. It met many users and helped them with their questions. And they all lived happily ever after!";
        }
        if (input.contains("sitting")) {
            return "Sitting can be relaxing! Do you like to read or watch something while you sit?";
        } else if (input.contains("lying")) {
            return "Lying down can be nice! Are you taking a break or trying to rest?";
        } else if (input.contains("sleeping")) {
            return "Sleep is important! Are you getting enough rest?";
        } else if (input.contains("eating")) {
            return "Eating is a great way to recharge! What's your favorite meal?";
        }

        // Check for specific phrases and keywords using contains
        for (String key : responses.keySet()) {
            if (input.contains(key)) {
                return responses.get(key);
            }
        }

        return "I'm not sure how to respond to that. Can you provide more context?";
    }

    private String handleMathOperation(String input) {
        // Normalize input by splitting by whitespace and operators
        String[] tokens = input.split("\\s+|(?=[-+*/])|(?<=[-+*/])"); // Split by whitespace and operators

        // Remove empty tokens and convert to array
        List<String> validTokens = Arrays.stream(tokens)
                .filter(token -> !token.isEmpty())
                .collect(Collectors.toList());

        if (validTokens.size() != 3) {
            waitingForCalculation = false;
            return "Please provide exactly two numbers with an operator in between (e.g., '5 + 3').";
        }

        try {
            double num1 = Double.parseDouble(validTokens.get(0));
            double num2 = Double.parseDouble(validTokens.get(2));
            double result;

            switch (validTokens.get(1)) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        waitingForCalculation = false;
                        return "Division by zero is not allowed!";
                    }
                    result = num1 / num2;
                    break;
                default:
                    waitingForCalculation = false;
                    return "I couldn't perform the calculation. Please try again.";
            }

            waitingForCalculation = false;
            String op = operationType; // Store operation type for response
            operationType = ""; // Reset operation type after calculation
            return "The result of " + op + "ing " + num1 + " and " + num2 + " is: " + result;
        } catch (NumberFormatException e) {
            waitingForCalculation = false;
            return "Please enter valid numbers.";
        }
    }

    public static void main(String[] args) {
        Chat_bot_demo chatbot = new Chat_bot_demo();
        chatbot.start();
    }
}

