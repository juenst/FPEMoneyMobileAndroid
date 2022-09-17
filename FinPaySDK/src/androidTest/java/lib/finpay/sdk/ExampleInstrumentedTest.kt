package lib.finpay.sdk

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("lib.finpay.sdk.test", appContext.packageName)

        val tokenID = FinPaySDK().getToken(
            userName = "MT77764DKM83N",
            password = "YJV3AM0y",
            secretKey = "daYumnMb",
            phoneNumber = "083815613839",
            transNumber = "TRX1234567890"
        )
        assertEquals("test", tokenID)
    }
}