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
    JFrame jFrame;
    byte counterPopUpAuth;
    boolean checkReadFile;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String login;
    private File dataMessage;
    private BufferedWriter writer;
    private BufferedReader reader;

    /**
     *  Освновная логика клиента.
     */


    public Client() {

        try {
            openConnection();
            popUpAuth();
            prepareUI();
        }catch (IOException e) {
            e.printStackTrace();
        }
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

                        } else if (messageFromServer.startsWith("Неверный")) {
                            JOptionPane.showMessageDialog(jFrame, "Неверный логин или пароль");
                            popUpAuth();
                            continue;

                        } else if (messageFromServer.startsWith(Constants.AUTH_OK_COMMAND)) {
                            String[] tokens = messageFromServer.split("\\s+");
                            this.login = tokens[1];
                            JOptionPane.showMessageDialog(jFrame, "Добро пожаловать: " + login);

                        } else if (messageFromServer.startsWith(login + " сменил ник")) {
                            String[] newNick = messageFromServer.split("\\s+");
                            this.login = newNick[5];
                        }
                        dataMessage = new File(login + ".txt");
                        if (!dataMessage.exists()) {
                            dataMessage.createNewFile();
                        }

                        writer = new BufferedWriter(new FileWriter(dataMessage, true));

                        if (checkReadFile == false) {
                            checkReadFile = true;
                            readFile();
                        }

                        textArea.append(messageFromServer);
                        textArea.append("\n");

                        if (messageFromServer != null) {
                            saveFile(messageFromServer);
                        }
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

    /**
     * Работа с файлами.
     *
     */

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

    /**
     * Закрыть программу
     * и потоки.
     */

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

    /**
     * Отправка сообщений
     */

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

    /**
     * Пользовательский
     * интерфейс.
     */

    private void popUpAuth () throws IOException {
        jFrame = new JFrame();

        while (true) {
            String getLogin = JOptionPane.showInputDialog(jFrame, "Логин");
            String getPass = JOptionPane.showInputDialog(jFrame, "Пароль");
            counterPopUpAuth++;

            if (counterPopUpAuth > 3) {
                JOptionPane.showMessageDialog(jFrame, "Превышено количество попыток.");
                closeConnection();
                System.exit(1);
            }
            else if (getLogin == null || getPass == null) {
                JOptionPane.showMessageDialog(jFrame, "Неверный логин или пароль");
                continue;
            }else {
                dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " " + getLogin + " " + getPass);
                break;
            }

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

        button.addActionListener(e -> sendMessage());

        textField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client());
    }}
