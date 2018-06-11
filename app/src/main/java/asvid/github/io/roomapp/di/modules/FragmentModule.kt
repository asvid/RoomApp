package asvid.github.io.roomapp.di.modules

import asvid.github.io.roomapp.views.gists.AddGistDialog
import asvid.github.io.roomapp.views.owners.AddOwnerDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeAddOwnerDialog(): AddOwnerDialog

    @ContributesAndroidInjector
    internal abstract fun contributeAddGistDialog(): AddGistDialog

}