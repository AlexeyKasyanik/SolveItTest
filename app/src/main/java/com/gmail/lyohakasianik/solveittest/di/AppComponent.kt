package com.gmail.lyohakasianik.solveittest.di

import android.content.Context
import com.gmail.lyohakasianik.solveittest.ui.PersonFragment
import com.gmail.lyohakasianik.solveittest.ui.listPerson.ListPersonFragment
import com.gmail.lyohakasianik.solveittest.ui.specialty.ListSpecialtyFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class, FactoryModule::class])
@ExperimentalStdlibApi
@Singleton
interface AppComponent {

    fun inject(listSpecialtyFragment: ListSpecialtyFragment)

    fun inject(listPersonFragment: ListPersonFragment)

    fun inject(personFragment: PersonFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(context: Context): Builder
    }
}