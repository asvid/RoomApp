package asvid.github.io.roomapp.di.components

import asvid.github.io.roomapp.App
import asvid.github.io.roomapp.di.ActivityBinder
import asvid.github.io.roomapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBinder::class,
    AppModule::class,
    MigrationsModule::class,
    DataModule::class,
    ServiceBuilderModule::class,
    FragmentBuilderModule::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }
}