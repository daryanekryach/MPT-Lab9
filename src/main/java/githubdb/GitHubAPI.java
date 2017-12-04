package githubdb;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.*;

public class GitHubAPI {
    private CloseableHttpClient httpclient;
    private ArrayList<Repository> repositories;
    private JSONParser parser;
    private final String TOKEN = System.getenv("GITHUB_TOKEN");

    public GitHubAPI() {
        httpclient = HttpClients.createDefault();
        repositories = new ArrayList<>();
        parser = new JSONParser();
    }

    /**
     * Method executes request for getting most committed repositories.
     *
     * @return CloseableHttpResponse response
     * @throws IOException
     */
    private CloseableHttpResponse requestRepositories(String dateFrom, String dateTo) throws IOException {
        URIBuilder URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.github.com")
                .setPath("/search/repositories")
                .setParameter("q", "created:" + dateFrom + ".." + dateTo);
        HttpGet httpGet = new HttpGet(URI.toString());
        httpGet.addHeader("Authorization", "token " + TOKEN);
        httpGet.addHeader("Accept", "application/vnd.github.cloak-preview+json");
        return httpclient.execute(httpGet);
    }

    /**
     * Method executes request for getting repository commits.
     *
     * @param repository
     * @return
     * @throws IOException
     */
    private CloseableHttpResponse requestRepositoryCommits(Repository repository)
            throws IOException {
        URIBuilder URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.github.com")
                .setPath("/repos/" + repository.getOwner().getUsername() + "/" + repository.getName() + "/contributors")
                .addParameter("per_page", "100");
        HttpGet httpGet = new HttpGet(URI.toString());
        httpGet.addHeader("Authorization", "token " + TOKEN);
        return httpclient.execute(httpGet);
    }

    /**
     * Method that reads response from the most committed request into Repository instance.
     *
     * @param response - request response
     * @return ArrayList<Repository> with parsed repositories
     * @throws IOException
     */
    private ArrayList<Repository> getMostCommitted(CloseableHttpResponse response) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(response
                    .getEntity().getContent()));
            String repositoryData = br.readLine();
            ArrayList<Repository> allRepos = parser.parseRepositoryJson(repositoryData);
            for (int i = 0; i < allRepos.size(); i++) {
                getRepositoryCommits(requestRepositoryCommits(allRepos.get(i)));
                allRepos.get(i).
                        setContributors(getRepositoryCommits(requestRepositoryCommits(allRepos.get(i))));
            }
        } finally {
            response.close();
        }
        return repositories;
    }

    /**
     * Method that reads the response from request into Contributor instance.
     *
     * @param response - request response
     * @return ArrayList<Contributor> with parsed contributors.
     * @throws IOException
     */
    private ArrayList<Contributor> getRepositoryCommits(CloseableHttpResponse response)
            throws IOException {
        ArrayList<Contributor> contributors;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(response
                    .getEntity().getContent()));
            String data = br.readLine();
            contributors = parser.parseRepositoryContributionJson(data);
        } finally {
            response.close();
        }
        return contributors;
    }

    public ArrayList<Repository> getData() throws IOException {
        getMostCommitted(requestRepositories("2017-09-01", "2017-09-07"));
        getMostCommitted(requestRepositories("2017-09-07", "2017-09-14"));
        getMostCommitted(requestRepositories("2017-09-15", "2017-09-21"));
        getMostCommitted(requestRepositories("2017-09-22", "2017-09-28"));
        getMostCommitted(requestRepositories("2017-09-29", "2017-10-05"));
        getMostCommitted(requestRepositories("2017-10-06", "2017-10-12"));
        getMostCommitted(requestRepositories("2017-10-13", "2017-10-19"));
        getMostCommitted(requestRepositories("2017-10-20", "2017-10-26"));
        return repositories;
    }
}
