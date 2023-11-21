package com.example.stresscoping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.stresscoping.databinding.FragmentStressCopingBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StressCopingFragment : Fragment() {

    private var _binding: FragmentStressCopingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val copings = arrayOf("サウナ", "甘い物を食べる", "寝る", "散歩", "読書")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStressCopingBinding.inflate(inflater, container, false)
        binding.buttonStressCoping.setOnClickListener { clickButtonStressCoping() }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickButtonStressCoping() {
        binding.textviewStressCoping.text = copings.random()
    }
}