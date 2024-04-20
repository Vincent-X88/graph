import java.util.List;

public class JobSeeker {
    private String name;
    private List<String> skills;
    private List<String> interests;
    private String careerGoal;

    public JobSeeker(String name, List<String> skills, List<String> interests, String careerGoal) {
        this.name = name;
        this.skills = skills;
        this.interests = interests;
        this.careerGoal = careerGoal;
    }

    public String getName() {
        return name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getInterests() {
        return interests;
    }

    public String getCareerGoal() {
        return careerGoal;
    }
}
