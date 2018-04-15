package asvid.github.io.roomapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.gist.GistWithOwnerRepository
import asvid.github.io.roomapp.model.toModel
import asvid.github.io.roomapp.services.GistLoadService
import asvid.github.io.roomapp.services.GistLoadService.ACTION
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.gistList
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  lateinit var gistRepository: GistRepository
    @Inject set

  lateinit var gistWithOwnerRepository: GistWithOwnerRepository
    @Inject set

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AndroidInjection.inject(this)

    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }

    initGistList()
  }

  private lateinit var adapter: GistAdapter

  private fun initGistList() {
    adapter = GistAdapter()
    gistList.adapter = adapter
    gistList.layoutManager = LinearLayoutManager(this)

    gistWithOwnerRepository.fetchAll().subscribe {
      adapter.updateData(it.toList().toModel())
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
