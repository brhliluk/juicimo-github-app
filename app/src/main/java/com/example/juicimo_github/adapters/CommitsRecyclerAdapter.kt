package com.example.juicimo_github.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.juicimo_github.DetailActivity
import com.example.juicimo_github.R
import com.example.juicimo_github.models.Commit
import kotlinx.android.synthetic.main.commit_item.view.*

/**
 * Class supporting list of repositories
 */
class CommitsRecyclerAdapter(private var clickListener: DetailActivity): RecyclerView.Adapter<CommitsRecyclerAdapter.CommitsViewHolder>() {

    // Stores all repositories
    private var items: List<Commit> = ArrayList()

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder {
        return CommitsRecyclerAdapter.CommitsViewHolder(
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
        holder.initialize(items[position], clickListener)
    }

    /**
     * Returns number of commits
     */
    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(commitsList: List<Commit>){
        items = commitsList
    }

    fun addItem(newCommit: Commit){
        items = items + newCommit
    }

    /**
     * Provide a reference to the views for each data item
     */
    class CommitsViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private val commitName: TextView = itemView.commit_name

        fun bind(commit: Commit){
            commitName.text = commit.name
        }

        /**
         * Init all fields, add onClickListener to items
         */
        fun initialize(item: Commit, action: OnCommitItemClickListener){
            commitName.text = item.name

            itemView.setOnClickListener{
                action.onItemClick(item, adapterPosition)
            }
        }

    }
}

/**
 * Ensures clicking on item in list feedback
 */
interface OnCommitItemClickListener{
    fun onItemClick(item: Commit, position: Int)
}