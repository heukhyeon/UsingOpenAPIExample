package kr.evalon.usingopenapi

import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ApiTest {

    @Test
    fun loadProfile(){
        val testObserver = TestObserver<Any>()
        ApiService.unSplash()
            .loadProfile()
            .subscribe(testObserver)

        testObserver.await()
        testObserver.assertComplete()
    }
}