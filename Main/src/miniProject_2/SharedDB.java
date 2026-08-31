package miniProject_2;

import miniProject_1.Job;

import java.util.HashMap;
import java.util.Map;

public class SharedDB {
    Map<Integer, String> jobs = new HashMap<>();

    public Map<Integer, String> getJobs() {
        return jobs;
    }

    public void saveJob(int value){
        //syntax to store processed elements
        jobs.put(value, "COMPLETED");
    }
}
