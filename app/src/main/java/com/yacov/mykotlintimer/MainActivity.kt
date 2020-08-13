package com.yacov.mykotlintimer

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var number = 0
    var handler: Handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        object : CountDownTimer(10000, 1000){
            override fun onFinish() {
                Toast.makeText(applicationContext, "Time's OFF", Toast.LENGTH_LONG).show()
                textViewID.text = "Left 0 second"
            }

            override fun onTick(p0: Long) {

                textViewID.text = "Left: ${p0 / 1000} seconds"

            }

        }.start()


    }

    fun start(view: View) {
//        number = 0

        runnable = object : Runnable {
            override fun run() {
                textViewID2.text = "Time ${number}"
                number++
                textViewID2.text = "Time ${number}"
                handler.postDelayed(runnable, 1000)
            }
        }

        handler.post(runnable)
    }


    fun reset(view: View) {
        val alert = AlertDialog.Builder(this)

        alert.setTitle("Reset")
        alert.setMessage("Are you sure? ")
        alert.setPositiveButton("Yes"){dialogInterface: DialogInterface?, i: Int ->
            handler.removeCallbacks(runnable)
            number = 0

            textViewID2.text = "Time $number"

            Toast.makeText(applicationContext, "Reseted", Toast.LENGTH_LONG).show()
        }

        alert.setNegativeButton("No"){dialogInterface: DialogInterface?, i: Int ->
            handler.removeCallbacks(runnable)
            textViewID2.text = "Time $number"

            Toast.makeText(applicationContext, "Paused", Toast.LENGTH_LONG).show()
        }
        alert.show()

    }
}