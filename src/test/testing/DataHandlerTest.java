package testing;

import githubdb.DataHandler;
import githubdb.Language;
import githubdb.Repository;
import githubdb.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;

public class DataHandlerTest {
    @Test
    public void getLanguage() throws NoSuchFieldException, IllegalAccessException {
        final DataHandler data = new DataHandler();
         LinkedHashSet<Repository> repositories = new LinkedHashSet();
         Repository repo = new Repository();
         repo.setName("name-repo");
        repositories.add(repo);
        final Field field = data.getClass().getDeclaredField("repositories");
        field.setAccessible(true);
        field.set(data, repositories);
        assertEquals(data.getRepositories(), repositories);
    }

    @Test
    public void getLanguages() throws NoSuchFieldException, IllegalAccessException {
        final DataHandler data = new DataHandler();
        LinkedHashSet<Language> languages = new LinkedHashSet();
        Language language = new Language();
        language.setName("name");
        languages.add(language);
        final Field field = data.getClass().getDeclaredField("languages");
        field.setAccessible(true);
        field.set(data, languages);
        assertEquals(data.getLanguages(), languages);
    }

    @Test
    public void getUsers() throws NoSuchFieldException, IllegalAccessException {
        final DataHandler data = new DataHandler();
        LinkedHashSet<User> users = new LinkedHashSet();
        User user = new User();
        user.setUsername("name");
        users.add(user);
        final Field field = data.getClass().getDeclaredField("users");
        field.setAccessible(true);
        field.set(data, users);
        assertEquals(data.getUsers(), users);
    }
}
