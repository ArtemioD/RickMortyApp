package org.example.project.di

import org.example.project.ui.detail.CharacterDetailViewModel
import org.example.project.ui.home.tabs.characters.CharactersViewModel
import org.example.project.ui.home.tabs.episodes.EpisodesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val iuModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterDetailViewModel)
}