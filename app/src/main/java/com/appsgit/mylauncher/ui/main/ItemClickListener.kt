package com.appsgit.mylauncher.ui.main

import com.appsgit.mylauncher.model.AppModel

interface ItemClickListener {
    fun onItemClick(app: AppModel?)
}