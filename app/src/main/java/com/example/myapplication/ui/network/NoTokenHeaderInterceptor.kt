package com.example.myapplication.ui.network

import android.content.Context
import android.os.Build
import android.provider.Settings
import com.example.myapplication.App
import com.example.myapplication.ui.local.SPUtils
import com.example.myapplication.ui.network.model.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.Locale

/**
 * val headers = Headers.headersOf(
    "Content-Type", "application/json",
    "Authorization", "Bearer YOUR_ACCESS_TOKEN",
    "User-Agent", "Your User Agent"
    )
 *
 *  相当于log、header、response统一处理
 */
class NoTokenHeaderInterceptor : Interceptor {

    private val tag = this::class.java.simpleName
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(assembleRequest(request))
    }

    /**
     * 登陆成功后存入
     * SpUtils.putString(this,"X-IDToken","token");
     *SPUtils.putInt(App.instance, "X-Role", tempRole);
     *     RECRUITER(1),
     *     CANDIDATE(2);
     */
    private fun assembleRequest(request: Request): Request {

        var newBuilder = request.newBuilder()
        var role = SPUtils.getInt(App.instance, "X-Role", 0)
        if (role != 0) {
            newBuilder.addHeader(Headers.ROLE, role.toString())
        }
        val deviceId = SPUtils.getString(App.instance, Headers.DEVICE_ID, "")
        if(deviceId!!.isNotEmpty()){
            newBuilder.addHeader(Headers.DEVICE_ID, deviceId)
        }else{
            var newDeviceId = getDeviceId(App.instance)
            SPUtils.putString(App.instance,Headers.DEVICE_ID,newDeviceId)
            newBuilder.addHeader(Headers.DEVICE_ID, newDeviceId)
        }
        val os = SPUtils.getString(App.instance, Headers.OS, "")
        if (os!!.isNotEmpty()) {
            newBuilder.addHeader(Headers.OS, os)
        }else{
            SPUtils.putString(App.instance,Headers.OS,"android")
            newBuilder.addHeader(Headers.OS, "android")
        }
        val appVersion = SPUtils.getString(App.instance, Headers.APP_VERSION, "")
        if (appVersion!!.isNotEmpty()) {
            newBuilder.addHeader(Headers.APP_VERSION, appVersion)
        }else{
            SPUtils.putString(App.instance,Headers.APP_VERSION,"2.5.9")
            newBuilder.addHeader(Headers.APP_VERSION, "2.5.9")
        }
        val bundleId = SPUtils.getString(App.instance, Headers.BUNDLE_ID, "")
        if (bundleId!!.isNotEmpty()) {
            newBuilder.addHeader(Headers.BUNDLE_ID, bundleId)
        }else{
            SPUtils.putString(App.instance,Headers.BUNDLE_ID,"are.you.happy")
            newBuilder.addHeader(Headers.BUNDLE_ID, "are.you.happy")
        }
        val osVersion = SPUtils.getString(App.instance, Headers.OS_VERSION, "")
        if (osVersion!!.isNotEmpty()) {
            newBuilder.addHeader(Headers.OS_VERSION, osVersion)
        }else{
            val newOsVersion = Build.VERSION.RELEASE
            SPUtils.putString(App.instance,Headers.OS_VERSION,newOsVersion)
            newBuilder.addHeader(Headers.OS_VERSION, newOsVersion)
        }
        val api = SPUtils.getInt(App.instance, Headers.API, 0)
        if (api != 0) {
            newBuilder.addHeader(Headers.API, api.toString())
        }else{
            val newApi = Build.VERSION.SDK_INT
            SPUtils.putInt(App.instance,Headers.API,newApi)
            newBuilder.addHeader(Headers.API, newApi.toString())
        }
        val model = SPUtils.getString(App.instance, Headers.MODEL, "")
        if (model!!.isNotEmpty()) {
            newBuilder.addHeader(Headers.MODEL, model)
        }else{
            val newModel = Build.MODEL
            SPUtils.putString(App.instance,Headers.MODEL,newModel)
            newBuilder.addHeader(Headers.MODEL, newModel)
        }
        val brand = SPUtils.getString(App.instance, Headers.MANUFACTURER, "")
        if (brand!!.isNotEmpty()) {
            newBuilder.addHeader(Headers.MANUFACTURER, brand)
        }else{
            val newBrand = Build.BRAND
            SPUtils.putString(App.instance,Headers.MANUFACTURER,newBrand)
            newBuilder.addHeader(Headers.MANUFACTURER, newBrand)
        }
        val dpi = SPUtils.getInt(App.instance, Headers.DPI, 0)
        if (dpi != 0) {
            newBuilder.addHeader(Headers.DPI, api.toString())
        }else{
            val newDpi = App.instance.resources.displayMetrics.densityDpi
            SPUtils.putInt(App.instance,Headers.DPI,newDpi)
            newBuilder.addHeader(Headers.DPI, newDpi.toString())
        }
        val language = SPUtils.getString(App.instance, Headers.LANGUAGE, "")
        if (language!!.isNotEmpty()) {
            newBuilder.addHeader(Headers.LANGUAGE, language)
        }else{
            val newLanguage = Locale.getDefault().language
            SPUtils.putString(App.instance,Headers.LANGUAGE,newLanguage)
            newBuilder.addHeader(Headers.LANGUAGE, newLanguage)
        }
        return newBuilder.build();
    }

    private fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

}
