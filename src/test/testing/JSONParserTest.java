package testing;

import githubdb.*;
import org.junit.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class JSONParserTest {
    private JSONParser parser;
    private String repositoryJSON;
    private String contributionJSON;
    private DataHandler data;

    @Before
    public void init() {
        data = new DataHandler();
        parser = new JSONParser(data);
        repositoryJSON = contributionJSON = "";
        try (Stream<String> stream = Files.lines(Paths.get("testRepoJSON.txt"))) {
            stream.forEach(line -> repositoryJSON += line);
        } catch (Exception e) {
            System.out.println("Problem occurred while reading your file : " + e);
        }
        try (Stream<String> stream = Files.lines(Paths.get("testContributionJSON.txt"))) {
            stream.forEach(line -> contributionJSON += line);
        } catch (Exception e) {
            System.out.println("Problem occurred while reading your file : " + e);
        }
    }

    @Test
    public void parseRepositoryJsonTest() {
        ArrayList<Repository> repositories = new ArrayList<>();
        Repository repository = new Repository();
        User user = new User();
        Language language = new Language();
        language.setName("test");
        user.setId(1);
        user.setUsername("test");
        repository.setName("test");
        repository.setOwner(user);
        repository.setDescription("test");
        repository.setLanguage(language);
        repositories.add(repository);
        assertEquals(repositories.get(0).getName(), parser.parseRepositoryJson(repositoryJSON).get(0).getName());
    }

    @Test
    public void parseRepositoryContributionJsonTest() {
        LinkedHashSet<User> contributors = new LinkedHashSet<>();
        User contributor = new User();
        contributor.setUsername("test");
        contributor.setId(1);
        contributors.add(contributor);
        assertEquals(contributors.toArray()[0], parser.parseRepositoryContributionJson(contributionJSON)
                .toArray()[0]);
    }
}
