package testing;


import githubdb.PostgreSQL;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PostgreSQLTest  {
 private PostgreSQL postgre;


    @Test
    public void connect() throws NoSuchFieldException, IllegalAccessException{
        postgre = new PostgreSQL();
        final Field field = postgre.getClass().getDeclaredField("connection");
        field.setAccessible(true);
        Connection connection = (Connection)field.get(postgre);
        assertNotNull(connection);
    }

    @Test
    public void queryContributionTest(){
        postgre = new PostgreSQL();
        assertTrue(postgre.getReposContributedByUser());
    }

    @Test
    public void queryOwnedTest(){
        postgre = new PostgreSQL();
        postgre.getReposOwnedByUser();
        assertTrue(postgre.getReposOwnedByUser());
    }

    @Test
    public void queryContributedTest(){
        postgre = new PostgreSQL();
        assertTrue(postgre.getReposContributedByUser());
    }

    @Test
    public void queryLanguagesTest(){
        postgre = new PostgreSQL();
        assertTrue(postgre.getMostPopularLanguages());
    }

}
