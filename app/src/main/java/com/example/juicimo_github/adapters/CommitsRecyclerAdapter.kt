package com.example.juicimo_github.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.juicimo_github.DetailActivity
import com.example.juicimo_github.R
import com.example.juicimo_github.models.CommitGH
import kotlinx.android.synthetic.main.commit_item.view.*

/**
 * Class supporting list of repositories
 */
class CommitsRecyclerAdapter(private var clickListener: DetailActivity) :
    RecyclerView.Adapter<CommitsRecyclerAdapter.CommitsViewHolder>() {

    // Stores all repositories
    private var items: List<CommitGH> = ArrayList()

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder {
        return CommitsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.commit_item,
                parent,
                false
            )
        )
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: CommitsRecyclerAdapter.CommitsViewHolder, position: Int) {
        holder.initialize(items[position])
    }

    /**
     * Returns number of commits
     */
    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(commitsList: List<CommitGH>) {
        items = commitsList
    }

    /**
     * Provide a reference to the views for each data item
     */
    class CommitsViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val commitName: TextView = itemView.commit_name
        private val commitDetails: TextView = itemView.commit_details

        /**
         * Init all fields, add onClickListener to items
         */
        fun initialize(item: CommitGH) {
            commitName.text = item.name
            val commitDetailsText = item.author + " commited on " + item.date
            commitDetails.text = commitDetailsText
        }

    }
}