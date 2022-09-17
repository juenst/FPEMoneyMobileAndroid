package lib.finpay.sdk.presentation.common

import lib.finpay.sdk.domain.exception.Failure

/*
 *  Created by Hanantadk on 28/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 28/05/20.
 */
sealed class ResultState<out T> {
    class OnLoading<out T> : ResultState<T>()
    data class OnSuccess<out T> (val data: T) : ResultState<T>()
    data class OnError<out T> (val error: Failure) : ResultState<T>()
}