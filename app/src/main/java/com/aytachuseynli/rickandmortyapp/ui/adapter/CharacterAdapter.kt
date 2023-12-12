package com.aytachuseynli.rickandmortyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aytachuseynli.rickandmortyapp.data.entity.ResultData
import com.aytachuseynli.rickandmortyapp.databinding.ItemCharacterBinding
import com.bumptech.glide.Glide

object CharacterDiffCallback : DiffUtil.ItemCallback<ResultData>() {
    override fun areItemsTheSame(oldItem: ResultData, newItem: ResultData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultData, newItem: ResultData): Boolean {
        return oldItem == newItem
    }
}


class CharacterAdapter : PagingDataAdapter<ResultData, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback) {

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: ResultData) {
            binding.apply {

                 characterName.text = character.name
                 characterSpecie.text = character.species

                 Glide
                     .with(itemView.context)
                     .load(character.image)
                     .into(characterImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character!!)
    }


}