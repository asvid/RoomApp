package asvid.github.io.roomapp.di

import asvid.github.io.roomapp.views.owners.AddOwnerDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeaddOwnerDialog(): AddOwnerDialog

}