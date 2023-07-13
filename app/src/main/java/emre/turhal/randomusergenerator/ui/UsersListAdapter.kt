package emre.turhal.randomusergenerator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import emre.turhal.randomusergenerator.R
import emre.turhal.randomusergenerator.model.ResultsItem

class UsersListAdapter(private val clickListener : (user : ResultsItem) -> Unit) : androidx.recyclerview.widget.ListAdapter<ResultsItem, UsersListAdapter.UserViewHolder>(UserDiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user, clickListener)
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.name)
        private val userCity: TextView = itemView.findViewById(R.id.city)
        private val userPicture: ImageView = itemView.findViewById(R.id.picture)
        private val userFirstName: TextView = itemView.findViewById(R.id.firstname)

        fun bind(user: ResultsItem, clickListener: (user: ResultsItem) -> Unit) = with(itemView) {
            Glide.with(context).load(user.picture?.large)
                .apply(RequestOptions().circleCrop()).into(userPicture)
            userName.text = user.name?.last
            userCity.text = user.location?.city
            userFirstName.text = user.name?.first
            itemView.setOnClickListener{
                clickListener(user)
            }
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.users_list_item, parent, false)
                return UserViewHolder(view)
            }
        }
    }}

    private class UserDiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
    override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem == newItem
    }
}