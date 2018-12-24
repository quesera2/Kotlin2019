package sera.sera.que.kotlin201x

import android.app.Application
import org.koin.android.ext.android.startKoin
import sera.sera.que.kotlin201x.di.applicationModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule))
    }
}