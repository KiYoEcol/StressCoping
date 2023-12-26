package com.example.stresscoping.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.stresscoping.R
import com.example.stresscoping.viewmodel.StressCopingViewModel
import com.example.stresscoping.databinding.FragmentStressCopingBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StressCopingFragment : Fragment() {

    private lateinit var binding: FragmentStressCopingBinding
    private val viewModel: StressCopingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStressCopingBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStressCoping.setOnClickListener {
            if (viewModel.btnStressCoping.value == getString(R.string.btn_start)) {
                viewModel.clickStart()
            } else {
                viewModel.clickStop()
            }
        }
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        viewModel.run {
            stressCopings.observe(viewLifecycleOwner) {}
        }
    }

    override fun onPause() {
        viewModel.stopStressCopingIfChangingText()
        super.onPause()
    }
}