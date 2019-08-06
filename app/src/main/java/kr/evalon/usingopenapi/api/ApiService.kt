package kr.evalon.usingopenapi.api

import kr.evalon.usingopenapi.api.unsplash.ApiUnsplash

object ApiService {

    fun unSplash() = create(ApiUnsplash.Companion::create)

    private val buffer = HashMap<Class<*>,Any>()

    private inline fun<reified T : Any> create(createFunc:()->T):T{
        if(buffer[T::class.java] == null){
            buffer[T::class.java] = createFunc()
        }

        return requireNotNull(buffer[T::class.java] as T)
    }
}