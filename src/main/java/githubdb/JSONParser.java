package githubdb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
/**
     * Method that parses JSON with repository info from request.
     * @param repoJson - JSON info of repository.
     * @return ArrayList<Repository> with parsed repositories.
     */

    public ArrayList<Repository> parseRepositoryJson(String repoJson) {
        JSONObject object = new JSONObject(repoJson);
        JSONArray items = object.getJSONArray("items");
        ArrayList<Repository> repositories = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            Repository repository = new Repository();
            JSONObject repositoryObject = items.getJSONObject(i);
            repository.setId(repositoryObject.getInt("id"));
            repository.setName(repositoryObject.getString("name"));
            if (!(repositoryObject.get("description") instanceof String)) {
                repository.setDescription("none");
            }
            else repository.setDescription(repositoryObject.getString("description"));
            Language language = new Language();
            if (!(repositoryObject.get("language") instanceof String)) {
                language.setName("none");
            }
            else language.setName(repositoryObject.getString("language"));
            repository.setLanguage(language);
            JSONObject ownerObject = repositoryObject.getJSONObject("owner");
            User user = new User();
            user.setUsername(ownerObject.getString("login"));
            user.setId(ownerObject.getInt("id"));
            repository.setOwner(user);
            repositories.add(repository);
        }
        return repositories;
    }

/**
     * Method that parses JSON with contribution info from request.
     * @param json - JSON info of contribution.
     * @return ArrayList<Contributor> with parsed contributors.
     */

    public ArrayList<Contributor> parseRepositoryContributionJson(String json) {
        JSONArray object = new JSONArray(json);
        ArrayList<Contributor> contributors = new ArrayList<>();
        for (int i = 0; i < object.length(); i++) {
            Contributor contributor = new Contributor();
            JSONObject contributionObject = object.getJSONObject(i);
            User user = new User();
            user.setUsername(contributionObject.getString("login"));
            user.setId(contributionObject.getInt("id"));
            contributor.setUser(user);
            contributors.add(contributor);
        }
        return contributors;
    }


}
