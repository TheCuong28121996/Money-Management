package com.money.management.utils

interface ViewHolderListener<T> {
    fun itemClicked(data: T, positon: Int)
}