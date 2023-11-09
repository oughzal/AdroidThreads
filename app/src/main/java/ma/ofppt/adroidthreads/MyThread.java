package ma.ofppt.adroidthreads;

import android.util.Log;

public class MyThread extends  Thread {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 1; i <= 100; i++) {
            Log.i("myThreadTag", name + ", i=" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
