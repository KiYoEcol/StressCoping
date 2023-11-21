package com.example.stresscoping

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stresscoping.databinding.FragmentStressCopingListBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class StressCopingListFragment : Fragment() {

    private var _binding: FragmentStressCopingListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStressCopingListBinding.inflate(inflater, container, false)
        binding.fabAddStressCoping.setOnClickListener {
            val context: Context = context ?: return@setOnClickListener
            val editText = AppCompatEditText(context)
            AlertDialog.Builder(context)
                .setTitle("追加")
                .setMessage("ストレスコーピングを入力してください。")
                .setView(editText)
                .setPositiveButton("OK") { dialog, _ ->
                    // OKボタンを押したときの処理
                    dialog.dismiss()
                }
                .setNegativeButton("キャンセル") { dialog, _ ->
                    // キャンセルボタンを押したときの処理
                    dialog.dismiss()
                }
                .create()
                .show()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewStressCopingList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter =
                StressCopingListViewAdapter((activity as MainActivity).stressCopingModels, null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerviewStressCopingList.adapter = null
        _binding = null
    }
}