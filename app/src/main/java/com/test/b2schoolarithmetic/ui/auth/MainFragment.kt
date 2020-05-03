package com.test.b2schoolarithmetic.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.b2schoolarithmetic.databinding.MainFragmentBinding
import com.test.b2schoolarithmetic.presentation.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

class MainFragment : Fragment(), KodeinAware {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory(direct.instance()) }
    override lateinit var kodein: Kodein

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kodein = Kodein {
            extend((requireActivity().application as KodeinAware).kodein)
        }
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchThemes()
    }
}