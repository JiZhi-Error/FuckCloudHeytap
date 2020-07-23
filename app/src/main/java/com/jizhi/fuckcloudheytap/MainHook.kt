package com.jizhi.fuckcloudheytap

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (lpparam!!.packageName == "com.heytap.cloud") {
            XposedBridge.log("Cloud Hook成功")
            val findClass = XposedHelpers.findClass("com.heytap.openid.sdk.a", lpparam.classLoader)
            XposedHelpers.findAndHookMethod(
                findClass,
                "a",
                String::class.java,
                String::class.java,
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam?) {
                        XposedHelpers.setStaticBooleanField(findClass,"a",true)
                        XposedBridge.log("Cloud Hook修改 Log 输出")
                    }
                })
        }

    }
}