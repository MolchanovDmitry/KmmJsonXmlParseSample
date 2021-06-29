package com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel

import android.content.Context
import com.dmitry.molchanov.kmmjsonxmlparsesample.db.DriverFactory

class AndroidMainViewModel(context: Context) :
    MainViewModel(sqlDriver = DriverFactory(context).createDriver())