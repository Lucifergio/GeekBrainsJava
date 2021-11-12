package Java2Lesson7.server;

import Java2Lesson7.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Обработчик для конкретного клиента.
 */

public class ClientHandler {

    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private Timer timer;

    public ClientHandler(MyServer server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new  DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authentication();
                    readMessage();

                }catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        closeConnection();
                    } catch (SocketException e) {
                        System.out.println("Соединение закрыто.");
                    }
                }
            }).start();
        }catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика.");
        }
    }


    private void authentication() throws IOException {

//Таймер для ограничения на подключение.
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("У клиента вышло время авторизации. Отключение...");
                try {
                    sendMessage("Вышло время авторизации. Соединение закрыто.");
                    closeConnection();
                }catch (SocketException ex) {
                    System.out.println("Вышло время авторизации. Соединение закрыто.");
                }
            }
        };
        //Запускаем таймер.
        timer = new Timer();
        timer.schedule(task, Constants.TIME);

        while (true) {

            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");
                Optional<String> nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                nick.toString();

                if(nick.isPresent()) {

                    boolean checkReturn = false;
                    for (ClientHandler ch : server.getClients()) {
                        if (ch.getName().equals(nick.get())){
                            sendMessage("Такой пользователь уже авторизован.");
                            checkReturn = true;
                            continue;
                        }
                    }
                    if (checkReturn == true) {
                        continue;
                    }
                    //Авторизовались
                    name = nick.get();
                    sendMessage(Constants.AUTH_OK_COMMAND + " " + nick);
                    server.broadcastMessage(nick + " вошел в чат");
                    server.broadcastMessage(server.getActiveClients() );
                    server.subscribe(this);

                    //Останавливаем таймер.
                    System.out.println("Клиент авторизовался как: " + nick + " Таймер остановлен.");
                    timer.cancel();

                    return;
                } else {
                    sendMessage("Неверный логин/пароль");
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage () throws IOException {

        while (true) {

            String messageFromClient = in.readUTF();
            String[] privMessToken = messageFromClient.split("\\s+");

            if (messageFromClient.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                sendMessage(server.getActiveClients());

            } else if (privMessToken[0].equals(Constants.PRIVATE_MESSAGE)) {
                server.privBroadcastMessage("Личное собщение от " + name + ": " + messageFromClient, privMessToken[1], name);
            } else {
                System.out.println("Сообщение от " + name + ": " + messageFromClient);
                server.broadcastMessage(name + ": " + messageFromClient);
            }

            if (messageFromClient.equals(Constants.END_COMMAND)) {
                break;
            }
        }
    }

    private void closeConnection() throws SocketException {

        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чата");
        try {
            in.close();
        }catch (IOException ex) {
            //Ignore
        }
        try {
            out.close();
        }catch (IOException ex) {
            //Ignore
        }
        try {
            socket.close();
        }catch (IOException ex) {
            //Ignore
        }
    }
}

