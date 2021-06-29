package com.dmitry.molchanov.kmmjsonxmlparsesample

import com.dmitry.molchanov.kmmjsonxmlparsesample.db.DriverFactory
import com.dmitry.molchanov.kmmjsonxmlparsesample.viewmodel.MainViewModel

class IosViewModel: MainViewModel(DriverFactory().createDriver()) {
}