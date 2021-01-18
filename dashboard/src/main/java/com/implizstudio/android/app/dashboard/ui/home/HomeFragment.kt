package com.implizstudio.android.app.dashboard.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.implizstudio.android.app.androidwarungkuowner.data.model.constant.Constant
import com.implizstudio.android.app.androidwarungkuowner.data.repository.dashboard.DashboardRepositoryImpl
import com.implizstudio.android.app.androidwarungkuowner.di.feature.DashboardDependency
import com.implizstudio.android.app.dashboard.databinding.FragmentHomeBinding
import com.implizstudio.android.app.dashboard.di.DaggerDashboardComponent
import com.implizstudio.android.app.dashboard.ui.adapter.TipAdapter
import com.implizstudio.android.app.resources.base.FragmentBase
import com.implizstudio.android.app.resources.extension.currency
import com.implizstudio.android.app.resources.extension.getDataFromSharedPreference
import com.implizstudio.android.app.resources.extension.startActivityModule
import com.implizstudio.android.app.resources.util.NavigationModule
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class HomeFragment : FragmentBase<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var dashboardRepositoryImpl: DashboardRepositoryImpl

    override fun onAttach(context: Context) {
        DaggerDashboardComponent.builder()
            .context(context)
            .appDependency(
                EntryPointAccessors.fromApplication(
                    context,
                    DashboardDependency::class.java
                )
            ).build()
            .inject(this)

        super.onAttach(context)
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onCreated(savedInstanceState: Bundle?) {

        viewModel.apply {

            getDashboard(
                dashboardRepositoryImpl,
                requireActivity().getDataFromSharedPreference(Constant.Key.ACCOUNT_ID, "")
            ).observe(viewLifecycleOwner, { dashboard ->
                getBinding()?.apply {

                    tvDashboardIncome.currency(dashboard.incomeToday)

                    tvDashboardProfit.currency(dashboard.profitToday)

                    tvDashboardPercentage.text = dashboard.percentage

                    rvHomeTip.let {
                        it.layoutManager = LinearLayoutManager(
                            requireActivity(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        it.adapter = TipAdapter(requireActivity(), dashboard.tips)
                    }

                }
            })

        }

        getBinding()?.apply {

            cfHomeProduct.setOnClickListener {
                requireActivity().startActivityModule(NavigationModule.GOTO_PRODUCT)
            }

        }

    }

}
