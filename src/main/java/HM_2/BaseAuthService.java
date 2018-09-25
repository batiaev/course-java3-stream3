package HM_2;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BaseAuthService implements AuthService {

    private Map<String, User> users = new HashMap<>();

    // Название базы данных
    private static final String DB_PATH = "users.db";
    // URL для получения соединения
    private static final String DB_NAME_URL = "jdbc:sqlite:" + DB_PATH;

    public BaseAuthService() {
        init();
    }

    @Override
    public String authByLoginAndPassword(String login, String password) {

        // Запрос в базу данных для получения nickname из таблицы user
        // где login и password соответствуют аргументам переданным в метод.
        String sql = "SELECT nickname FROM user WHERE login = ? AND password " +
                "= ?";

        System.out.println(sql);
        // Получаем соединение с базой данных.
        try (Connection conn = DriverManager.getConnection(DB_NAME_URL)) {

            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, login);
            stat.setString(2, password);

            // Получаем и возвращаем результат после запроса.
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return null;
    }

    // Создаем и заполняем базу данных.
    private void init() {

        try (Connection conn = DriverManager.getConnection(DB_NAME_URL)) {
            Statement statement = conn.createStatement();

            // Что бы не мусорить во время тестов.
            statement.executeUpdate("DROP TABLE IF EXISTS user");
            statement.executeUpdate(
                    "CREATE TABLE user (login STRING, password STRING, nickname STRING)"
            );

            statement.executeUpdate(
                    "INSERT INTO user VALUES('login1', 'pass1', 'nick1')"
            );
            statement.executeUpdate(
                    "INSERT INTO user VALUES('login2', 'pass2', 'nick2')"
            );
            statement.executeUpdate(
                    "INSERT INTO user VALUES('login3', 'pass3', 'nick3')"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User createOrActivateUser(String login, String password, String nick) {
        User user = new User(login, password, nick);
        if (users.containsKey(nick)) {
            users.get(nick).setActive(true);
            System.out.println("User with nick " + nick + "already exist");
        } else {
            users.put(nick, user);
            persist();
        }
        return user;
    }

    private void persist() {
        //do some logic...
//        new File("users.txt");
    }

    @Override
    public boolean deactivateUser(String nick) {
        User user = users.get(nick);
        if (user != null) {
            user.setActive(false);
            return true;
        }
        return false;
    }
}
