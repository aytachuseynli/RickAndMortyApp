package com.aytachuseynli.rickandmortyapp.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.aytachuseynli.rickandmortyapp.R
import com.aytachuseynli.rickandmortyapp.data.entity.ResultData
import com.aytachuseynli.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            with(binding) {
                detailImg.load(it.getString("image"))
                detailName.text = it.getString("name")
                genderTxt.text = it.getString("gender")
                statusTxt.text = it.getString("status")
                speciesTxt.text = it.getString("species")
                typeTxt.text = it.getString("type")
                originTxt.text = it.getString("origin")
            }
        }

        val character = arguments?.getSerializable("character") as? ResultData
        if (character != null) {
            populateCharacterDetails(character)
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun populateCharacterDetails(character: ResultData) {
        binding.apply {
            detailName.text = character.name
            statusTxt.text = character.status
            speciesTxt.text = character.species
            typeTxt.text = character.type
            originTxt.text = character.origin?.name

            Glide.with(requireContext())
                .load(character.image)
                .into(detailImg)
        }
    }


}