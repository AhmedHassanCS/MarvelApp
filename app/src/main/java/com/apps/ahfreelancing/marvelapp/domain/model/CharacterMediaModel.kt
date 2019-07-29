package com.apps.ahfreelancing.marvelapp.domain.model

/**
 * Created by Ahmed Hassan on 7/28/2019.
 */
data class CharacterMediaModel (
    val comics: List<MediaModel>,
    val series: List<MediaModel>,
    val stories: List<MediaModel>,
    val events: List<MediaModel>
)