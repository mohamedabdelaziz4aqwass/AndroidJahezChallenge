package com.mohamedabdelaziz.jahez.presentation.ui

import dagger.hilt.android.AndroidEntryPoint
import com.mohamedabdelaziz.jahez.presentation.viewmodels.RestaurantViewModel
import android.os.Bundle


import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.mohamedabdelaziz.jahez.R
import com.mohamedabdelaziz.jahez.core.base.BaseActivity
import com.mohamedabdelaziz.jahez.core.utils.constants.Constants
import com.mohamedabdelaziz.jahez.core.utils.languages.Language
import com.mohamedabdelaziz.jahez.core.utils.languages.LanguageUtils
import com.mohamedabdelaziz.jahez.databinding.ActivityMainBinding
import com.mohamedabdelaziz.jahez.domain.entities.RestaurantsResponseItem


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var restaurantAdapter: RestaurantAdapter
    private var isConnected = false
    private val restaurantViewModel by viewModels<RestaurantViewModel>()
    private var checked: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LanguageUtils.setLanguageWithoutReload(Language.getLanguage(), this)
        init()
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.restaurantRecyclerView.layoutManager = gridLayoutManager
        binding.restaurantSwipeRefresh.setColorSchemeResources(R.color.black)
        binding.restaurantRecyclerView.adapter = restaurantAdapter
        binding.restaurantSwipeRefresh.setOnRefreshListener {
            if (isConnected)
            getDataFromApi()
            else
            getDataFromCashes()
            binding.restaurantSwipeRefresh.isRefreshing = false
        }

    }

    override fun onConnectionSuccess() {
        isConnected=true
        getDataFromApi()
    }

    override fun onConnectionFailed() {
        isConnected=false
        getDataFromCashes()
    }


    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.baseToolbar)
        restaurantAdapter = RestaurantAdapter(this)
    }

    private fun getDataFromApi() {
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.GONE
        restaurantViewModel.restaurantMutableLiveData.observe(
            this,
            { restaurantsResponse ->
                restaurantAdapter.setList(restaurantsResponse)
                binding.restaurantRecyclerView.adapter = restaurantAdapter

                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            })
    }

    private fun getDataFromCashes() {
        binding.shimmerLayout.startShimmer()
        restaurantViewModel.getRestaurantListLocal()
        restaurantViewModel.getRestaurantListLocal()
            .observe(this, { restaurantsResponseItem: List<RestaurantsResponseItem> ->
                restaurantAdapter.setList(restaurantsResponseItem)
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_rating -> {
                restaurantAdapter.setList(restaurantViewModel.restaurantMutableLiveData.value!!.sortedByDescending { it.rating })
                return true
            }
            R.id.action_offers -> {
                restaurantAdapter.setList(restaurantViewModel.restaurantMutableLiveData.value!!.filter { it.hasOffer })
                return true
            }
            R.id.action_distance -> {
                restaurantAdapter.setList(restaurantViewModel.restaurantMutableLiveData.value!!.sortedByDescending { it.distance })
                return true
            }
            R.id.action_settings -> {
                settingDialog()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun settingDialog() {
        checked = when {
            Language.getLanguage() == "ar" -> {
                0
            }
            Language.getLanguage() == "en" -> {
                1
            }
            else -> -1
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.label_app_lang)
        val items = arrayOf(
            getString(R.string.lang_arabic),
            getString(R.string.lang_english),
        )

        builder.setSingleChoiceItems(
            items,
            checked
        )
        { _, _ -> }.setPositiveButton(R.string.ok) { dialog, _ ->
            dialog.dismiss()
            when ((dialog as AlertDialog).listView.checkedItemPosition) {
                0 -> {
                    LanguageUtils.saveInSharedPreference(Constants.LANGUAGE, "ar")
                    LanguageUtils.setLanguage("ar", this)
                    LanguageUtils.setLanguageWithoutReload("ar", this)

                }
                1 -> {
                    LanguageUtils.saveInSharedPreference(Constants.LANGUAGE, "en")
                    LanguageUtils.setLanguage("en", this)
                    LanguageUtils.setLanguageWithoutReload("en", this)
                }
            }
        }.setNegativeButton(
            R.string.label_dismiss
        ) { dialog, _ ->
            dialog.dismiss()
        }.create().show()
    }


}