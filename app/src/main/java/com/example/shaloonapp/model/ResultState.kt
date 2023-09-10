package com.example.shaloonapp.model

sealed class ResultState<T:Any>{
    class Loading<X:Any> : ResultState<X>()
    class Success <T:Any> (val body: T?) : ResultState<T>()
    class  Error <T : Any>(val errorMessage: String) : ResultState<T>()
    class  Exception <T : Any>(val exception: kotlin.Exception) : ResultState<T>()
}
