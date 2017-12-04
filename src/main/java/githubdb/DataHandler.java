package githubdb;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHandler {
/*    private HashMap<Integer, Repository> repositories;
    private HashMap<Integer, String> languages;
    private HashMap<Integer, String> users;
    private HashMap<Integer, Repository> repositories_owners;
    private HashMap<Integer, Contributor> contributors;

    public DataHandler() {
        repositories = new HashMap<>();
        languages = new HashMap<>();
        users = new HashMap<>();
        repositories_owners = new HashMap<>();
        contributors = new HashMap<>();
    }

    public void handleData(ArrayList<Repository> repositoriesArray) {
        for (Repository repo : repositoriesArray) {
            addToRepositories(repo);
            addToContributors(repo.getContributors());
            addToLanguages(repo.getLanguage());
            addToRepositoryOwners();
            addToUsers(repo.getOwner());
        }
    }

    private void addToRepositories(Repository repository) {
        if (!repositories.containsValue(repository)) {
            repositories.put(repositories.size() + 1, repository);
        }
    }

    private void addToLanguages(String language) {
        if (!languages.containsValue(language)) {
            languages.put(languages.size() + 1, language);
        }
    }

    private void addToUsers(String user) {
        if (!users.containsValue(user)) {
            users.put(languages.size() + 1, user);
        }
    }

    private void addToContributors(ArrayList<Contributor> contributors) {
        for (Contributor user : contributors ) {
            addToUsers(user.);
            if (!users.containsValue(user)) {
                users.put(languages.size() + 1, user);
            }
        }
        addToUsers(user);
        if (!users.containsValue(user)) {
            users.put(languages.size() + 1, user);
        }
    }

    private void addToRepositoryOwners() {

    }*/
}
