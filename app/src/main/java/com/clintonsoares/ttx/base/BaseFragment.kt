package com.clintonsoares.ttx.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.clintonsoares.ttx.R
import com.clintonsoares.ttx.common.hideKeyboard
import com.clintonsoares.ttx.databinding.LayoutProgressBinding
import com.clintonsoares.ttx.utils.*

abstract class BaseFragment<out T : ViewDataBinding>(
    @LayoutRes val layout: Int
) : Fragment() {

    var toolbarTitle: Int? = null

    constructor(@LayoutRes layout: Int, @StringRes toolbarTitle: Int) : this(layout) {
        this.toolbarTitle = toolbarTitle
    }

    val binding: T by lazy {
        DataBindingUtil.inflate(layoutInflater, layout, null, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolbarTitle?.apply { setToolbarTitle(this) }
        setHasOptionsMenu(true)
        attachBinding()
        initVMDependency()
        return binding.root
    }

    open fun initVMDependency() {
        getVM().bus.observe(viewLifecycleOwner, { event ->
            when (event) {
                is ToastEvent -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is ErrorEvent -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is SuccessEvent -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is NoInternetEvent -> {
                    Toast.makeText(
                        context,
                        getString(R.string.no_internet_available),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is CloseKeyboardEvent -> {
                    hideKeyboard()
                }
                else -> {
                    handleEvent(event)
                }
            }
        })

    }

    fun <T> getSavedState(key: String): MutableLiveData<T>? {
        return findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
    }

    open fun getProgessBinging(): LayoutProgressBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_progress,
            null,
            false
        )

    open fun attachBinding() {
    }

    abstract fun getVM(): BaseViewModel

    open fun handleEvent(event: BaseEvent) {

    }

    fun setToolbarTitle(@StringRes title: Int) {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).supportActionBar?.setTitle(title)
        }
    }

    override fun onPause() {
        hideKeyboard()
        super.onPause()
    }

}