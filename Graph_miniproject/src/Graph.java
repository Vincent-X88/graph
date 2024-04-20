import java.util.List;

public class Graph {
    private List<JobSeeker> jobSeekers;
    private List<Business> businesses;
    private Matcher matcher;

    public Graph(List<JobSeeker> jobSeekers, List<Business> businesses) {
        this.jobSeekers = jobSeekers;
        this.businesses = businesses;
        this.matcher = new Matcher();
        createEdges();
    }

    private void createEdges() {
        for (JobSeeker jobSeeker : jobSeekers) {
            for (Business business : businesses) {
                boolean match = true;
                for (String skill : business.getRequiredSkills()) {
                    if (!jobSeeker.getSkills().contains(skill)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (String interest : business.getRequiredInterests()) {
                        if (!jobSeeker.getInterests().contains(interest)) {
                            match = false;
                            break;
                        }
                    }
                }
                if (match) {
                    matcher.addMatch(jobSeeker, business);
                }
            }
        }
    }
}
