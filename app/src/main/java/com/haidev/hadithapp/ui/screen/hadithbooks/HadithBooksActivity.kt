package com.haidev.hadithapp.ui.screen.hadithbooks

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidev.hadithapp.R
import com.haidev.hadithapp.data.model.HadithBooksModel
import com.haidev.hadithapp.data.model.Resource
import com.haidev.hadithapp.databinding.ActivityHadithBooksBinding
import com.haidev.hadithapp.ui.base.BaseActivity
import com.haidev.hadithapp.util.Status
import com.haidev.hadithapp.util.gone
import com.haidev.hadithapp.util.observe
import com.haidev.hadithapp.util.visible
import org.koin.android.ext.android.inject

class HadithBooksActivity : BaseActivity<ActivityHadithBooksBinding, HadithBooksViewModel>(),
    HadithBooksNavigator {

    private val hadithBooksViewModel: HadithBooksViewModel by inject()
    private var _binding: ActivityHadithBooksBinding? = null
    private val binding get() = _binding

    private lateinit var itemHadithBooksAdapter: ItemHadithBooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        hadithBooksViewModel.navigator = this
        initAdapter()
        onGetHadithBooks()
    }

    override fun setLayout(): Int = R.layout.activity_hadith_books

    override fun getViewModels(): HadithBooksViewModel = hadithBooksViewModel

    private fun initAdapter() {
        binding?.rvHadithBooks?.apply {
            layoutManager = LinearLayoutManager(this@HadithBooksActivity)
            itemHadithBooksAdapter = ItemHadithBooksAdapter {
                navigateToHadithCollection(it)
            }
            adapter = itemHadithBooksAdapter
        }

    }

    private fun onGetHadithBooks() {
        hadithBooksViewModel.getListHadithBooks()
        with(hadithBooksViewModel) {
            observe(listHadithBooks, ::handleListHadithBooks)
        }
    }

    private fun handleListHadithBooks(it: Resource<HadithBooksModel.Response>) {
        when (it.status) {
            Status.LOADING -> {
                binding?.shimmerHadithBooks?.visible()
            }
            Status.SUCCESS -> {
                binding?.shimmerHadithBooks?.gone()
                itemHadithBooksAdapter.submitList(it.data?.data?.toMutableList())
            }
            Status.ERROR -> binding?.shimmerHadithBooks?.gone()
            else -> binding?.shimmerHadithBooks?.gone()
        }
    }

    override fun navigateToHadithCollection(data: HadithBooksModel.Response.Data) {
        Toast.makeText(this, data.id, Toast.LENGTH_SHORT).show()
    }
}