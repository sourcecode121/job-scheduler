package com.example.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private JobScheduler jobScheduler;
    private ComponentName jobService;
    private JobInfo.Builder jobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobService = new ComponentName(MainActivity.this, jService.class);

        jobInfo = new JobInfo.Builder(1, jobService);
        jobInfo.setMinimumLatency(10000)
                .setOverrideDeadline(60000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false);

        findViewById(R.id.start_job_scheduler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = jobScheduler.schedule(jobInfo.build());
                if(result == JobScheduler.RESULT_SUCCESS) {
                    Toast.makeText(MainActivity.this, "Job Scheduler has started", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Scheduler has failed to start", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.stop_job_scheduler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobScheduler.cancelAll();
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
