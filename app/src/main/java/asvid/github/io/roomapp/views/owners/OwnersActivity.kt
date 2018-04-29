package asvid.github.io.roomapp.views.owners

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import asvid.github.io.roomapp.R
import asvid.github.io.roomapp.R.id
import asvid.github.io.roomapp.views.gists.GistsIntent
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_owners.fab
import kotlinx.android.synthetic.main.activity_owners.toolbar

fun Context.OwnersIntent(): Intent {
  return Intent(this, OwnersActivity::class.java)
}

class OwnersActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)
    setContentView(R.layout.activity_owners)
    setSupportActionBar(toolbar)

    fab.setOnClickListener {
      addOwner()
    }
  }

  private fun addOwner() {

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
