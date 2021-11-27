package ChatGB.client;


import ChatGB.constants.Constants;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client extends JFrame {

    private JTextField textField;
    private JTextArea textArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String login;

    private File dataMessage;
    private DataInputStream dis;
    private DataOutputStream dos;

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
        AtomicBoolean checkHistroty = new AtomicBoolean(true);


        new Thread(() -> {

                try {
                    while (true) {

                        String messageFromServer = dataInputStream.readUTF();

                        if (messageFromServer.startsWith(login + ": " + Constants.END_COMMAND)) {
                            break;

                        } else if (messageFromServer.startsWith(Constants.AUTH_OK_COMMAND)) {
                            String[] tokens = messageFromServer.split("\\s+");
                            this.login = tokens[1];
                            textArea.append("Успешно авторизован как: " + login);
                            textArea.append("\n");

                            dataMessage = new File(login + ".txt");

                            if (!dataMessage.exists()) {
                                dataMessage.createNewFile();
                            }

                            try {
                                dis = new DataInputStream(new FileInputStream(dataMessage));
                                dos = new DataOutputStream(new FileOutputStream(dataMessage));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        if (dis.available() > 0 && checkHistroty.get()) {
                            textArea.append("История: ");
                            textArea.append(dis.readUTF());
                            checkHistroty.set(false);
                        }
                        textArea.append(messageFromServer);
                        textArea.append("\n");

                    }

                    sendMessage();
                    System.out.println(login + " разорвал соединение.");
                    closeSaveMessage();
                    closeConnection();
                    System.exit(1);

                }catch (Exception ex) {
                   ex.printStackTrace();
                }
        }).start();

    }


    public void closeSaveMessage () throws IOException {
        dis.close();
        dos.flush();
        dos.close();
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
        textArea.getAutoscrolls();

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
    }}
