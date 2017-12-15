package githubdb;

import lombok.*;

import java.util.LinkedHashSet;

@Getter @Setter
public class Repository {
    private long id;
    private String name;
    private User owner;
    private String description;
    private Language language;
    private LinkedHashSet<User> contributors;

    public Repository() {
        contributors = new LinkedHashSet<>();
    }

}
