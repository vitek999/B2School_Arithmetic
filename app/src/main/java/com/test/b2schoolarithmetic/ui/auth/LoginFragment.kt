package com.test.b2schoolarithmetic.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.databinding.LoginFragmentBinding
import com.test.b2schoolarithmetic.presentation.LoginViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

class LoginFragment : Fragment(), KodeinAware {

    private val viewModel: LoginViewModel by viewModels { LoginViewModel.Factory(direct.instance()) }
    private lateinit var binding: LoginFragmentBinding

    override lateinit var  kodein: Kodein

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kodein = Kodein {
            extend((requireActivity().application as KodeinAware).kodein)
        }
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginButton()
        setupProgressBar()
        setupRegistrationButtoon()
        observeErrorEvent()
        observeAuthorizedEvent()
    }

    private fun observeErrorEvent() {
        viewModel.errorEvent.observe(viewLifecycleOwner, Observer {event ->
            event.getContentIfNotHandled()?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeAuthorizedEvent() {
        viewModel.authorizedGameEvent.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let {

            }
        })
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            viewModel.login(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun setupRegistrationButtoon() {
        binding.registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun setupProgressBar() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { showProgress(it) })
    }

    private fun showProgress(state: Boolean) {
        fun hideView(viewState: Boolean): Int = if (viewState) View.GONE else View.VISIBLE

        with(binding) {
            imageView.visibility = hideView(state)
            titleTextView.visibility = hideView(state)
            loginTextInputLayout.visibility = hideView(state)
            passwordTextInputLayout.visibility = hideView(state)
            loginButton.visibility = hideView(state)
            registerTextView.visibility = hideView(state)
            progressBar.visibility = hideView(!state)
        }
    }
}