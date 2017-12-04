package githubdb;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException{
    /*PostgreSQL postgre = new PostgreSQL();
    postgre.connectToPostgre();*/

    GitHubAPI github = new GitHubAPI();
    github.getData();
    }
}
