package testing;

import githubdb.DataHandler;
import githubdb.GitHubAPI;
import org.joda.time.DateTime;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GitHubAPITest {

    @Test
    public void getGitHubData(){
        final GitHubAPI github = new GitHubAPI();
        final DateTime date = new DateTime().withDate(2017,9,1);
        assertTrue(github.getGitHubData(date,1));
    }

    @Test
    public void getData() throws NoSuchFieldException, IllegalAccessException {
        final GitHubAPI github = new GitHubAPI();
        DataHandler data = new DataHandler();
        final Field field = github.getClass().getDeclaredField("data");
        field.setAccessible(true);
        field.set(github, data);
        assertEquals(github.getData(), data);
    }

}
