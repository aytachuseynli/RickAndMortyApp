package com.aytachuseynli.rickandmortyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.aytachuseynli.rickandmortyapp.R
import com.aytachuseynli.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterDetailBinding.bind(view)

        // Your fragment initialization code here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}