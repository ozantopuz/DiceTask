package com.ozantopuz.dicetask.util.usecase

import com.ozantopuz.dicetask.data.Result

interface UseCase {

    @FunctionalInterface
    interface FlowUseCase<in P, out T> : UseCase where P : Params {
        suspend fun execute(params: P): Result<T>
    }
}

abstract class Params