package com.test.b2schoolarithmetic.ui.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.test.b2schoolarithmetic.databinding.LevelFragmentBinding
import com.test.b2schoolarithmetic.presentation.LevelViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.instance

class LevelFragment : Fragment(), KodeinAware {
    private val args: LevelFragmentArgs by navArgs()
    override lateinit var kodein: Kodein
    private lateinit var binding: LevelFragmentBinding
    private val viewModel: LevelViewModel by viewModels { LevelViewModel.Factory(args.levelId,direct.instance()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LevelFragmentBinding.inflate(inflater, container, false)
        kodein = Kodein {
            extend((requireActivity().application as KodeinAware).kodein)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchQuestion()
    }
}