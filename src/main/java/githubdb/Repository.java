package githubdb;

import lombok.*;

import java.util.ArrayList;

@Getter @Setter
public class Repository {
    private int id;
    private String name;
    private User owner;
    private String description;
    private Language language;
    private ArrayList<Contributor> contributors;

    public Repository() {
        contributors = new ArrayList<>();
    }

}
