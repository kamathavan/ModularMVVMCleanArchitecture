package com.app.test.coffeeapp.di

import com.app.test.coffee.data.repository.CoffeeRepositoryImpl
import com.app.test.coffee.data.source.CoffeeDataSource
import com.app.test.coffee.data.source.CoffeeDataSourceImpl
import com.app.test.coffee.domain.repository.CoffeeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCoffeeRepository(coffeeRepository: CoffeeRepositoryImpl): CoffeeRepository

    @Binds
    abstract fun bindCoffeeDataSource(coffeeDataSource: CoffeeDataSourceImpl): CoffeeDataSource

}