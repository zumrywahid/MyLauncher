package com.appsgit.mylauncher.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.appsgit.mylauncher.model.AppModel

class AppUtil {
    companion object {
        fun getInstalledApps(intent: Intent, appContext: Context) : List<AppModel> {
            val list = arrayListOf<AppModel>()
            appContext.packageManager.queryIntentActivities(intent, 0).forEach {
                list.add(
                    AppModel(
                        appName = it.activityInfo.loadLabel(appContext.packageManager).toString(),
                        packageName = it.activityInfo.packageName,
                        appImage = it.activityInfo.loadIcon(appContext.packageManager)
                    )
                )
            }
            return list
        }

        fun launchApp(appContext: Context, app: AppModel) {
            appContext
                .applicationContext
                .packageManager
                .getLaunchIntentForPackage(app.packageName)
                ?.let {
                    appContext.startActivity(it)
            }
        }
    }
}