package com.sample.marvel.framework.data.mapper

import com.sample.domain.model.Character
import com.sample.marvel.framework.data.db.entity.CharacterEntity

fun List<CharacterEntity>.mapToCharacterList(): List<Character> {
    return map { characterEntity ->
        Character(
            characterEntity.id,
            characterEntity.name,
            characterEntity.description,
            characterEntity.thumbnail?.mapToThumbnail()
        )
    }
}

fun List<Character>.mapToCharacterEntityList(): List<CharacterEntity> {
    return map { character ->
        CharacterEntity(
            id = character.id,
            name = character.name,
            description = character.description,
            thumbnail = character.thumbnail?.mapToThumbnailEntity()
        )
    }
}