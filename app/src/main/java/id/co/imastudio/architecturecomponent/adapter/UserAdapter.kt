package id.co.imastudio.architecturecomponent.adapter

import android.content.ClipData
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.co.imastudio.architecturecomponent.R
import id.co.imastudio.architecturecomponent.db.User

/**
 * Created by gilan on 2/9/2018.
 */
class UserAdapter(var userModels: List<User>, var longClickListener: View.OnLongClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(v)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userModel = userModels[position]
        holder?.textNama?.text = userModel.nama_user
        holder?.textNomer?.text = userModel.nomer_user
        holder.itemView.tag = userModel
        holder.itemView.setOnLongClickListener(longClickListener)
    }

    override fun getItemCount(): Int {
        return userModels.size
    }

    fun addUser(user: List<User>) {
        userModels = user
        notifyDataSetChanged()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageUser = itemView.findViewById<ImageView>(R.id.imageView)
        val textNama = itemView.findViewById<TextView>(R.id.nama)
        val textNomer = itemView.findViewById<TextView>(R.id.nomer)
    }


}