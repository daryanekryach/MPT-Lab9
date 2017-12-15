package testing;

import githubdb.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;

public class RepositoryTest {
    private User user;
    private Language language;
    @Before
    public void init(){
        user = new User();
        user.setId(1);
        user.setUsername("name");
        language = new Language();
        language.setName("language");
    }

    @Test
    public void setId() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        long id = 1;
        repository.setId(id);
        final Field field = repository.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(repository), id);
    }

    @Test
    public void getId() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("id");
        field.setAccessible(true);
        long id = 1;
        field.set(repository, id);
        assertEquals(repository.getId(), id);
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String name = "test";
        field.set(repository, name);
        assertEquals(repository.getName(), name);
    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        String name = "test";
        repository.setName(name);
        final Field field = repository.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals(field.get(repository), name);
    }

    @Test
    public void getDescription() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("description");
        field.setAccessible(true);
        String description = "test";
        field.set(repository, description);
        assertEquals(repository.getDescription(), description);
    }

    @Test
    public void setDescription() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        String description = "test";
        repository.setDescription(description);
        final Field field = repository.getClass().getDeclaredField("description");
        field.setAccessible(true);
        assertEquals(field.get(repository), description);
    }

    @Test
    public void getLanguage() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("language");
        field.setAccessible(true);
        field.set(repository, language);
        assertEquals(repository.getLanguage(), language);
    }

    @Test
    public void setLanguage() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        repository.setLanguage(language);
        final Field field = repository.getClass().getDeclaredField("language");
        field.setAccessible(true);
        assertEquals(field.get(repository), language);
    }

    @Test
    public void getContributors() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        final Field field = repository.getClass().getDeclaredField("contributors");
        field.setAccessible(true);
        LinkedHashSet<User> contributors = new LinkedHashSet<>();
        contributors.add(user);
        field.set(repository, contributors);
        assertEquals(repository.getContributors(), contributors);
    }

    @Test
    public void setContributors() throws NoSuchFieldException, IllegalAccessException {
        final Repository repository = new Repository();
        LinkedHashSet<User> contributors = new LinkedHashSet<>();
        contributors.add(user);
        repository.setContributors(contributors);
        final Field field = repository.getClass().getDeclaredField("contributors");
        field.setAccessible(true);
        assertEquals(field.get(repository), contributors);
    }

}
