package com.example.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by Anand on 25/10/2016.
 */

public class jService extends JobService {

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Toast.makeText(getApplicationContext(), "Service has started", Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters) message.obj, false);
            return true;
        }
    });

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        handler.sendMessage(Message.obtain(handler, 1, jobParameters));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        handler.removeMessages(1);
        return false;
    }
}
