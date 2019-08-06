package kr.evalon.usingopenapi

import io.reactivex.observers.TestObserver
import kr.evalon.usingopenapi.api.ApiService
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
class ApiTest {

    @Test
    fun loadPhoto(){
        ShadowLog.stream = System.out

        val testObserver = TestObserver<List<ModelUnsplashPhoto>>()
        ApiService.unSplash()
            .loadPhoto()
            .subscribe(testObserver)

        testObserver.await()
        testObserver.assertComplete()
        testObserver.values().first().toJson().apply {
            println(this)
        }
    }
}