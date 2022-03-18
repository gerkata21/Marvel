package com.sample.data

import com.sample.domain.model.Character
import com.sample.domain.model.Thumbnail
import com.sample.network.model.character.CharacterRemote
import com.sample.network.model.character.ThumbnailRemote

fun List<CharacterRemote>.mapToCharacterList(): List<Character> {
    return map { characterRemote ->
        Character(
            characterRemote.id,
            characterRemote.name,
            characterRemote.description,
            characterRemote.thumbnail?.mapToThumbnail()
        )
    }
}

fun ThumbnailRemote.mapToThumbnail(): Thumbnail {
    return Thumbnail(path = path, extension = extension)
}