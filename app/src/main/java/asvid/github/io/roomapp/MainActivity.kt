package asvid.github.io.roomapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import asvid.github.io.roomapp.api.Api
import asvid.github.io.roomapp.api.pojo.Gist
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }

    initGistList();

    Api.gitHubService.listRepos().enqueue(object : Callback<List<Gist>> {
      override fun onFailure(call: Call<List<Gist>>?, t: Throwable?) {
        Log.d("MAIN_ACTIVITY", "getting Gist FAIL $t")
      }

      override fun onResponse(call: Call<List<Gist>>?, response: Response<List<Gist>>?) {
        Log.d("MAIN_ACTIVITY", "getting Gist success $response")
        Log.d("MAIN_ACTIVITY", "getting Gist success ${response?.body()}")
      }
    })
  }

  private fun initGistList() {

  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }
}
