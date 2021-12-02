package ChatGB.client;


import ChatGB.constants.Constants;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client extends JFrame {

    private JTextField textField;
    JTextArea textArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String login;
    private File dataMessage;
    private BufferedWriter writer;
    private BufferedReader reader;


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
        ExecutorService singleService = Executors.newSingleThreadExecutor();

        singleService.execute(() -> {

                try {
                    while (true) {

                        String messageFromServer = dataInputStream.readUTF();

                        if (messageFromServer.startsWith(login + ": " + Constants.END_COMMAND)) {
                            break;

                        } else if (messageFromServer.startsWith(Constants.AUTH_OK_COMMAND)) {
                            String[] tokens = messageFromServer.split("\\s+");
                            this.login = tokens[1];

                            dataMessage = new File(login + ".txt");
                            if (!dataMessage.exists()) {
                                dataMessage.createNewFile();
                            }
                            writer = new BufferedWriter(new FileWriter(dataMessage, true));

                            readFile();

                            textArea.append("Успешно авторизован как: " + login);
                            textArea.append("\n");
                        }
                        textArea.append(messageFromServer);
                        textArea.append("\n");
                        saveFile(messageFromServer);
                    }

                    sendMessage();
                    System.out.println(login + " разорвал соединение.");
                    closeSaveMessage();
                    closeConnection();
                    System.exit(1);

                }catch (EOFException eofException) {
                    try {
                        closeSaveMessage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    closeConnection();
                    System.exit(1);

                } catch (Exception ex) {
                   ex.printStackTrace();
                }
        });
        singleService.shutdown();

    }

    private void readFile () throws IOException {
        try {
            String str;
            int counterMessage = 0;
            long counterLine = 0L;

            reader = new BufferedReader(new FileReader(dataMessage));

            while (true) {
                if (reader.readLine() != null) {
                    counterLine++;
                } else {
                    reader.close();
                    break;
                }
            }

            reader = new BufferedReader(new FileReader(dataMessage));

                while ((str = reader.readLine()) != null) {
                    counterMessage++;

                    if (counterMessage >= counterLine-100) {

                        if (counterMessage == counterLine-100) {
                            textArea.append("История сообщений: ");
                            textArea.append("\n");
                        }
                        textArea.append(str);
                        textArea.append("\n");
                    }
                }
                reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile (String message) {
        try {
            if (!message.startsWith(Constants.AUTH_OK_COMMAND) && !message.startsWith(Constants.DELETE_MESSAGE)) {
                writer.write(message);
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeSaveMessage () throws IOException {
        reader.close();
        writer.flush();
        writer.close();
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
