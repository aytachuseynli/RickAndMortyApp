package com.aytachuseynli.rickandmortyapp.ui.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aytachuseynli.rickandmortyapp.R
import com.aytachuseynli.rickandmortyapp.data.entity.Characters
import com.aytachuseynli.rickandmortyapp.data.entity.ResultData
import com.aytachuseynli.rickandmortyapp.databinding.ItemCharacterBinding
import com.bumptech.glide.Glide

class CharacterAdapter(private val listener: OnCharacterClickListener) :
    PagingDataAdapter<ResultData, CharacterAdapter.CharacterHolder>(CHARACTER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class CharacterHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: ResultData) {

            Glide.with(binding.root)
                .load(character.image)
                .into(binding.characterImg)


            binding.characterName.text = character.name
            binding.characterSpecie.text = character.species

            when(character.gender){
                "Female"-> binding.characterGender.setImageResource(R.drawable.ic_female)
                "Male"-> binding.characterGender.setImageResource(R.drawable.ic_male)
                "Genderless"-> binding.characterGender.setImageResource(R.drawable.ic_genderless)
                "Unknown"-> binding.characterGender.setImageResource(R.drawable.ic_undefined)
            }


            binding.root.setOnClickListener {
                listener.onClickCharacter(character , binding.characterImg)
            }
            binding.characterImg.transitionName = character.name
        }



    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<ResultData>() {
            override fun areItemsTheSame(oldItem: ResultData, newItem: ResultData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResultData, newItem: ResultData): Boolean {
                return oldItem == newItem
            }
        }
    }
}

interface OnCharacterClickListener{
    fun onClickCharacter(item: ResultData , image: ImageView)
}
