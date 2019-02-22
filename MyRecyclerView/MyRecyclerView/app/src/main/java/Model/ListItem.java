package Model;

/**
 * Created by paulodichone on 3/22/17.
 */

public class ListItem {
    private String name;
    private String description;

    public ListItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
