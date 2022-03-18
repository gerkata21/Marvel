package com.sample.marvel.ui.characters.model

import com.sample.domain.model.Character

fun List<Character>.mapToCharacterUiList(): List<CharacterUiModel> {
    return map { characterEntity ->
        CharacterUiModel(
            characterEntity.id,
            characterEntity.name,
            characterEntity.description,
            characterEntity.thumbnail?.mapToThumbnailUiModel()
        )
    }
}

fun List<CharacterUiModel>.mapToCharacterList(): List<Character> {
    return map { character ->
        Character(
            id = character.id,
            name = character.name,
            description = character.description,
            thumbnail = character.thumbnailUiModel?.mapToThumbnail()
        )
    }
}