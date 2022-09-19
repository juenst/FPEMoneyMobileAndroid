package com.finpay.wallet.base.`interface`

import dagger.Module

interface ToolbarInterface {
    fun initToolbar()
    fun newNotification(totalNotification: Int)
    fun clearNotification()
}