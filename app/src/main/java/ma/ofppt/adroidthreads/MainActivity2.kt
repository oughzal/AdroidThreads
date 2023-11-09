package ma.ofppt.adroidthreads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ma.ofppt.adroidthreads.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRunThread.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main){
                binding.btnRunThread.text = "Main"
            }
        }
        binding.btnRunThread2.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){
                // TODO : changer le Context (Thread)
                withContext(Dispatchers.Main){
                    binding.btnRunThread2.text = "Main"
                }

            }
        }
    }
}