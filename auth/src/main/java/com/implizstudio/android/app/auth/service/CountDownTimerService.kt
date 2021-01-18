package com.implizstudio.android.app.auth.service

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import com.implizstudio.android.app.androidwarungkuowner.data.model.constant.Constant

class CountDownTimerService : Service() {

    companion object {
        private const val INTERVAL_MILLISECONDS = 60000L
        private const val TIME_MILLISECONDS = 1000L
        const val INTENT_ACTION = "com.implizstudio.android.app.auth.service"
    }

    private lateinit var countDownTimer: CountDownTimer

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        val intent = Intent(INTENT_ACTION)

        countDownTimer = object : CountDownTimer(INTERVAL_MILLISECONDS, TIME_MILLISECONDS) {

            override fun onTick(millisUntilFinished: Long) {
                intent.putExtra(Constant.Key.COUNT_TIMER, millisUntilFinished)
                sendBroadcast(intent)
            }

            override fun onFinish() {
                intent.putExtra(Constant.Key.COUNT_FINISH, true)
                sendBroadcast(intent)
            }

        }.start()

    }

    override fun onDestroy() {
        countDownTimer.cancel()
        super.onDestroy()
    }

}