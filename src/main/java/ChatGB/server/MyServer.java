package ChatGB.server;

import ChatGB.constants.Constants;
import LessonTestAndLog.LoggerApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Логика сервера.
 */

public class MyServer {
    Logger logger = LogManager.getLogger(MyServer.class);

    public AuthService getAuthService() {
        return authService;
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    /**
     * Сервис аутентификации.
     */
    private AuthService authService;

    /**
     * Активные клиенты.
     */
    private List<ClientHandler> clients;

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {

            authService = new DbAuthService();
            authService.start();

            clients = new ArrayList<>();

            while (true) {
                logger.info("Сервер ожидает подключения.");
                Socket socket = server.accept();
                logger.info("Клиент подключился");
                new ClientHandler(this, socket);
            }


        }catch (IOException ex) {
            logger.error("Ошибка в работе сервера.");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) {

        clients.forEach(client -> client.sendMessage(message));
    }

    public synchronized void privBroadcastMessage(String message, String privName, String otpravitel) {

        for (ClientHandler cl : clients) {
            if (cl.getName().equals(privName)) {
                for (ClientHandler cl1 : clients) {
                    if (cl1.getName().equals(otpravitel)) {
                        cl.sendMessage(message);
                        cl1.sendMessage("Вы отправили личное сообщение для " + privName);
                        return;
                    }
                }
            }
        }
        for (ClientHandler cl : clients) {
            System.out.println("Пользователь не найден.");
            if (cl.getName().equals(otpravitel)) {
                cl.sendMessage("Такого пользователя не существует.");
                return;
            }
        }
    }

    public synchronized void subscribe (ClientHandler client) {
        clients.add(client);
    }

    public synchronized void unsubscribe (ClientHandler client) {
        clients.remove(client);
    }

    public synchronized String getActiveClients() {
        StringBuilder sb = new StringBuilder(Constants.CLIENTS_LIST_COMMAND).append(" ");
        sb.append(clients.stream()
                .map(c -> c.getName())
                .collect(Collectors.joining(" "))
        );
        return sb.toString();
    }
}
