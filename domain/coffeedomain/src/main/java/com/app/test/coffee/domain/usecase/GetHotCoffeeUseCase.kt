package com.app.test.coffee.domain.usecase

import com.app.test.coffee.domain.model.Coffee
import com.app.test.coffee.domain.model.RequestState
import com.app.test.coffee.domain.repository.CoffeeRepository
import javax.inject.Inject

class GetHotCoffeeUseCase @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) {
    suspend operator fun invoke(): RequestState<List<Coffee>> =  coffeeRepository.getCoffees()
}