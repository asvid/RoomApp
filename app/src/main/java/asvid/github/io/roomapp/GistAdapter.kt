package asvid.github.io.roomapp

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import asvid.github.io.roomapp.GistAdapter.GistViewHolder
import asvid.github.io.roomapp.model.GistModel

class GistAdapter : RecyclerView.Adapter<GistViewHolder>() {

  lateinit var items: List<GistModel>

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {

  }

  override fun getItemCount(): Int {
    return items.size
  }

  override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
  }

  fun updateData(list: List<GistModel>) {
    items = list
    notifyDataSetChanged()
  }

  class GistViewHolder : RecyclerView.ViewHolder() {

  }

}