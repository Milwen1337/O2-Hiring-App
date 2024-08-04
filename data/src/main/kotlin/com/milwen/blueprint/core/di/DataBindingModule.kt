package com.milwen.blueprint.core.di

import com.milwen.blueprint.core.repository.ScratchCardRepositoryImpl
import com.milwen.blueprint.repository.ScratchCardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataBindingModule {

    @Binds
    fun bindScratchCardRepository(repository: ScratchCardRepositoryImpl): ScratchCardRepository
}
