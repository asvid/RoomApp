package asvid.github.io.roomapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import asvid.github.io.roomapp.GistAdapter.GistViewHolder
import asvid.github.io.roomapp.model.GistWithOwnerModel
import com.bumptech.glide.Glide

class GistAdapter : RecyclerView.Adapter<GistViewHolder>() {

  private var items: List<GistWithOwnerModel>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.gist_item, parent, false)
    return GistViewHolder(view)
  }

  override fun getItemCount(): Int {
    return items?.size ?: 0
  }

  override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
    val item = items?.get(position)
    holder.setId(item?.id.toString())
    holder.setDescription(item?.description)
    holder.setOwnerLogin(item?.owner?.login)
    holder.setStarred(item?.starred)
  }

  fun updateData(list: List<GistWithOwnerModel>) {
    items = list
    notifyDataSetChanged()
  }

  class GistViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun setId(id: String?) {
      view.findViewById<TextView>(R.id.idTextView).text = id
    }

    fun setUrl(url: String?) {
      view.findViewById<TextView>(R.id.gistDescriptionTextView).text = url
    }

    fun setDescription(description: String?) {
      view.findViewById<TextView>(R.id.gistDescriptionTextView).text = description
    }

    fun setOwnerLogin(login: String?) {
      view.findViewById<TextView>(R.id.gistAuthorTextView).text = login
    }

    fun setOwnerAvatarUrl(avatarUrl: String?) {
      Log.d("GIST_HOLDER", "url: $avatarUrl")
      Glide.with(view.context)
          .load(avatarUrl)
          .placeholder(R.mipmap.ic_launcher)
          .into(view.findViewById(R.id.gistAuthorAvatarImageView))
    }

    fun setStarred(starred: Boolean?) {
      view.findViewById<CheckBox>(R.id.starredCheckBox).isChecked = starred ?: false
    }

  }

}