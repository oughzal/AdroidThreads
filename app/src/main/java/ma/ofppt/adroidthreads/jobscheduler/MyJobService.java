package ma.ofppt.adroidthreads.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("MySchedulerTAG","Started");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("MySchedulerTAG","Stopped");
        return true;
    }
}
