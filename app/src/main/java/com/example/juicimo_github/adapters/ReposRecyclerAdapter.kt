package com.example.juicimo_github.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.juicimo_github.R
import com.example.juicimo_github.ScrollingActivity
import com.example.juicimo_github.models.Repository
import kotlinx.android.synthetic.main.repository_item.view.*

/**
 * Class supporting list of repositories
 */
class ReposRecyclerAdapter(private var clickListener: ScrollingActivity) :
    RecyclerView.Adapter<ReposRecyclerAdapter.RepositoriesViewHolder>() {

    // Stores all repositories
    private var items: List<Repository> = ArrayList()

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        return RepositoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.repository_item,
                parent,
                false
            )
        )
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(
        holder: ReposRecyclerAdapter.RepositoriesViewHolder,
        position: Int
    ) {
        holder.initialize(items[position], clickListener)
    }

    /**
     * Returns number of repositories
     */
    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(repositoriesList: List<Repository>) {
        items = repositoriesList
    }

    /**
     * Provide a reference to the views for each data item
     */
    class RepositoriesViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val repositoryName: TextView = itemView.repo_name

        fun bind(repository: Repository) {
            repositoryName.text = repository.name
        }

        /**
         * Init all fields, add onClickListener to items
         */
        fun initialize(item: Repository, action: OnRepositoryItemClickListener) {
            repositoryName.text = item.name

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }

    }
}

/**
 * Ensures clicking on item in list feedback
 */
interface OnRepositoryItemClickListener {
    fun onItemClick(item: Repository, position: Int)
}