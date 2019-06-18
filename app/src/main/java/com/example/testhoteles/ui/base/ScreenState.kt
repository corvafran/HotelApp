package com.example.testhoteles.ui.base

sealed class ScreenState {
    class Error(val error : String) : ScreenState()
    class ToastMessage(val message: String) : ScreenState()
    class Loading(val show : Boolean) : ScreenState()
}
