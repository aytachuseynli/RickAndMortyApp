package com.aytachuseynli.rickandmortyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.aytachuseynli.rickandmortyapp.R
import com.aytachuseynli.rickandmortyapp.databinding.FragmentCharactersBinding
import com.aytachuseynli.rickandmortyapp.ui.adapter.CharacterAdapter
import com.aytachuseynli.rickandmortyapp.ui.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private lateinit var characterAdapter: CharacterAdapter
    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharactersBinding.bind(view)

        setupRecyclerView()

        charactersViewModel.characters.observe(viewLifecycleOwner) { characters ->
            characterAdapter.submitData(viewLifecycleOwner.lifecycle,characters)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvCharacters
        characterAdapter = CharacterAdapter()

        recyclerView.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = characterAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}