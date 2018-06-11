package asvid.github.io.roomapp.di.modules

import android.content.Context
import asvid.github.io.roomapp.App
import asvid.github.io.roomapp.di.components.MainActivityComponent
import asvid.github.io.roomapp.di.components.OwnersActivityComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [MainActivityComponent::class, OwnersActivityComponent::class])
class AppModule {

    @Provides
    internal fun context(application: App): Context {
        return application.applicationContext
    }
}