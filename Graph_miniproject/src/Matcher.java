import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Matcher {
    private Map<JobSeeker, List<Business>> matches;

    public Matcher() {
        this.matches = new HashMap<>();
    }

    public void addMatch(JobSeeker jobSeeker, Business business) {
        if (matches.containsKey(jobSeeker)) {
            matches.get(jobSeeker).add(business);
        } else {
            List<Business> businesses = new ArrayList<>();
            businesses.add(business);
            matches.put(jobSeeker, businesses);
        }
    }

    public List<Business> getMatches(JobSeeker jobSeeker) {
        return matches.get(jobSeeker);
    }
}
