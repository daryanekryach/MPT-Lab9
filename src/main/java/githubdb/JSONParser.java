package githubdb;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class JSONParser {
    private DataHandler data;

    public JSONParser(DataHandler data) {
        this.data = data;
    }

    /**
     * Method that parses JSON with repository info from request.
     *
     * @param repoJson - JSON info of repository.
     * @return ArrayList<Repository> with parsed repositories.
     */
    public ArrayList<Repository> parseRepositoryJson(String repoJson) {
        JSONObject object = new JSONObject(repoJson);
        ArrayList<Repository> repositories = new ArrayList<>();
        if (object.has("items")) {
            JSONArray items = object.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                Repository repository = new Repository();
                JSONObject repositoryObject = items.getJSONObject(i);
                repository.setId(repositoryObject.getLong("id"));
                repository.setName(repositoryObject.getString("name"));
                if (!(repositoryObject.get("description") instanceof String)) {
                    repository.setDescription("none");
                } else repository.setDescription(repositoryObject.getString("description"));
                Language language = new Language();
                if (!(repositoryObject.get("language") instanceof String)) {
                    language.setName("none");
                } else language.setName(repositoryObject.getString("language"));
                data.addToLanguages(language);
                language.setId(data.getLanguageIndex(language));
                repository.setLanguage(language);
                JSONObject ownerObject = repositoryObject.getJSONObject("owner");
                repository.setOwner(parseUserJson(ownerObject));
                repositories.add(repository);
            }
        }
        return repositories;
    }

    /**
     * Method that parses JSON with contribution info from request.
     *
     * @param json - JSON info of contribution.
     * @return ArrayList<Contributor> with parsed contributors.
     */
    public LinkedHashSet<User> parseRepositoryContributionJson(String json) {
        JSONArray object = new JSONArray(json);
        LinkedHashSet<User> contributors = new LinkedHashSet<>();
        for (int i = 0; i < object.length(); i++) {
            JSONObject contributorObject = object.getJSONObject(i);
            contributors.add(parseUserJson(contributorObject));
        }
        return contributors;
    }

    private User parseUserJson(JSONObject userObject) {
        User user = new User();
        user.setUsername(userObject.getString("login"));
        user.setId(userObject.getLong("id"));
        return user;
    }

}
