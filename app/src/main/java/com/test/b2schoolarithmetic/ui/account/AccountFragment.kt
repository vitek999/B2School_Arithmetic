package com.test.b2schoolarithmetic.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.test.b2schoolarithmetic.databinding.AccountFragmentBinding
import com.test.b2schoolarithmetic.presentation.AccountViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

class AccountFragment : Fragment(), KodeinAware {
    override lateinit var kodein: Kodein
    private lateinit var binding: AccountFragmentBinding
    private val viewModel: AccountViewModel by viewModels { AccountViewModel.Factory(direct.instance()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AccountFragmentBinding.inflate(inflater, container, false)
        kodein = Kodein {
            extend((requireActivity().application as KodeinAware).kodein)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProgressBar()
        observeUser()
        viewModel.fetchAccountInfo()
    }

    private fun setupProgressBar() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showProgress(it)
        })
    }

    private fun observeUser() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.fioTextView.text = "${it.lastName} ${it.firstName}"
            binding.accountToolbar.title = it.userName
            binding.pointsTextView.text = it.points.toString()
        })
    }

    private fun showProgress(state: Boolean) {
        fun hideView(viewState: Boolean): Int = if (viewState) View.GONE else View.VISIBLE

        with(binding) {
            addButton.visibility = hideView(state)
            imageView3.visibility = hideView(state)
            imageView7.visibility = hideView(state)
            pointsTextView.visibility = hideView(state)
            fioTextView.visibility = hideView(state)
            accountToolbar.visibility = hideView(state)
            progressBar.visibility = hideView(!state)
        }
    }
}