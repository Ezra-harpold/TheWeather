package ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.theweather.R


class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainActivityViewModel = ViewModelProviders.of(this).get<MainActivityViewModel>(
            MainActivityViewModel::class.java
        )


        //Coroutines.main {
         //   val forecast = viewModel.forecast.await()
          //  forecast.observe(this, Observer {
           // Toast.makeText(this,it.size.toString(),Toast.LENGTH_LONG).show()
           // })
        //}
    }

}


