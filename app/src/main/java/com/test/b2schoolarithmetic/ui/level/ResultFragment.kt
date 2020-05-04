package com.test.b2schoolarithmetic.ui.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.databinding.ResultFragmentBinding

class ResultFragment : Fragment() {

    private val args: ResultFragmentArgs by navArgs()
    private lateinit var binding: ResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resultImageView.setImageResource(if(args.result) R.drawable.ic_good_test else R.drawable.ic_bad_test)
        binding.resultTextView.setText(if(args.result) R.string.good_result else R.string.bad_result)
        binding.goBackButton.setOnClickListener { findNavController().navigateUp() }
    }
}