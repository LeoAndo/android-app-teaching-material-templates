package com.example.m3basicsample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.m3basicsample.databinding.FragmentSecondBinding
import com.example.m3basicsample.viewmodels.SecondViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
internal class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args by navArgs<SecondFragmentArgs>()
    private val viewModel by viewModels<SecondViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)
        binding.textviewSecond.text = args.user.name + " : " + args.user.age
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}