package com.ozantopuz.dicetask.di

import com.ozantopuz.dicetask.domain.usecase.GetArtistsUseCase
import com.ozantopuz.dicetask.domain.usecase.GetReleasesUseCase
import com.ozantopuz.dicetask.util.usecase.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetArtistsUseCase(useCase: GetArtistsUseCase): UseCase

    @Binds
    abstract fun bindGetReleasesUseCase(useCase: GetReleasesUseCase): UseCase
}