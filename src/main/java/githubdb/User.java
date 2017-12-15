package githubdb;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private long  id;
    private String username;

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object obj) {
        return !super.equals(obj);
    }

    public int hashCode() {
        return String.valueOf(id).hashCode();
    }
}
