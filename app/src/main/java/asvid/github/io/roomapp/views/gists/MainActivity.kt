package asvid.github.io.roomapp.views.gists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import asvid.github.io.roomapp.R
import asvid.github.io.roomapp.R.id
import asvid.github.io.roomapp.R.layout
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.gistwithowner.GistWithOwnerRepository
import asvid.github.io.roomapp.data.owner.OwnerRepository
import asvid.github.io.roomapp.model.GistWithOwnerModel
import asvid.github.io.roomapp.model.toGistModel
import asvid.github.io.roomapp.services.GistLoadService
import asvid.github.io.roomapp.services.GistLoadService.ACTION
import asvid.github.io.roomapp.views.owners.OwnersIntent
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

fun Context.GistsIntent(): Intent {
    return Intent(this, MainActivity::class.java)
}

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

        setContentView(layout.activity_main)
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
                    Log.d("MAIN_ACTIVITY", "Owners changed: $it")
                }

        gistRepository.fetchAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("MAIN_ACTIVITY", "Gists changed: $it")
                    Toast.makeText(this, "new gist!", Toast.LENGTH_SHORT).show()

                    gistWithOwnerRepository.fetchAll()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    { onNext -> handleGistsChange(onNext) },
                                    { onError -> Log.d("MAIN_ACTIVITY", "error: $onError") },
                                    { Log.d("MAIN_ACTIVITY", "onComplete") }
                            )
                }

        adapter.itemStarredSubject
                .subscribe {
                    gistRepository.update(it.toGistModel()).subscribe()
                    Log.d("MAIN_ACTIVITY", "star checkbox clicked: ${it.toGistModel()}")
                }
    }

    private fun handleGistsChange(onNext: Collection<GistWithOwnerModel>) {
        Log.d("MAIN_ACTIVITY", "onNext $onNext")
        adapter.updateData(onNext.toList())
    }

    private fun addGist() {
        AddGistDialog().show(fragmentManager, "add gist")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.action_service -> handleServiceChange(item)
            id.action_show_owners -> showOwners()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showOwners(): Boolean {
        startActivity(OwnersIntent())
        return true
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
