package githubdb;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;

import java.io.*;
import java.util.*;

public class GitHubAPI {
    private CloseableHttpClient httpClient;
    private DataHandler data;
    private JSONParser parser;
    private final String TOKEN = System.getenv("GITHUB_TOKEN");

    public GitHubAPI() {
        httpClient = HttpClients.createDefault();
        data = new DataHandler();
        parser = new JSONParser(data);
    }

    /**
     * Method executes request for getting most committed repositories.
     *
     * @return CloseableHttpResponse response
     * @throws IOException
     */
    private void requestRepositories(String dateFrom, String dateTo) {
        try {
            int page = 1;
            URIBuilder URI;
            HttpGet httpGet;
            HttpEntity httpEntity;
            CloseableHttpResponse response;
            ArrayList<Repository> parsedRepositories;
            while (true) {
                if (page == 6) break;
                URI = new URIBuilder()
                        .setScheme("https")
                        .setHost("api.github.com")
                        .setPath("/search/repositories")
                        .setParameter("q", "created:" + dateFrom + ".." + dateTo)
                        .setParameter("page", String.valueOf(page))
                        .setParameter("per_page", "100");
                httpGet = new HttpGet(URI.toString());
                httpGet.addHeader("Authorization", "token " + TOKEN);
                httpGet.addHeader("Accept", "application/vnd.github.cloak-preview+json");
                response = httpClient.execute(httpGet);
                httpEntity = response.getEntity();
                if (httpEntity == null) {
                    System.out.println("Finished searching");
                    break;
                }
                String repositoryData = EntityUtils.toString(httpEntity);
                if ("[]".equals(repositoryData)) break;
                parsedRepositories = parser.parseRepositoryJson(repositoryData);
                for (Repository repo : parsedRepositories) {
                    if (!data.repositoryAlreadyExists(repo)) {
                        repo.setContributors(requestRepositoryCommits(repo));
                        data.handleData(repo);
                    }
                }
                page++;
                response.close();
            }
        } catch (IOException e) {
            System.out.println("Error occurred while searching repositories " + e.getMessage());
        }
    }

    /**
     * Method executes request for getting repository commits.
     *
     * @param repository
     * @return
     * @throws IOException
     */
    private LinkedHashSet<User> requestRepositoryCommits(Repository repository) {
        LinkedHashSet<User> contributors = new LinkedHashSet<>();
        try {
            int page = 1;
            URIBuilder URI;
            HttpGet httpGet;
            HttpEntity httpEntity;
            CloseableHttpResponse response;
            while (true) {
                if (page == 6) break;
                URI = new URIBuilder()
                        .setScheme("https")
                        .setHost("api.github.com")
                        .setPath("/repos/" + repository.getOwner().getUsername() +
                                "/" + repository.getName() + "/contributors")
                        .setParameter("page", String.valueOf(page))
                        .setParameter("per_page", "100");
                httpGet = new HttpGet(URI.toString());
                httpGet.addHeader("Authorization", "token " + TOKEN);
                response = httpClient.execute(httpGet);
                httpEntity = response.getEntity();
                if (httpEntity == null) {
                    System.out.println("Finished searching");
                    break;
                }
                String contributionData = EntityUtils.toString(httpEntity);
                if ("[]".equals(contributionData)) break;
                contributors = parser.parseRepositoryContributionJson(contributionData);
                page++;
                response.close();
                return contributors;
            }
        } catch (IOException e) {
            System.out.println("Error occurred while searching contributons: " + e.getMessage());
        }
        return contributors;
    }

    public boolean getGitHubData(DateTime date, int weekPeriod) {
        DateTime iteratedate = date;
            for (int i = 1; i <= weekPeriod; i++) {
                requestRepositories(iteratedate.toString("y-MM-dd"),
                        iteratedate.plusDays(6).toString("y-MM-dd"));
                iteratedate = iteratedate.plusDays(7);
            }
        return true;
    }

    public DataHandler getData() {
        return data;
    }
}
