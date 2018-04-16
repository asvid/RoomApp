package asvid.github.io.roomapp.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import asvid.github.io.roomapp.MainActivity
import asvid.github.io.roomapp.R.mipmap
import asvid.github.io.roomapp.api.Api
import asvid.github.io.roomapp.api.pojo.Gist
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.owner.OwnerRepository
import asvid.github.io.roomapp.model.toModel
import dagger.android.AndroidInjection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GistLoadService : Service() {

    lateinit var gistRepository: GistRepository
        @Inject set
    lateinit var ownerRepository: OwnerRepository
        @Inject set

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
            stopForeground(true)
            stopSelf()
        }
        return Service.START_STICKY
    }

    private fun startLoadingData() {
        val handler = Handler()
        val delay = 3 * 1000 //milliseconds

        handler.postDelayed(object : Runnable {
            override fun run() {
                Api.gitHub.listRepos().enqueue(object : Callback<List<Gist>> {
                    override fun onFailure(call: Call<List<Gist>>?, t: Throwable?) {
//            NOOP
                    }

                    override fun onResponse(call: Call<List<Gist>>?,
                                            response: Response<List<Gist>>?) {
                        response?.body()?.map {
                            val gistModel = it.toModel()
                            gistRepository.save(gistModel)
                            ownerRepository.save(gistModel.owner)
                            return@map
                        }
                    }
                })
                handler.postDelayed(this, delay.toLong())
            }
        }, delay.toLong())
    }
}
