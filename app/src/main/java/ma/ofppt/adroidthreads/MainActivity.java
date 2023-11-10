package ma.ofppt.adroidthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.net.NetworkRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import ma.ofppt.adroidthreads.databinding.ActivityMainBinding;
import ma.ofppt.adroidthreads.jobscheduler.MyJobService;

public class MainActivity extends AppCompatActivity {
    private class MyAsycnTask extends AsyncTask<String,Integer,String>{

        @Override // dans un Thread
        protected String doInBackground(String... strings) {
            setProgress(10);
            return null;
        }

        @Override // avant l'exécution de doInBackGround (UI)
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override // Après l'exécution (UI)
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override // envois la progression (UI)
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }


    ActivityMainBinding binding;
    JobScheduler jobScheduler;
    MyJobService myJobService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRunThread.setOnClickListener(view->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();

                    //TODO : Modifier Main Tread depuis un autre thread
                    binding.btnRunThread.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.btnRunThread.setText(Thread.currentThread().getName());
                        }
                    });
                    // TODO : exécuter sur main Thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.btnRunThread.setText(Thread.currentThread().getName());
                        }
                    });
                   for(int i=1;i<=100;i++){
                       Log.i("myThreadTag",name + ", i="+ i);
                       try {
                           Thread.sleep(1000);
                       } catch (InterruptedException e) {
                           throw new RuntimeException(e);
                       }
                   }


                }
            }).start();
        });

        binding.btnRunThread2.setOnClickListener(view ->{
            MyThread thread = new MyThread();
            thread.start();
        });

        binding.btnScheduler.setOnClickListener(view ->{
            JobInfo.Builder builder = new JobInfo.Builder(1,new ComponentName(getPackageName(), MyJobService.class.getName()))
                    .setPersisted(true) // survie au redemarrage
                    .setPeriodic(30000);// type de réseau

            JobInfo jobInfo = builder.build();
           jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
           jobScheduler.schedule(jobInfo);
        });

        binding.btnAsyncTask.setOnClickListener(view ->{
            MyAsycnTask myAsycnTask = new MyAsycnTask();
            myAsycnTask.execute();
        });
    }
}