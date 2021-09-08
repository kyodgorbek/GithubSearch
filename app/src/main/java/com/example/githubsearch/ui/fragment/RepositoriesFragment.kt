package com.example.githubsearch.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearch.R
import com.example.githubsearch.adapter.RepositoryAdapter
import com.example.githubsearch.data.model.GithubRepository
import com.example.githubsearch.databinding.FragmentRepositoriesBinding
import com.example.githubsearch.viewmodel.RepositoriesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.HttpException

class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding>() {

    private val viewModel: RepositoriesViewModel by inject()

    override val bindingInflater =
        { inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean ->
            FragmentRepositoriesBinding.inflate(inflater, container, attachToRoot)
        }

    private val repositoryAdapter by lazy {
        RepositoryAdapter {
            showDetails(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.setOnEditorActionListener { textView, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    textView.text.toString().let {
                        if (it.isEmpty()) {
                            Snackbar.make(
                                binding.root,
                                getString(R.string.empty_search),
                                Snackbar.LENGTH_SHORT
                            ).show()
                            return@setOnEditorActionListener false
                        }
                    }
                    getData(textView.text.toString())
                    true
                }
                else -> false
            }
        }

        binding.rvRepositories.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            adapter = repositoryAdapter
        }
    }

    private fun getData(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            repositoryAdapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Error -> {
                        val errorText = (it.refresh as LoadState.Error).error.let { error ->
                            when (error) {
                                is HttpException -> error.message()
                                else -> error.message
                            } ?: getString(R.string.unknown_error)
                        }
                        Snackbar.make(binding.root, errorText, Snackbar.LENGTH_LONG).show()
                        binding.cpProgress.hide()
                    }
                    is LoadState.Loading -> {
                        binding.cpProgress.show()
                    }
                    is LoadState.NotLoading -> {
                        binding.cpProgress.hide()
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.search(searchString).collectLatest {
                repositoryAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    private fun showDetails(repository: GithubRepository) {
        navController.navigate(R.id.action_Repositories_to_RepositoryDetails, Bundle().apply {
            putParcelable(RepositoryDetailsFragment.EXTRA_REPOSITORY, repository)
        })
    }

}