package com.app.test.coffee.data.repository


import com.app.test.coffee.data.mapper.CoffeeDataMapper
import com.app.test.coffee.data.source.CoffeeDataSource
import com.app.test.coffee.domain.model.Coffee
import com.app.test.coffee.domain.model.RequestState
import com.app.test.coffee.domain.repository.CoffeeRepository
import javax.inject.Inject


class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeDataSource: CoffeeDataSource,
    private val coffeeDataMapper: CoffeeDataMapper,
) : CoffeeRepository {
    override suspend fun getCoffees(): RequestState<List<Coffee>> {
        return try {
            val dataList = coffeeDataSource.getCoffeeData()
            RequestState.SuccessState(
                data = coffeeDataMapper.transformCoffeeData(coffeeResponse = dataList)
            )
        } catch (e: Exception) {
            RequestState.FailureState(error = e)
        }
    }
}
