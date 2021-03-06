package asvid.github.io.roomapp.views.gists

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import asvid.github.io.roomapp.R
import asvid.github.io.roomapp.R.*
import asvid.github.io.roomapp.model.GistWithOwnerModel
import asvid.github.io.roomapp.views.gists.GistAdapter.GistViewHolder
import com.bumptech.glide.Glide
import io.reactivex.subjects.PublishSubject
import java.util.*

class GistAdapter : RecyclerView.Adapter<GistViewHolder>() {

    private var items: List<GistWithOwnerModel>? = null
    val itemStarredSubject: PublishSubject<GistWithOwnerModel> = PublishSubject.create<GistWithOwnerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(layout.gist_item, parent, false)
        return GistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        val item = items?.get(position)
        holder.setStarredListener(null)
        holder.setId(item?.id.toString())
        holder.setDescription(item?.description)
        holder.setOwnerLogin(item?.owner?.login)
        holder.setStarred(item?.starred)
        holder.setDate(item?.date)
        holder.setStarredListener(CompoundButton.OnCheckedChangeListener { button, isChecked ->
            items?.get(position)!!.starred = isChecked
            itemStarredSubject.onNext(items?.get(position)!!)
        })
    }

    fun updateData(list: List<GistWithOwnerModel>) {
        items = list
        notifyDataSetChanged()
    }

    class GistViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val starredCheckbox = view.findViewById<CheckBox>(id.starredCheckBox)

        fun setId(id: String?) {
            view.findViewById<TextView>(R.id.idTextView).text = id
            starredCheckbox.tag = id
        }

        fun setDate(date: Date?) {
            view.findViewById<TextView>(id.gistDateTextView).text = date.toString()
        }

        fun setDescription(description: String?) {
            view.findViewById<TextView>(id.gistDescriptionTextView).text = description
        }

        fun setOwnerLogin(login: String?) {
            view.findViewById<TextView>(id.gistAuthorTextView).text = login
        }

        fun setOwnerAvatarUrl(avatarUrl: String?) {
            Log.d("GIST_HOLDER", "url: $avatarUrl")
            Glide.with(view.context)
                    .load(avatarUrl)
                    .placeholder(mipmap.ic_launcher)
                    .into(view.findViewById(id.gistAuthorAvatarImageView))
        }

        fun setStarred(starred: Boolean?) {
            starredCheckbox.isChecked = starred ?: false
        }

        fun setStarredListener(listener: CompoundButton.OnCheckedChangeListener?) {
            starredCheckbox.setOnCheckedChangeListener(listener)
        }

    }

}