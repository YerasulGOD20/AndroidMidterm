package com.example.aviatickets.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aviatickets.R
import com.example.aviatickets.adapter.OfferListAdapter
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.service.FakeService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.network.ApiClient
import com.example.aviatickets.model.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OfferListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var offerListAdapter: OfferListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_offer_list, container, false)

        recyclerView = rootView.findViewById(R.id.offer_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        offerListAdapter = OfferListAdapter()
        recyclerView.adapter = offerListAdapter

        fetchOfferList()

        return rootView
    }

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        adapter.setItems(FakeService.offerList)
    }
    private fun fetchOfferList() {
        val apiService = ApiClient.getRetrofitInstance().create(ApiService::class.java)
        val call = apiService.getOfferList()
        call.enqueue(object : Callback<List<Offer>> {
            override fun onResponse(call: Call<List<Offer>>, response: Response<List<Offer>>) {
                if (response.isSuccessful) {
                    val offers = response.body()
                    offerListAdapter.setOffers(offers)
                } else {
                    // Handle errors
                }
            }

            override fun onFailure(call: Call<List<Offer>>, t: Throwable) {
                // Handle network failures
            }
        })
    }

    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        /**
                         * implement sorting by price
                         */
                    }

                    R.id.sort_by_duration -> {
                        /**
                         * implement sorting by duration
                         */
                    }
                }
            }
        }
    }
}