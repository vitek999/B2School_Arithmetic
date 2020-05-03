package com.test.b2schoolarithmetic.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.data.domain.user.RegisterUser
import com.test.b2schoolarithmetic.data.domain.user.UserType
import com.test.b2schoolarithmetic.databinding.RegistrationFragmentBinding
import com.test.b2schoolarithmetic.presentation.RegistrationViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

class RegistrationFragment : Fragment(), KodeinAware {

    private lateinit var binding: RegistrationFragmentBinding
    private val viewModel: RegistrationViewModel by viewModels {
        RegistrationViewModel.Factory(
            direct.instance()
        )
    }

    override lateinit var kodein: Kodein

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kodein = Kodein {
            extend((requireActivity().application as KodeinAware).kodein)
        }
        binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackNavigation()
        setupUserTypesSpinner()
        setupRegistrationButton()
        setupProgress()
        observeErrorEvent()
        observeRegistrationSuccess()
    }

    private fun setupUserTypesSpinner() {
        val list = listOf(UserType.STUDENT, UserType.PARENT, UserType.TEACHER)
        binding.userTypeSpinner.adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, list)
    }

    private fun setupProgress() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showProgress(it)
        })
    }

    private fun setupBackNavigation() {
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun observeErrorEvent() {
        viewModel.errorEvent.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeRegistrationSuccess() {
        viewModel.successRegistration.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigateUp()
                Snackbar.make(requireView(), R.string.success_registration, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun setupRegistrationButton() {
        binding.registerButton.setOnClickListener {
            val newUser = with(binding) {
                RegisterUser(
                    firstNameEditText.text.toString(),
                    lastNameEditText.text.toString(),
                    passwordEditText.text.toString(),
                    loginEditText.text.toString()
                )
            }
            val userType = binding.userTypeSpinner.selectedItem as UserType
            if(isValidData()) viewModel.registration(userType, newUser)
        }
    }

    private fun showProgress(state: Boolean) {
        fun hideView(viewState: Boolean): Int = if (viewState) View.GONE else View.VISIBLE

        with(binding) {
            registrationScrollView.visibility = hideView(state)
            progressBar.visibility = hideView(!state)
        }
    }

    private fun isValidData(): Boolean {
        var isValid = true

        if (binding.firstNameEditText.text.isNullOrBlank()) {
            binding.firstNameTextInputLayout.error = getString(R.string.field_cant_be_blank)
            isValid = false
        } else {
            binding.firstNameTextInputLayout.error = ""
        }

        if (binding.lastNameEditText.text.isNullOrBlank()) {
            binding.lastNameTextInputLayout.error = getString(R.string.field_cant_be_blank)
            isValid = false
        } else {
            binding.lastNameTextInputLayout.error = ""
        }

        if (binding.loginEditText.text.isNullOrBlank()) {
            binding.loginTextInputLayout.error = getString(R.string.field_cant_be_blank)
            isValid = false
        } else {
            binding.loginTextInputLayout.error = ""
        }

        if (binding.passwordEditText.text.isNullOrBlank()) {
            binding.passwordTextInputLayout.error = getString(R.string.field_cant_be_blank)
            isValid = false
        } else {
            binding.passwordTextInputLayout.error = ""
        }

        if(binding.repeatPasswordEditText.text.toString() != binding.passwordEditText.text.toString()) {
            binding.repeatPasswordTextInputLayout.error = "Пароли не совпадают"
            isValid = false
        } else {
            binding.repeatPasswordTextInputLayout.error = ""
        }

        if (isValid && binding.repeatPasswordEditText.text.isNullOrBlank()) {
            binding.repeatPasswordTextInputLayout.error = getString(R.string.field_cant_be_blank)
            isValid = false
        } else {
            binding.repeatPasswordTextInputLayout.error = ""
        }

        return isValid
    }
}