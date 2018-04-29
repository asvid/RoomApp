package asvid.github.io.roomapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwner
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwnerRepository
import asvid.github.io.roomapp.data.owner.OwnerRepository
import asvid.github.io.roomapp.model.GistWithOwnerModel
import asvid.github.io.roomapp.model.OwnerModel
import asvid.github.io.roomapp.model.toModel
import asvid.github.io.roomapp.services.GistLoadService
import asvid.github.io.roomapp.services.GistLoadService.ACTION
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.gistList
import java.util.Date
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  lateinit var gistRepository: GistRepository
    @Inject set

  lateinit var ownerRepository: OwnerRepository
    @Inject set

  lateinit var gistWithOwnerRepository: GistWithOwnerRepository
    @Inject set

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AndroidInjection.inject(this)

    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    fab.setOnClickListener {
      addGist()
    }

    initGistList()
  }

  private lateinit var adapter: GistAdapter

  private fun initGistList() {
    adapter = GistAdapter()
    gistList.adapter = adapter
    gistList.layoutManager = LinearLayoutManager(this)

    ownerRepository.fetchAll()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          Log.d("MAIN_ACTIVITY", "new Owner: $it")
        }
    gistRepository.fetchAll()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          Log.d("MAIN_ACTIVITY", "new Gist: $it")
        }

    gistWithOwnerRepository.fetchAll()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { onNext -> handleGistsChange(onNext) },
            { onError -> Log.d("MAIN_ACTIVITY", "error: $onError") },
            { Log.d("MAIN_ACTIVITY", "onComplete") }
        )
  }

  private fun handleGistsChange(onNext: Collection<GistWithOwner>) {
    Log.d("MAIN_ACTIVITY", "onNext $onNext")
    adapter.updateData(onNext.toList().toModel())
  }

  private fun addGist() {
    val owner = OwnerModel("random login")
    gistWithOwnerRepository.save(
        GistWithOwnerModel(null, "description", owner, true, Date())).subscribe { gistWithOwner ->
      Log.d("MAIN_ACTIVITY", "saved gist with owner: $gistWithOwner")
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_service -> handleServiceChange(item)
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun handleServiceChange(menuItem: MenuItem): Boolean {
    if (menuItem.isChecked) {
      val stopIntent = Intent(this@MainActivity, GistLoadService::class.java)
      stopIntent.action = ACTION.STOPFOREGROUND_ACTION
      startService(stopIntent)
      menuItem.isChecked = false
    } else {
      val startIntent = Intent(this@MainActivity, GistLoadService::class.java)
      startIntent.action = ACTION.STARTFOREGROUND_ACTION
      startService(startIntent)
      menuItem.isChecked = true
    }
    return true
  }
}
