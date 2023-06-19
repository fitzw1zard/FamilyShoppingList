package com.example.familyshoppinglist.di

import android.app.Application
import com.example.familyshoppinglist.MainActivity
import com.example.familyshoppinglist.presentation.view.ChangeNoteFragment
import com.example.familyshoppinglist.presentation.view.NotesFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: ChangeNoteFragment)

    fun inject(fragment: NotesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}