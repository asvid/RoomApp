package asvid.github.io.roomapp.views.owners

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
import asvid.github.io.roomapp.data.owner.OwnerRepository
import asvid.github.io.roomapp.model.OwnerModel
import asvid.github.io.roomapp.views.gists.GistsIntent
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_owners.*
import kotlinx.android.synthetic.main.content_owners.*
import javax.inject.Inject

fun Context.OwnersIntent(): Intent {
    return Intent(this, OwnersActivity::class.java)
}

class OwnersActivity : AppCompatActivity() {

    lateinit var ownersRepository: OwnerRepository
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_owners)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            addOwner()
        }

        initOwnersList()
    }

    private lateinit var adapter: OwnersAdapter
    private fun initOwnersList() {
        adapter = OwnersAdapter()
        ownersList.adapter = adapter
        ownersList.layoutManager = LinearLayoutManager(this)

        ownersRepository.fetchAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onNext -> handleOwnersChange(onNext.toList()) },
                        { onError -> Log.d("OWNERS", "error: $onError") },
                        { Log.d("OWNERS", "onComplete") }
                )

        adapter.itemDeleteSubject.subscribe {
            Log.d("OWNERS", "deleting $it")
            ownersRepository.delete(it).subscribe()
        }

        adapter.itemClickedSubject.subscribe {
            val ownerId = it.id
            ownersRepository.fetchById(ownerId!!)
                    .subscribe { next ->
                        Toast.makeText(this, "Owner: $next", Toast.LENGTH_LONG).show()
                    }
        }
    }

    private fun handleOwnersChange(onNext: List<OwnerModel>) {
        Log.d("OWNERS", "onNext: $onNext")
        adapter.updateData(onNext)
    }

    private fun addOwner() {
        AddOwnerDialog().show(fragmentManager, "add owner")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_owners, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.action_show_gists -> openGistsActivity()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openGistsActivity(): Boolean {
        startActivity(GistsIntent())
        return true
    }

}
