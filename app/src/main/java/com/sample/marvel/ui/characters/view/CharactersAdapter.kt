package com.sample.marvel.ui.characters.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.marvel.R
import com.sample.marvel.framework.base.BaseViewHolder
import com.sample.marvel.ui.characters.model.CharacterUiModel

const val ITEM_CHARACTER = 0
const val ITEM_SEARCH = 1

class CharactersAdapter(
    private var charactersList: List<CharacterUiModel>,
    private val itemType: Int
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view: View
        return if (viewType == ITEM_CHARACTER) {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
            CharactersWithGifViewHolder(view, charactersList)
        } else {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
            CharactersWithImageViewHolder(view, charactersList)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindViews(position)
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    fun updateList(charactersList: List<CharacterUiModel>) {
        // TODO: 10/7/2020  use diff util
        this.charactersList = charactersList
        notifyDataSetChanged()
    }
}
