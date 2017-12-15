package testing;

import githubdb.*;
import org.junit.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

public class UserTest {
    private User userOne;
    private User userTwo;

    @Before
    public void init(){
        userOne = new User();
        userTwo = new User();
        userOne.setUsername("name");
        userOne.setId(1);
        userTwo.setUsername("name");
        userTwo.setId(1);
    }

    @Test
    public void setId() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        long id = 1;
        user.setId(id);
        final Field field = user.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals(field.get(user), id);
    }

    @Test
    public void getId() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("id");
        field.setAccessible(true);
        long id = 2;
        field.set(user, id);
        assertEquals(user.getId(), id);
    }

    @Test
    public void setUsername() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        String name = "name";
        user.setUsername(name);
        final Field field = user.getClass().getDeclaredField("username");
        field.setAccessible(true);
        assertEquals(field.get(user), name);
    }

    @Test
    public void getUsername() throws NoSuchFieldException, IllegalAccessException {
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("username");
        field.setAccessible(true);
        String name = "name";
        field.set(user, name);
        assertEquals(user.getUsername(), name);
    }

    @Test
    public void equalsTest() {
        assertEquals(userOne.equals(userTwo), userTwo.equals(userOne));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(userOne.hashCode(), userTwo.hashCode());
    }

    @Test
    public void toStringTest() {
        assertEquals(userOne.toString(), userTwo.toString());
    }
}
