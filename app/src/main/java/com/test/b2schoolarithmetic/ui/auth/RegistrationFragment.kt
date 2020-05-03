package com.test.b2schoolarithmetic.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.b2schoolarithmetic.databinding.RegistrationFragmentBinding
import com.test.b2schoolarithmetic.presentation.RegistrationViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

class RegistrationFragment : Fragment(), KodeinAware {

    private lateinit var binding: RegistrationFragmentBinding
    private val viewModel : RegistrationViewModel by viewModels { RegistrationViewModel.Factory(direct.instance()) }

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
    }

    private fun setupBackNavigation() {
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}