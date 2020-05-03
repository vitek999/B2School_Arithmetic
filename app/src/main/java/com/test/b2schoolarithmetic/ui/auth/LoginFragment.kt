package com.test.b2schoolarithmetic.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        setUpLoginButton()
    }

    private fun setUpLoginButton() {
        binding.loginButton.setOnClickListener {
            viewModel.login(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }
}