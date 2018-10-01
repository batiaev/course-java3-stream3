package HM_2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private String nick;
    private Channel channel;
    private Thread t;
    private BufferedReader in = null;
    private BufferedWriter out = null;

    private static final int TIME_OUT = 30000;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
        long current = System.currentTimeMillis();
        try {

            // Создается файл для записи истории чата
            out = new BufferedWriter(
                    new FileWriter("chat_history.txt", true)
            );

            in = new BufferedReader(
                    new FileReader("chat_history.txt")
            );

            channel = ChannelBase.of(socket);
            t = new Thread(() -> {

                while (!auth()) {
                    long a = System.currentTimeMillis() - current;
                    if (a >= TIME_OUT) {
                        // После превышения таймаута, сокет закрывается.
                        try {
                            System.out.println("Trying to close socket");
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("socket closed: " + socket.isClosed());
                    }
                }
                // Если авторизация прошла успешно, выполняется блок кода с
                // обработкой сообщений.Если авторизация прошла успешно,
                // выполняется блок кода с обработкой сообщений.
                System.out.println(nick + " handler waiting for new massages");
                while (socket.isConnected()) {
                    Message msg = channel.getMessage();

                    saveChatHistory(msg.toString());

                    if (msg == null) continue;
                    switch (msg.getType()) {
                        case EXIT_COMMAND:
                            server.unsubscribe(this);
                            break;
                        case PRIVATE_MESSAGE:
                            sendPrivateMessage(msg.getBody());
                            break;
                        case RENAME_MESSAGE:
                            rename(msg);
                            break;
                        case BROADCAST_CHAT:
                            server.sendBroadcastMessage(
                                    nick + " : " + msg.getBody()
                            );
                        default:
                            System.out.println("invalid message type");
                    }
                }
            });
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод сохраняет сообщения в файл
    private void saveChatHistory(String msg) {
        Date date = new Date(System.currentTimeMillis());
        String msgFormat = String.format(
                "%s: %s: %s\n", date, nick, msg
        );

        // Заполняем файл для тестирования
        try {
            for (int i = 0; i < 110; i++) {
                out.write("Msg nr: " + i + " from: " + nick + "\n");
            }
            //out.write(msgFormat);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rename(Message msg) {
        String[] str = msg.getBody().split(" ");
        server.getAuthService().rename(nick, str[2]);
        server.sendBroadcastMessage(nick + " renamed to: " + str[2]);
        nick = str[2];
    }

    private void sendPrivateMessage(String messageWithNickTo) {
        int firstSpaceIndex = messageWithNickTo.indexOf(" ");
        final String nickTo = messageWithNickTo.substring(0, firstSpaceIndex);
        final String message = messageWithNickTo.substring(firstSpaceIndex).trim();
        if (server.isNickTaken(nickTo)) {
            server.sendPrivateMessage(nick, nickTo, nick + " -> " + nickTo + " : " + message);
        } else {
            sendMessage(nickTo + " not taken!");
        }
    }

    // Изменения в методе: теперь метод возвращает значение true,
    // если авторизация прошла успешно, и false  в другом случае.
    private boolean auth() {
        while (true) {
            if (!channel.hasNextLine()) continue;
            Message message = channel.getMessage();
            if (MessageType.AUTH_MESSAGE.equals(message.getType())) {
                String[] commands = message.getBody().split(" ");// /auth login1 pass1
                if (commands.length >= 3) {
                    String login = commands[1];
                    String password = commands[2];
                    System.out.println(
                            "Try to login with " + login + " and " + password
                    );

                    String nick = server
                            .getAuthService()
                            .authByLoginAndPassword(login, password);

                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        sendMessage(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        sendMessage(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);

                        loadChatHistory();

                        sendMessage(msg);
                        server.subscribe(this);

                        return true;
                    }
                }
            } else {
                sendMessage("Invalid command!");
            }
            return false;
        }
    }

    // Метод загружает историю чата и выводит на экран пользователя
    private void loadChatHistory() {
        try {
            String msg;
            List<String> lines = new ArrayList<>();

            while ((msg = in.readLine()) != null) {
                lines.add(msg);
            }

            Collections.reverse(lines);
            for (int i = 100; i >= 0; i--) {
                sendMessage(lines.get(i));
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        channel.sendMessage(msg);
    }

    public String getNick() {
        return nick;
    }
}
