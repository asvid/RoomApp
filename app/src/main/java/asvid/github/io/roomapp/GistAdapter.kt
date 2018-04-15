package asvid.github.io.roomapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import asvid.github.io.roomapp.GistAdapter.GistViewHolder
import asvid.github.io.roomapp.model.GistModel
import com.bumptech.glide.Glide

class GistAdapter : RecyclerView.Adapter<GistViewHolder>() {

  private var items: List<GistModel>? = null

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
    holder.setId(item?.id)
    holder.setUrl(item?.url)
    holder.setDescription(item?.description)
    holder.setOwnerLogin(item?.owner?.login)
    holder.setOwnerAvatarUrl(item?.owner?.avatarUrl)
    holder.setStarred(item?.starred)
  }

  fun updateData(list: List<GistModel>) {
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
      Glide.with(view.context)
          .load(avatarUrl)
          .centerCrop()
          .placeholder(R.mipmap.ic_launcher)
          .dontAnimate()
          .into(view.findViewById(R.id.gistAuthorAvatarImageView))
    }

    fun setStarred(starred: Boolean?) {
      view.findViewById<CheckBox>(R.id.starredCheckBox).isChecked = starred ?: false
    }

  }

}