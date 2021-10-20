package com.strides.soft.mvvmretrofitkotlinsamplebyak

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.strides.soft.apptunixassignmentkotlin.Models.BreedModel2
import com.strides.soft.apptunixassignmentkotlin.ViewModels.TimerViewModel
import com.strides.soft.mvvmretrofitkotlinsamplebyak.databinding.ActivityTimerStatBinding
import java.lang.String

class TimerStatActivity : AppCompatActivity() {
    //Databinding JetPack Component
    lateinit var binding: ActivityTimerStatBinding;

    //ViewModel JetPack Component
    private var timerViewModel: TimerViewModel? = null

    //Declare a variable to hold count down timer's paused status
    private var isPaused = false

    //Declare a variable to hold count down timer's paused status
    private var isCanceled = false

    //Declare a variable to hold CountDownTimer remaining time
    private var timeRemaining: Long = 0

    //Progressbar
    var mProgressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_timer_stat);

        binding.llTimerStartLay.visibility = View.VISIBLE
        binding.llTimerCompleteLay.visibility = View.GONE
        binding.llImagedisplay.visibility = View.GONE

        var hours = 0
        var minutes = 0
        var seconds = 0
        val intent = intent
        hours = intent.getStringExtra("Hourse")!!.toInt()
        minutes = intent.getStringExtra("Minutes")!!.toInt()
        seconds = intent.getStringExtra("Seconds")!!.toInt()
        Log.d("AK", seconds.toString())
        binding.tvHours.text = "" + hours
        binding.tvMint.text = "" + minutes
        binding.tvSec.text = "" + seconds

        var minutes2 = 0
        var totalMinutes2 = 0
        var second2 = 0
        var totalSecond2 = 0
        minutes2 = hours * 60
        totalMinutes2 = minutes2 + minutes
        second2 = totalMinutes2 * 60
        totalSecond2 = second2 + seconds
        Log.d("AK", totalSecond2.toString())
        timeRemaining = (totalSecond2 * 1000).toLong()

        binding.btnStart.setOnClickListener {

            isPaused = false
            isCanceled = false
            //Disable Start button
            //Disable Start button
            binding.btnStart.isEnabled = false
            binding.btnStop.isEnabled = true
            binding.btnStart.setBackgroundColor(binding.btnStart.context.resources.getColor(R.color.gray))
            binding.btnStop.setBackgroundColor(binding.btnStart.context.resources.getColor(R.color.blue))
            val countDownInterval: Long = 1000 //1 second


            //Initialize a new CountDownTimer instance
            //Initialize a new CountDownTimer instance
            object : CountDownTimer(timeRemaining, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    //do something in every tick
                    if (isPaused || isCanceled) {
                        //If the user request to paused the
                        //CountDownTimer we will paused the current instance
                        cancel()
                    } else {
                        //Display the remaining seconds to app interface
                        //1 second = 1000 milliseconds
                        Log.d("Ak_Complete", "" + millisUntilFinished / 1000)
                        //Put count down timer remaining time in a variable
                        timeRemaining = millisUntilFinished
                        Log.d("Ak_Remaining", timeRemaining.toString())
                        var seconds: Long = 0
                        var minutes: Long = 0
                        var hours: Long = 0
                        //Here we will convert time
                        try {
                            seconds = timeRemaining / 1000 % 60
                            minutes = timeRemaining / (1000 * 60) % 60
                            hours = timeRemaining / (1000 * 60 * 60)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                        //Here we will bind textview
                        binding.tvHours.text = "" + hours
                        binding.tvMint.text = "" + minutes
                        binding.tvSec.text = "" + seconds
                    }
                }

                override fun onFinish() {
                    //Do something when count down finished
                    Log.d("Ak_Completed", "Done!")
                    //Enable Start button
                    binding.btnStart.isEnabled = true
                    binding.btnStop.isEnabled = false
                    binding.btnStart.setBackgroundColor(
                        binding.btnStart.context.resources.getColor(
                            R.color.blue
                        )
                    )
                    binding.btnStop.setBackgroundColor(binding.btnStart.context.resources.getColor(R.color.gray))
                    binding.tvHours.text = "0"
                    binding.tvMint.text = "0"
                    binding.tvSec.text = "0"
                    viewModelCall()
                }
            }.start()
        }


        binding.btnStop.setOnClickListener {
            //When user request to pause the CountDownTimer

            //When user request to pause the CountDownTimer
            isPaused = true
            //Enable Start button
            //Enable Start button
            binding.btnStart.isEnabled = true
            binding.btnStop.isEnabled = false
            binding.btnStart.setBackgroundColor(binding.btnStart.context.resources.getColor(R.color.blue))
            binding.btnStop.setBackgroundColor(binding.btnStart.context.resources.getColor(R.color.gray))
        }

        binding.btnOk.setOnClickListener {
            binding.llTimerStartLay.visibility = View.GONE
            binding.llTimerCompleteLay.visibility = View.GONE
            binding.llImagedisplay.visibility = View.VISIBLE
        }

        binding.btnHome.setOnClickListener {
            val intent = Intent(this@TimerStatActivity, MainActivity::class.java)
            startActivity(intent)
        }

        //Back Arrow clicked Listner
        binding.btnBack.setOnClickListener {
            finish()
        }
    }


    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
            val a = Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(a)
        }, 2000)
    }


    fun viewModelCall() {
        //Progress Bar will Create and Start here
        Log.d("Ak_Calling", "Inside viewModelCall()")
        try {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.isIndeterminate = true
            mProgressDialog!!.setMessage("Loading...")
            mProgressDialog!!.show()
            timerViewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)
            timerViewModel!!.fetchCountriesFromServer(false)?.observe(this,
                Observer<Any?> { breedModel ->
                    Log.d("Ak_Calling", "Response In Activity: " + breedModel)
                    //Progress Bar will dismissed here
                    mProgressDialog!!.dismiss()
                    if (breedModel != null) {
                        var message = (breedModel as BreedModel2).message
                        var status = (breedModel as BreedModel2).status
                        Log.d("AK", String.valueOf(breedModel))
                        if (status != null) {
                            Log.d("data", status)
                        }
                        Toast.makeText(this@TimerStatActivity, "Response: $status", Toast.LENGTH_LONG
                        ).show()
                        try {
                            binding.llTimerStartLay.visibility = View.GONE
                            binding.llTimerCompleteLay.visibility = View.VISIBLE
                            binding.llImagedisplay.visibility = View.GONE
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        Picasso.with(this@TimerStatActivity).load(message).into(binding.ivImageview)
                        if (message != null) {
                            Log.d("AK", message)
                        }
                    } else {
                        Toast.makeText(this@TimerStatActivity, "Network Error", Toast.LENGTH_LONG).show()
                        try {
                            binding.llTimerStartLay.visibility = View.VISIBLE
                            binding.llTimerCompleteLay.visibility = View.GONE
                            binding.llImagedisplay.visibility = View.GONE
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}