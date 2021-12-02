package ChatGB.server;

import java.sql.*;

public class DbAuthService implements AuthService{

    private static Connection connection;
    private static Statement statement;

    @Override
    public void start() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chatdb1.sqlite");
            statement = connection.createStatement();
            //dropTable();
            //createTable();
            //insertStudentBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Подключение к БД");

    }

    @Override
    public void stop() {

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Отключение от БД");

    }

    public static void createTable() throws SQLException {

        statement.executeUpdate("create table if not exists users (\n" +
                "\n" +
                "    id integer primary key autoincrement not null,\n" +
                "    login text not null,\n" +
                "    pass text not null,\n" +
                "    nick integer\n" +
                ");");
    }

    private static void insertStudentBatch () {

        try (PreparedStatement ps = connection.prepareStatement(
                "insert into users (login, pass, nick)" +
                        " values (?, ?, ?)")) {
            for (int i = 1; i <= 3; i++) {
                ps.setString(1,"login" + i);
                ps.setString(2,"pass" + i);
                ps.setString(3, "nick" + i);
                ps.addBatch();
            }
            ps.executeBatch();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void dropTable() throws SQLException {
        statement.executeUpdate("drop table users");
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login = ? and pass = ?")){
            ps.setString(1,login);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("nick");
            }
 /* Не знаю какой из вариантов лучше.
        try (ResultSet rs = statement.executeQuery("select * from users")){
            while (rs.next()) {
                if (rs.getString("login").equals(login) && rs.getString("pass").equals(pass)) {
                    return rs.getString("nick");
                }
            }
  */
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    protected static void nickChange (String newNick, String yourLogin) {

        try (PreparedStatement ps = connection.prepareStatement("UPDATE users SET nick = ? " +
                "WHERE nick = ?;")) {
           ps.setString(1, newNick);
           ps.setString(2, yourLogin);
           ps.executeUpdate();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
