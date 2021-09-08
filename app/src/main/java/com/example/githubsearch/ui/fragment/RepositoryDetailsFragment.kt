package com.example.githubsearch.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.githubsearch.R
import com.example.githubsearch.data.model.GithubRepository
import com.example.githubsearch.databinding.FragmentRepositoryDetailsBinding

class RepositoryDetailsFragment : BaseFragment<FragmentRepositoryDetailsBinding>() {

    companion object {
        const val EXTRA_REPOSITORY = "extra_repository"
    }

    override val bindingInflater =
        { inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean ->
            FragmentRepositoryDetailsBinding.inflate(inflater, container, attachToRoot)
        }

    private val repository by lazy {
        arguments?.getParcelable(EXTRA_REPOSITORY) as GithubRepository?
            ?: throw IllegalStateException("Repository can't be null")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(repository) {
            binding.tvRepositoryName.text = getString(R.string.format_repository_name, name)
            Glide.with(requireContext()).load(owner.avatarUrl).into(binding.ivOwnerAvatar)
            binding.rbRepositoryStars.rating = score
            binding.tvDescription.text = getString(R.string.format_description, description)
            binding.tvRepositoryUrl.text = getString(R.string.format_repository_url, url)
            binding.tvRepositoryStars.text = getString(R.string.format_stars, stars)
        }
    }

}