package miniProject_2;

import java.util.concurrent.Semaphore;

public class SharedDB {
    int processedJobs=0;


    public synchronized void setProcessedJobs(){
        processedJobs++;
    }

    public int getProcessedJobs() {
        return processedJobs;
    }
}
