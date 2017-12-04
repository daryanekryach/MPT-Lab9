package githubdb;

import lombok.*;

@Getter
@Setter
public class Contributor {
    private Repository repository;
    private User user;
}
