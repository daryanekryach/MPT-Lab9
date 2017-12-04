package githubdb;

import java.sql.*;
import java.util.ArrayList;

public class PostgreSQL {
    private final String dbURL = "jdbc:postgresql://localhost/GitHubData?user=postgres&password=12345678";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PostgreSQL() {
        connection = null;
        preparedStatement = null;
    }

    public void connectToPostgre() throws SQLException {
        connection = DriverManager.getConnection(dbURL);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void insertDataToDB(ArrayList<Repository> repositories) throws SQLException {
        insertIntoRepositories();
        insertIntoLanguages();
        insertIntoUsers();
        insertIntoContributors();
        insertIntoRepositoryOwners();
    }

    private void insertIntoRepositories() throws SQLException {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO repositories(repo_id, name, description, language_id) values(?,?,?,?)");
        preparedStatement.executeUpdate();
    }

    private void insertIntoUsers() throws SQLException {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO users(id, login) values(?,?)");
        preparedStatement.executeUpdate();
    }

    private void insertIntoContributors() throws SQLException {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO contributors(repo_id, user_id) values(?,?)");
        preparedStatement.executeUpdate();
    }

    private void insertIntoLanguages() throws SQLException {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO languages(id,language) values(?,?)");
        preparedStatement.executeUpdate();
    }

    private void insertIntoRepositoryOwners() throws SQLException {
        preparedStatement = connection.prepareStatement(
                "INSERT INTO repository_owners(repo_id, user_id) values(?,?)");
        preparedStatement.executeUpdate();
    }
}
