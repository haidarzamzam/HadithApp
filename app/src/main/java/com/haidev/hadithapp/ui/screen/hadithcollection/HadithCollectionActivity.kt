package com.haidev.hadithapp.ui.screen.hadithcollection

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidev.hadithapp.R
import com.haidev.hadithapp.data.model.HadithBooksModel
import com.haidev.hadithapp.data.model.HadithCollectionModel
import com.haidev.hadithapp.data.model.Resource
import com.haidev.hadithapp.databinding.ActivityHadithCollectionBinding
import com.haidev.hadithapp.ui.base.BaseActivity
import com.haidev.hadithapp.util.Status
import com.haidev.hadithapp.util.gone
import com.haidev.hadithapp.util.observe
import com.haidev.hadithapp.util.visible
import org.koin.android.ext.android.inject

class HadithCollectionActivity :
    BaseActivity<ActivityHadithCollectionBinding, HadithCollectionViewModel>(),
    HadithCollectionNavigator {

    private val hadithCollectionViewModel: HadithCollectionViewModel by inject()
    private var _binding: ActivityHadithCollectionBinding? = null
    private val binding get() = _binding

    private lateinit var dataHadithBook: HadithBooksModel.Response.Data
    private lateinit var itemHadithCollectionAdapter: ItemHadithCollectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        hadithCollectionViewModel.navigator = this
        initLayout()
        initAdapter()
        onGetHadithCollection("1-100")
    }

    private fun initLayout() {
        dataHadithBook =
            intent.getParcelableExtra<HadithBooksModel.Response.Data>(EXTRA_DATA_HADITH_BOOKS)
                    as HadithBooksModel.Response.Data
        binding?.tvTitle?.text = "Hadith : ${dataHadithBook.name}"
        binding?.ivBack?.setOnClickListener {
            finish()
        }
    }

    private fun initAdapter() {
        binding?.rvHadithCollection?.apply {
            layoutManager = LinearLayoutManager(this@HadithCollectionActivity)
            itemHadithCollectionAdapter = ItemHadithCollectionAdapter {
                navigateToShareHadith(it)
            }
            adapter = itemHadithCollectionAdapter
        }
    }

    companion object {
        const val EXTRA_DATA_HADITH_BOOKS = "EXTRA_DATA_HADITH_BOOKS"
    }

    override fun setLayout(): Int = R.layout.activity_hadith_collection

    override fun getViewModels(): HadithCollectionViewModel = hadithCollectionViewModel

    private fun onGetHadithCollection(range: String) {
        hadithCollectionViewModel.getListHadithCollection(dataHadithBook.id, range)
        with(hadithCollectionViewModel) {
            observe(listHadithCollection, ::handleListHadithCollection)
        }
    }

    private fun handleListHadithCollection(it: Resource<HadithCollectionModel.Response>) {
        when (it.status) {
            Status.LOADING -> {
                binding?.shimmerHadithCollection?.visible()
            }
            Status.SUCCESS -> {
                binding?.shimmerHadithCollection?.gone()
                itemHadithCollectionAdapter.submitList(it.data?.data?.hadiths?.toMutableList())
            }
            Status.ERROR -> {
                Toast.makeText(this, it.throwable.toString(), Toast.LENGTH_SHORT).show()
                binding?.shimmerHadithCollection?.gone()
            }
            else -> binding?.shimmerHadithCollection?.gone()
        }
    }

    override fun navigateToShareHadith(data: HadithCollectionModel.Response.Data.Hadith) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hadith form ${dataHadithBook.name}, This hadith contains about ${data.id}"
        )
        startActivity(Intent.createChooser(shareIntent, getString(R.string.app_name)))
    }
}