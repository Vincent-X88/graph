import java.util.List;

public class Business 
    private String name;
    private List<String> requiredSkills;
    private List<String> requiredInterests;
    private String description;

    public Business(String name, List<String> requiredSkills, List<String> requiredInterests, String description) {
        this.name = name;
        this.requiredSkills = requiredSkills;
        this.requiredInterests = requiredInterests;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public List<String> getRequiredInterests() {
        return requiredInterests;
    }

    public String getDescription() {
        return description;
    }
}
