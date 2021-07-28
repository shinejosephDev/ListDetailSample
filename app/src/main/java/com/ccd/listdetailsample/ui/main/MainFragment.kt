package com.ccd.listdetailsample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ccd.listdetailsample.business.model.Article
import com.ccd.listdetailsample.data.repository.AppRepository
import com.ccd.listdetailsample.databinding.MainFragmentBinding
import com.ccd.listdetailsample.ui.detail.DetailActivity

class MainFragment : Fragment(), ListAdapter.Interaction {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var listAdapter: ListAdapter? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = AppRepository()
        val factory = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        setupList()
        populateData()

        return binding.root
    }

    private fun populateData() {
        viewModel.response.observe(viewLifecycleOwner, Observer { response ->
            listAdapter?.submitList(response.results)
        })
    }

    private fun setupList() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, 1))
            listAdapter = ListAdapter(
                context,
                this@MainFragment
            )
            adapter = listAdapter
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(position: Int, item: Article) {
        activity?.let { DetailActivity.startActivity(it, item.url) }
    }

}