package com.faizal.newsapp.common.utils


sealed class UIComponent {

    data class Toast(val message: String): UIComponent()

    data class Dialog(val title: String, val message: String): UIComponent()

    data class None(val message: String? = null): UIComponent()

}