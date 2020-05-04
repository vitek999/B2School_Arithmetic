package com.test.b2schoolarithmetic.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.b2schoolarithmetic.databinding.MainFragmentBinding
import com.test.b2schoolarithmetic.presentation.MainViewModel
import com.test.b2schoolarithmetic.ui.main.adapter.LevelsListAdapter
import com.test.b2schoolarithmetic.ui.main.di.levelsKodeinModule
import com.test.b2schoolarithmetic.ui.main.vo.LevelListItem
import com.test.b2schoolarithmetic.ui.main.vo.ListItem
import com.test.b2schoolarithmetic.ui.main.vo.ThemeHeader
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

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
            import(levelsKodeinModule)
            bind<Context>() with provider { this@MainFragment.requireContext() }
        }
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProgressBar()
        setupLevelsRecyclerView()
        observeErrorEvent()
        observeThemes()
        viewModel.fetchThemes()
    }

    private fun setupLevelsRecyclerView() {
        val levelsListAdapter: LevelsListAdapter by instance<LevelsListAdapter>()
        val linearLayoutManager: LinearLayoutManager by instance<LinearLayoutManager>()

        binding.levelsRecyclerView.adapter = levelsListAdapter
        binding.levelsRecyclerView.layoutManager = linearLayoutManager
    }

    private fun setupProgressBar() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            showProgress(it)
        })
    }

    private fun observeErrorEvent() {
        viewModel.errorEvent.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeThemes() {
        viewModel.themes.observe(viewLifecycleOwner, Observer {
            val levelsListAdapter: LevelsListAdapter by instance<LevelsListAdapter>()

            val newData: MutableList<ListItem> = mutableListOf()

            for(theme in it) {
                newData.add(ThemeHeader(theme.name, theme.description))
                newData.addAll(theme.levels.map { level -> LevelListItem(level.id, level.name, "desc", true, true) })
            }

            levelsListAdapter.updateData(newData)
        })
    }

    private fun showProgress(state: Boolean) {
        fun hideView(viewState: Boolean): Int = if (viewState) View.GONE else View.VISIBLE

        with(binding) {
            progressBar.visibility = hideView(!state)
        }
    }
}