package githubdb;

import java.io.IOException;
import java.sql.SQLException;

import org.joda.time.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        DateTime date = new DateTime().withDate(2017,9,1);

        System.out.println("STARTING PROCESSING DATA FROM GITHUB...");
        Metrics.start();
        GitHubAPI github = new GitHubAPI();
        github.getGitHubData(date, 1);
        Metrics.stop();
        Metrics.getAllMetrics();
        DataHandler data = github.getData();
        System.out.println("FINISHED PROCESSING DATA FROM GITHUB");

        System.out.println("STARTING INSERTING DATA TO POSTGRESQL...");
        Metrics.start();
        insertData(data);
        Metrics.stop();
        Metrics.getAllMetrics();
        System.out.println("FINISHED INSERTING DATA TO POSTGRESQL");

        System.out.println("STARTING SELECTING DATA FROM POSTGRESQL...");
        Metrics.start();
        executeQueries();
        Metrics.stop();
        Metrics.getAllMetrics();
        System.out.println("FINISHED SELECTING DATA FROM POSTGRESQL...");
    }


    private static void insertData(DataHandler data){
        try(PostgreSQL postgre = new PostgreSQL()) {
            postgre.insertLanguages(data.getLanguages());
            postgre.insertRepositories(data.getRepositories());
            postgre.insertUsers(data.getUsers());
            postgre.insertContributors(data.getRepositories());
            postgre.insertRepositoryOwners(data.getRepositories());
        }
        catch (SQLException e){
            System.out.println("Error occurred while working with database "  + e.getMessage());
        }
    }

    public static boolean executeQueries(){
        try(PostgreSQL postgre = new PostgreSQL()) {
            postgre.getMostPopularLanguages();
            postgre.getReposContributedByUser();
            postgre.getReposOwnedByUser();
            postgre.getMostCommitedRepository();
        }
        catch (SQLException e){
            System.out.println("Error occurred while quering data from database "  + e.getMessage());
        }
        return true;
    }
}
