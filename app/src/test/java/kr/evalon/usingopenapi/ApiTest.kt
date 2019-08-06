package kr.evalon.usingopenapi

import io.reactivex.observers.TestObserver
import kr.evalon.usingopenapi.api.ApiService
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

@RunWith(RobolectricTestRunner::class)
class ApiTest {

    @Test
    fun loadPhoto(){
        ShadowLog.stream = System.out

        val testObserver = TestObserver<Response<List<ModelUnsplashPhoto>>>()
        ApiService.unSplash()
            .loadPhoto()
            .subscribe(testObserver)

        testObserver.await()
        testObserver.assertComplete()
        val response = testObserver.values().first()
        println("Response Accept")
        response.headers().toJson().apply {
            println(this)
        }
        response.body()?.toJson()?.apply {
            println(this)
        }

    }
}