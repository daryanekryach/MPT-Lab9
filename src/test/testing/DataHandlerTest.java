package testing;

import githubdb.*;
import org.junit.*;

import java.util.LinkedHashSet;

import static org.junit.Assert.*;

public class DataHandlerTest {
    private LinkedHashSet<Repository> repositories;
    private LinkedHashSet<Language> languages;
    private LinkedHashSet<User> users;
    private Repository repository;
    private Language language;
    private User user;

    @Before
    public void init() {
        repositories = new LinkedHashSet<>();
        languages = new LinkedHashSet<>();
        users = new LinkedHashSet<>();
        repository = new Repository();
        user = new User();
        language = new Language();
        LinkedHashSet<User> contributors = new LinkedHashSet<>();
        language.setName("language");
        contributors.add(user);
        repository.setId(12);
        repository.setLanguage(language);
        repository.setOwner(user);
        repository.setContributors(contributors);

    }

    /*@Test
    public void handleData() {

    }

    @Test
    public void languageAlreadyExists() {

    }

    @Test
    public void repositoryAlreadyExists() {

    }

    @Test
    public void addToLanguages() {

    }

    @Test
    public void getLanguageIndex() {

    }*/
}
