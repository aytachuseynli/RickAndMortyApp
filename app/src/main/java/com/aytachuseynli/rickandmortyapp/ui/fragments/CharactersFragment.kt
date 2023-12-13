package com.aytachuseynli.rickandmortyapp.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.aytachuseynli.rickandmortyapp.data.entity.ResultData
import com.aytachuseynli.rickandmortyapp.databinding.FragmentCharactersBinding
import com.aytachuseynli.rickandmortyapp.ui.adapter.CharacterAdapter
import com.aytachuseynli.rickandmortyapp.ui.adapter.FilterAdapter
import com.aytachuseynli.rickandmortyapp.ui.adapter.OnCharacterClickListener
import com.aytachuseynli.rickandmortyapp.ui.adapter.OnFilterClick
import com.aytachuseynli.rickandmortyapp.ui.viewmodel.CharactersViewModel
import com.aytachuseynli.rickandmortyapp.ui.viewmodel.GenderType
import com.aytachuseynli.rickandmortyapp.ui.viewmodel.SpeciesType
import com.aytachuseynli.rickandmortyapp.ui.viewmodel.StatusType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding

    private val filterAdapter by lazy {
        FilterAdapter(requireContext().resources, object : OnFilterClick {
            override fun onClickGender(genderType: GenderType) {
                characterViewModel.updateGender(genderType)
                observeCharacterData()
            }
            override fun onClickStatus(statusType: StatusType) {
                characterViewModel.updateStatus(statusType)
                observeCharacterData()
            }
            override fun onCLickSpecies(speciesType: SpeciesType) {
                characterViewModel.updateSpecies(speciesType)
                observeCharacterData()
            }
        })
    }
    private val characterAdapter by lazy {
        CharacterAdapter(object : OnCharacterClickListener {
            override fun onClickCharacter(item: ResultData , image : ImageView) {

                val extras = FragmentNavigatorExtras(
                    image to item.image!!
                )
                findNavController().navigate(
                    CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(item) , extras
                )


            }
        })
    }
    private val characterViewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFilter.adapter = filterAdapter
        binding.rvCharacters.adapter = characterAdapter

        observeCharacterData()

        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            lifecycleScope.launch {
                delay(500)
                characterViewModel.updateName(text.toString())
            }

        }

        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation

    }
    private fun observeCharacterData() {
        lifecycleScope.launch {
            characterViewModel.characterPagingData
                .flowWithLifecycle(lifecycle).collectLatest {
                    characterAdapter.submitData(it)
                }
        }

        lifecycleScope.launch {
            characterViewModel.filterState.flowWithLifecycle(lifecycle).collectLatest {
                Log.e("TEST 3 ", "observeCharacterData: $it", )
                characterViewModel.getCharacters(
                    name = it.name,
                    gender= it.gender,
                    status = it.status,
                    species = it.species
                )
            }
        }
    }
}