package asvid.github.io.roomapp.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import asvid.github.io.roomapp.MainActivity
import asvid.github.io.roomapp.R.mipmap
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwnerRepository
import dagger.android.AndroidInjection
import javax.inject.Inject

class GistLoadService : Service() {

  lateinit var gistWithOwnerRepository: GistWithOwnerRepository
    @Inject set

  var handler: Handler? = null
  val delay = 60 * 1000 //milliseconds
  lateinit var runnable: Runnable

  override fun onBind(intent: Intent?): IBinder? {
    return null
  }

  override fun onCreate() {
    super.onCreate()
    AndroidInjection.inject(this)
  }

  interface ACTION {
    companion object {
      const val STARTFOREGROUND_ACTION = "com.truiton.foregroundservice.action.startforeground"
      const val STOPFOREGROUND_ACTION = "com.truiton.foregroundservice.action.stopforeground"
    }
  }

  interface NOTIFICATION_ID {
    companion object {
      const val FOREGROUND_SERVICE = 101
    }
  }

  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
    if (intent.action == ACTION.STARTFOREGROUND_ACTION) {
      val notificationIntent = Intent(this, MainActivity::class.java)
      notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      val pendingIntent = PendingIntent.getActivity(this, 0,
          notificationIntent, 0)

      val notification = NotificationCompat.Builder(this)
          .setContentTitle("Gist downloading")
          .setContentText("")
          .setSmallIcon(mipmap.ic_launcher)
          .setContentIntent(pendingIntent)
          .setOngoing(true)
          .build()
      startForeground(NOTIFICATION_ID.FOREGROUND_SERVICE,
          notification)

      startLoadingData()
    } else if (intent.action == ACTION.STOPFOREGROUND_ACTION) {
      Log.e("GIST_SERVICE", "stop service")
      handler?.removeCallbacks(runnable)
      handler = null
      stopForeground(true)
      stopSelf()
    }
    return Service.START_STICKY
  }

  private fun startLoadingData() {
    handler = Handler()
    runnable = createRunnable()
    handler?.post(runnable)
  }

  private fun createRunnable(): Runnable {
    return object : Runnable {
      override fun run() {

//        TODO: random generate gist with owner

        handler?.postDelayed(this, delay.toLong())
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
  }
}
