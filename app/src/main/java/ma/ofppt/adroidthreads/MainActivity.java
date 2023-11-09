package ma.ofppt.adroidthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import ma.ofppt.adroidthreads.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;
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
    }
}