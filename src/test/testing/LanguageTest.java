package testing;

import githubdb.*;
import org.junit.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

public class LanguageTest {

    @Test
    public void setId() throws NoSuchFieldException, IllegalAccessException {
        final Language language = new Language();
        int id = 1;
        language.setId(id);
        final Field field = language.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(language), id);
    }

    @Test
    public void getId() throws NoSuchFieldException, IllegalAccessException {
        final Language language = new Language();
        final Field field = language.getClass().getDeclaredField("id");
        field.setAccessible(true);
        int id = 2;
        field.set(language, id);
        assertEquals(language.getId(), id);
    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        final Language language = new Language();
        String name = "name";
        language.setName(name);
        final Field field = language.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals(field.get(language), name);
    }

    @Test
    public void getUsername() throws NoSuchFieldException, IllegalAccessException {
        final Language language = new Language();
        final Field field = language.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String name = "name";
        field.set(language, name);
        assertEquals(language.getName(), name);
    }

}
