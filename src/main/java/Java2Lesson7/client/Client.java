package Java2Lesson7.client;


import Java2Lesson7.constants.Constants;
import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {

    private JTextField textField;
    private JTextArea textArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String login;

    public Client() {

        try {
            openConnection();
        }catch (IOException e) {
            e.printStackTrace();
        }
        prepareUI();

    }

    private void openConnection () throws IOException {

        socket = new Socket(Constants.SERVER_ADDRESS, Constants.SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());



        new Thread(() -> {

                try {
                    while (true) {

                        String messageFromServer = dataInputStream.readUTF();
                        System.out.println(messageFromServer);

                        if (messageFromServer.startsWith(login + ": " + Constants.END_COMMAND)) {
                            textField.setEnabled(false);
                            break;
                        } else if (messageFromServer.startsWith(Constants.AUTH_OK_COMMAND)) {
                            String[] tokens = messageFromServer.split("\\s+");
                            this.login = tokens[1];
                            textArea.append("Успешно авторизован как: " + login);
                            textArea.append("\n");
                        }
                        System.out.println(messageFromServer);
                        textArea.append(messageFromServer);
                        textArea.append("\n");
                    }

                    textArea.append("Соединение разорвано");
                    textField.setEnabled(false);
                    sendMessage();
                    System.out.println(login + " разорвал соединение.");
                    closeConnection();
                    System.exit(1);

                }catch (Exception ex) {
                   ex.printStackTrace();
                }
        }).start();

    }

    private void closeConnection() {
        try {
            dataOutputStream.close();

        }catch (Exception ex) {
        }

        try {
            dataInputStream.close();

        }catch (Exception ex) {
        }

        try {
            socket.close();
        }catch (Exception ex) {
        }

    }

    private void sendMessage() {
        if (textField.getText().trim().isEmpty()) {
            return;
        }
        try {
            dataOutputStream.writeUTF(textField.getText());
            textField.setText("");
            textField.grabFocus();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void prepareUI () {

        setBounds(200, 200, 500, 500);
        setTitle("Chat client");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        JButton button = new JButton("Send");
        panel.add(button, BorderLayout.EAST);
        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        /**
         * Панель для ввода логина/пароля.
         */
        JPanel loginPanel = new JPanel(new BorderLayout());
        JTextField loginField = new JTextField();
        loginPanel.add(loginField, BorderLayout.WEST);
        loginField.setPreferredSize(new Dimension(200, 25));
        JTextField passField = new JTextField();
        loginPanel.add(passField, BorderLayout.CENTER);
        JButton authButton = new JButton("Авторизация");
        loginPanel.add(authButton, BorderLayout.EAST);
        add(loginPanel, BorderLayout.NORTH);

        authButton.addActionListener(e -> {
            try {
                dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " " + loginField.getText() + " " + passField.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        button.addActionListener(e -> sendMessage());

        textField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client());
    }


}
