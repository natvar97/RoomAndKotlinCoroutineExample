package com.example.kotlin_coroutine_example.view.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_coroutine_example.R
import com.example.kotlin_coroutine_example.database.User
import com.example.kotlin_coroutine_example.view.activities.MainActivity

class UserRecyclerAdapter(
    private val activity: Activity
) : RecyclerView.Adapter<UserRecyclerAdapter.UserRecyclerViewHolder>() {

    private var users = ArrayList<User>()

    class UserRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username = itemView.findViewById<TextView>(R.id.tv_username)
        val email = itemView.findViewById<TextView>(R.id.tv_email)
        val password = itemView.findViewById<TextView>(R.id.tv_password)

        val ivDelete = itemView.findViewById<ImageView>(R.id.iv_delete)

        fun bind(user: User) {
            username.text = user.username
            email.text = user.email
            password.text = user.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UserRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRecyclerViewHolder, position: Int) {
        holder.bind(users[position])
        holder.ivDelete.setOnClickListener {
            if (activity is MainActivity) {
                activity.deleteUser(users[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun getAll(list: List<User>) {
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }
}