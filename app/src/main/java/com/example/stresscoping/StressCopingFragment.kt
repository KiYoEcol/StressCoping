package com.example.stresscoping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        binding.buttonStressCoping.setOnClickListener { viewModel.clickChoose() }
        binding.buttonFirst.setOnClickListener { findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment) }
    }
}