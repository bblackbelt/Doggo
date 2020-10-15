package com.doggo.di

import com.doggo.adapter.BreedsAdapter
import com.doggo.controllers.BreedsController
import com.doggo.domain.BreedsService
import org.koin.dsl.module

val breedsModule = module {
    factory<BreedsService> { BreedsService.Impl() }
    factory<BreedsController> { BreedsController.Impl(get(), get()) }
    factory<BreedsAdapter> { BreedsAdapter.Impl() }
}