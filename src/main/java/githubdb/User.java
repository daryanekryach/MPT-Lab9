package githubdb;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class User {
    private int  id;
    private String username;
    private ArrayList<Repository> ownedRepositories;
}
