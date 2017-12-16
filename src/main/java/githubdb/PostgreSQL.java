package githubdb;

import java.sql.*;
import java.util.LinkedHashSet;

public class PostgreSQL implements AutoCloseable {
    private final String NAME = "postgres";
    private final String PASSWORD = "12345678";
    private final String URL = "jdbc:postgresql://localhost/GitHubData";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PostgreSQL() {
        connectToPostgre();
    }

    private void connectToPostgre() {
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error occurred while connecting to database " + e.getMessage());
        }
    }

    public void close() throws SQLException {
        connection.close();
    }

    //region Insertion region
    public void insertRepositories(LinkedHashSet<Repository> repositories) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO repositories(repo_id, name, description, lang_id) VALUES(?,?,?,?)");
            for (Repository repo : repositories) {
                preparedStatement.setLong(1, repo.getId());
                preparedStatement.setString(2, repo.getName());
                preparedStatement.setString(3, repo.getDescription());
                preparedStatement.setInt(4, repo.getLanguage().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while inserting data to 'repositories' table " + e.getMessage());
        }
    }

    public void insertUsers(LinkedHashSet<User> users) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(user_id, login) VALUES(?,?)");
            for (User user : users) {
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while inserting data to 'users' table " + e.getMessage());
        }
    }

    public void insertContributors(LinkedHashSet<Repository> repositories) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO contributors(repo_id, contributor_id) VALUES(?,?)");
            for (Repository repo : repositories) {
                for (User user : repo.getContributors()) {
                    preparedStatement.setLong(1, repo.getId());
                    preparedStatement.setLong(2, user.getId());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while inserting data to 'contributors' table " + e.getMessage());
        }
    }

    public void insertLanguages(LinkedHashSet<Language> languages) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO languages(lang_id,language) VALUES(?,?)");
            for (Language lang : languages) {
                preparedStatement.setInt(1, lang.getId());
                preparedStatement.setString(2, lang.getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while inserting data to 'languages' table " + e.getMessage());
        }

    }

    public void insertRepositoryOwners(LinkedHashSet<Repository> repositories) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO repository_owners(repo_id, owner_id) VALUES(?,?)");
            for (Repository repo : repositories) {
                preparedStatement.setLong(1, repo.getId());
                preparedStatement.setLong(2, repo.getOwner().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while inserting data to 'repository_owners' table " + e.getMessage());
        }
    }
    //endregion

    public void getReposContributedByUser() {
        try {
            String query = "SELECT users.login, count(contributors.repo_id) FROM (contributors " +
                    "INNER JOIN users ON contributors.contributor_id = users.user_id)" +
                    "GROUP BY users.login " +
                    "ORDER BY COUNT(contributors.repo_id) DESC " +
                    "LIMIT 10;";

            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("USERS WHO CONTRIBUTED THE MOST");
            int i = 0;
            while (rs.next()) {
                System.out.println("" + (i + 1) + ". " + rs.getString("login") + " - " +
                        rs.getInt("count"));
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selectin data from database " + e.getMessage());
        }
    }

    public void getReposOwnedByUser() {
        try {
            String query = "SELECT users.login,count(repositories.name) FROM (repository_owners " +
                    "INNER JOIN users ON repository_owners.owner_id = users.user_id)" +
                    "INNER JOIN repositories ON repository_owners.repo_id = repositories.repo_id " +
                    "GROUP BY users.login\n" +
                    "ORDER BY COUNT(repositories.name) DESC " +
                    "LIMIT 10";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("USERS WHO OWN THE MOST REPOSITORIES");
            int i = 0;
            while (rs.next()) {
                System.out.println("" + (i + 1) + ". " + rs.getString("login") + " - " +
                        rs.getInt("count"));
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selectin data from database " + e.getMessage());
        }
    }

    public void getMostCommitedRepository() {
        try {
            String query = "SELECT repositories.name, count(repositories.name) FROM contributors " +
                    "INNER JOIN repositories ON contributors.repo_id = repositories.repo_id " +
                    "GROUP BY repositories.name " +
                    "ORDER BY COUNT(repositories.name) DESC " +
                    "LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("THE MOST COMMITTED REPOSITORY IS " + rs.getString("name") + " WITH " +
                        rs.getInt("count") + " COMMITS");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selectin data from database " + e.getMessage());
        }
    }

    public void getMostPopularLanguages() {
        try {
            String query = "SELECT * FROM (SELECT DISTINCT ON (languages.language)  languages.language, " +
                    "count(languages.language) FROM repositories " +
                    "INNER JOIN languages ON repositories.lang_id = languages.lang_id " +
                    "GROUP BY languages.language) langs " +
                    "ORDER BY COUNT(langs) DESC " +
                    "LIMIT 10";
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("MOST POPULAR LANGUAGES");
            int i = 0;
            while (rs.next()) {
                System.out.println("" + (i + 1) + ". " + rs.getString("language") + " - " +
                        rs.getInt("count"));
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while selectin data from database " + e.getMessage());
        }
    }

}
