package asvid.github.io.roomapp.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import asvid.github.io.roomapp.R.mipmap
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.owner.OwnerRepository
import asvid.github.io.roomapp.model.GistModel
import asvid.github.io.roomapp.model.OwnerModel
import asvid.github.io.roomapp.utils.RandomStringGenerator
import asvid.github.io.roomapp.utils.getRandomElement
import asvid.github.io.roomapp.views.gists.MainActivity
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.IoScheduler
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
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
            const val STARTFOREGROUND_ACTION = "asvid.github.io.roomapp.services.GistLoadService.action.startforeground"
            const val STOPFOREGROUND_ACTION = "asvid.github.io.roomapp.services.GistLoadService.action.stopforeground"
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
                    .setContentTitle("Gist creating")
                    .setContentText("")
                    .setSmallIcon(mipmap.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .build()
            startForeground(NOTIFICATION_ID.FOREGROUND_SERVICE, notification)

            startLoadingData()
        } else if (intent.action == ACTION.STOPFOREGROUND_ACTION) {
            Timber.d("STOP creating gists")
            intervalRunner.dispose()
            stopForeground(true)
            stopSelf()
        }
        return Service.START_STICKY
    }

    private lateinit var intervalRunner: Disposable

    private fun startLoadingData() {
        Timber.d("start creating gists")
//        TODO(24) tworzenie losowego Gista w tle
        intervalRunner = Observable
                .interval(10, TimeUnit.SECONDS)
                .startWith(0)
                .subscribeOn(IoScheduler())
                .doOnNext {
                    // TODO(25) pobierana jest zamknęta lista Ownerów a nie strumień - w tym miejscu nie interesują nas aktualizacje,
                    // a dostęp do aktualizowanego strumienia poza wątkiem z Looperem powoduje wyjątek
                    ownerRepository.fetchAllOnce().subscribe { owners ->
                        Timber.d("onNext Owners list: $owners")
                        val owner = owners.getRandomElement() as OwnerModel
                        Timber.d("adding gist to owner: $owner")
//                        TODO(26) losujemy Ownera z listy, generujemy losowo Gista i zapisujemy
                        owner.let {
                            val gist = GistModel(null, RandomStringGenerator.getString(), it, false, Date())
                            Timber.d("gist added from service: $gist")
                            gistRepository.save(gist).subscribe { onNext ->
                                Timber.d("gist saved: $onNext")

                            }
                        }
                    }
                }.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
