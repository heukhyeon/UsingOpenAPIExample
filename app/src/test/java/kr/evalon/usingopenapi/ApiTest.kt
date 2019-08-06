package kr.evalon.usingopenapi

import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
class ApiTest {

    @Test
    fun loadPhoto(){
        ShadowLog.stream = System.out

        val testObserver = TestObserver<Any>()
        ApiService.unSplash()
            .loadPhoto()
            .subscribe(testObserver)

        testObserver.await()
        testObserver.assertComplete()
    }
}