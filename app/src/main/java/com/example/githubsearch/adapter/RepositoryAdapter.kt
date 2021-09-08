package com.example.githubsearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.githubsearch.R
import com.example.githubsearch.data.model.GithubRepository
import com.example.githubsearch.databinding.ViewItemRepositoryBinding

class RepositoryAdapter(private val onItemClick: (repository: GithubRepository) -> Unit) :
    BasePagingDataAdapter<GithubRepository, ViewItemRepositoryBinding>() {
    override val bindingInflater =
        { inflater: LayoutInflater, viewGroup: ViewGroup?, attachToRoot: Boolean ->
            ViewItemRepositoryBinding.inflate(inflater, viewGroup, attachToRoot)
        }

    override fun bind(data: GithubRepository, binding: ViewItemRepositoryBinding) {
        with(binding) {
            tvRepositoryName.text = data.name
            Glide.with(root.context).load(data.owner.avatarUrl).into(binding.ivOwnerAvatar)
            rbRepositoryStars.rating = data.score
            tvRepositoryStars.text =
                binding.root.context.getString(R.string.format_stars, data.stars)
            root.setOnClickListener {
                onItemClick.invoke(data)
            }
        }
    }
}