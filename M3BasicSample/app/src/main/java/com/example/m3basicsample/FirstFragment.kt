package com.example.m3basicsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.m3basicsample.databinding.FragmentFirstBinding
import com.example.m3basicsample.models.User

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
internal class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    User("Yamada", 20)
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}