package asvid.github.io.roomapp.views.owners

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import asvid.github.io.roomapp.R
import asvid.github.io.roomapp.model.OwnerWithGistsModel
import asvid.github.io.roomapp.views.owners.OwnersAdapter.OwnerViewHolder
import io.reactivex.subjects.PublishSubject

class OwnersAdapter : RecyclerView.Adapter<OwnerViewHolder>() {

    private var items: List<OwnerWithGistsModel>? = null
    val itemDeleteSubject: PublishSubject<OwnerWithGistsModel> = PublishSubject.create<OwnerWithGistsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnersAdapter.OwnerViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.owner_item, parent, false)
        return OwnersAdapter.OwnerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        val item = items?.get(position)
        holder.setDeleteButtonListener(View.OnClickListener {
            itemDeleteSubject.onNext(item!!)
        })
        holder.setId(item?.id)
        holder.setLogin(item?.login)
        holder.setAvatarUrl()
        holder.setGistsNumber(item?.gists?.size)
    }

    fun updateData(list: List<OwnerWithGistsModel>) {
        items = list
        notifyDataSetChanged()
    }

    class OwnerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setDeleteButtonListener(listener: View.OnClickListener) {
            view.findViewById<Button>(R.id.deleteButton).setOnClickListener(listener)
        }

        fun setId(id: Long?) {
            view.findViewById<TextView>(R.id.idTextView).text = id.toString()
        }

        fun setLogin(login: String?) {
            view.findViewById<TextView>(R.id.ownerLoginTextView).text = login
        }

        fun setAvatarUrl() {

        }

        fun setGistsNumber(size: Int?) {
            view.findViewById<TextView>(R.id.ownerGistsNumberTextView).text = "has $size gists"
        }
    }
}