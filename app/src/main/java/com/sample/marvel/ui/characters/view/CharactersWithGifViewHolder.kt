package com.sample.marvel.ui.characters.view

import android.view.View
import com.bumptech.glide.Glide
import com.sample.marvel.R
import com.sample.marvel.framework.base.BaseViewHolder
import com.sample.marvel.ui.characters.model.CharacterUiModel
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersWithGifViewHolder(itemView: View, val charactersList: List<CharacterUiModel>) :
    BaseViewHolder(itemView) {

    override fun bindViews(position: Int) {
        val character = charactersList[position]
        itemView.character_name_tv.text = character.name

        val characterImageURL = String.format(
            "%s.%s",
            character.thumbnailUiModel!!.path,
            character.thumbnailUiModel!!.extension
        ).replace("http://", "https://")

        if (characterImageURL.contains("gif", true)) {
            displayGif(characterImageURL)
        } else {
            displayImage(characterImageURL)
        }

    }

    override fun clear() {
        itemView.character_name_tv.text = ""
    }

    override fun displayImage(
        image: String
    ) {
        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(image)
            .error(R.drawable.placeholder)
            .into(itemView.thumbnail_img_view)

    }

    override fun displayGif(
        image: String
    ) {
        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .asGif()
            .load(image)
            .error(R.drawable.placeholder)
            .into(itemView.thumbnail_img_view)
    }

}