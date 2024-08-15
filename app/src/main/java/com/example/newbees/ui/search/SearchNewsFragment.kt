package com.example.newbees.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbees.ui.trending.TrendingNewsPagingAdapter
import com.example.newbees.utils.onUpButtonClick
import com.example.newbees.utils.showSoftKeyboard
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mubarak.newscastmb.R
import com.mubarak.newscastmb.databinding.FragmentSearchNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchNewsFragment : Fragment() {

    private lateinit var binding: FragmentSearchNewsBinding

    private lateinit var pagerAdapter: TrendingNewsPagingAdapter
    private val viewModel: SearchNewsViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchNewsBinding.inflate(
            layoutInflater,
            container,
            false
        )

        pagerAdapter = TrendingNewsPagingAdapter()
        setUpRecyclerView(binding.searchNewsList)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchToolbar.onUpButtonClick()

        binding.searchViewNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {

                query?.let {
                    viewModel.getNewsBySearch(it)
                }
                return true
            }
        })

        binding.searchViewNews.setOnQueryTextFocusChangeListener { edittext, hasFocus ->
            if (hasFocus) {
                view.showSoftKeyboard(edittext.findFocus())
            }
        }
        binding.searchViewNews.requestFocus()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.searchResults.collect {
                    pagerAdapter.submitData(lifecycle, it)
                }
            }
        }
        pagerAdapter.onNewsItemClick {
            val action =
                SearchNewsFragmentDirections.actionSearchNewsFragmentToDetailedNewsFragment(
                    it
                )
            findNavController().navigate(action)
        }

        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.btnView)

        hideBottomNavigation(navBar)
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = pagerAdapter
        }
    }

    private fun hideBottomNavigation(bottomNavigationView: BottomNavigationView) {

        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.searchNewsFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}