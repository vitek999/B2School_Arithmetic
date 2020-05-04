package com.test.b2schoolarithmetic.ui.level

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.test.b2schoolarithmetic.data.remote.dto.AnswerDto
import com.test.b2schoolarithmetic.databinding.LevelFragmentBinding
import com.test.b2schoolarithmetic.presentation.LevelViewModel
import com.test.b2schoolarithmetic.ui.level.adapter.AnswersAdapter
import com.test.b2schoolarithmetic.ui.level.di.levelKodeinModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class LevelFragment : Fragment(), KodeinAware {
    private val args: LevelFragmentArgs by navArgs()
    override lateinit var kodein: Kodein
    private lateinit var binding: LevelFragmentBinding
    private val viewModel: LevelViewModel by viewModels { LevelViewModel.Factory(args.levelId,direct.instance()) }
    private lateinit var adapter: AnswersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LevelFragmentBinding.inflate(inflater, container, false)
        kodein = Kodein {
            extend((requireActivity().application as KodeinAware).kodein)
            import(levelKodeinModule)
            bind<Context>() with provider { this@LevelFragment.requireContext() }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTitle()
        setupProgress()
        setUpAnswerRecyclerView()
        observeCurrentQuestion()
        viewModel.fetchQuestion()
    }

    private fun observeCurrentQuestion() {
        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
            binding.questionTextView.text = it.text
            adapter.updateData(it.answers)
        })
    }

    private fun setUpAnswerRecyclerView() {
        adapter = AnswersAdapter()
        val gridLayoutManager: GridLayoutManager by instance<GridLayoutManager>()

        binding.answerButtonsRecyclerView.layoutManager = gridLayoutManager
        binding.answerButtonsRecyclerView.adapter = adapter
    }

    private fun setupTitle() {
        binding.levelToolbar.title = args.levelTitle
    }

    private fun setupProgress() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showProgress(it)
        })
    }

    private fun showProgress(state: Boolean) {
        fun hideView(viewState: Boolean): Int = if (viewState) View.GONE else View.VISIBLE

        with(binding) {
            progressBar.visibility = hideView(!state)
        }
    }
}