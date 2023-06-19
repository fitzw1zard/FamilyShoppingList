package com.example.familyshoppinglist

import android.app.Application
import com.example.familyshoppinglist.di.DaggerApplicationComponent

class MainApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}