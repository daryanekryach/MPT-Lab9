package githubdb;

import lombok.Getter;

import java.util.LinkedHashSet;

@Getter
public class DataHandler {
    private LinkedHashSet<Repository> repositories;
    private LinkedHashSet<Language> languages;
    private LinkedHashSet<User> users;

    public DataHandler() {
        repositories = new LinkedHashSet<>();
        languages = new LinkedHashSet<>();
        users = new LinkedHashSet<>();
    }

    public boolean handleData(Repository repository) {
        repositories.add(repository);
        users.add(repository.getOwner());
        for (User contributor : repository.getContributors()) {
            users.add(contributor);
        }
        return true;
    }

    public boolean repositoryAlreadyExists(Repository repository) {
        return repositories.contains(repository);
    }

    public boolean languageAlreadyExists(Language language) {
        boolean result = false;
        for (Language lang : languages) {
            result = lang.getName().equals(language.getName());
            if (result) break;
        }
        return result;
    }

    public void addToLanguages(Language language) {
        if (!languageAlreadyExists(language)) {
            language.setId(languages.size() + 1);
            languages.add(language);
        }
    }

    public int getLanguageIndex(Language language) {
        int id = 0;
        for (Language lang : languages) {
            if (lang.getName().equals(language.getName())) {
                id = lang.getId();
                break;
            }
        }
        return id;
    }
}
