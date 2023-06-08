package com.example.myapplication.ui.network.model

object Headers {
    /** Constant `ID_TOKEN="X-IDToken"`  */
    const val ID_TOKEN = "X-IDToken"

    /** Constant `FCM_TOKEN="X-FcmToken"`  */
    const val FCM_TOKEN = "X-FcmToken"

    /** Constant `DEVICE_ID="X-DeviceId"`  */
    const val DEVICE_ID = "X-DeviceId"

    /** Constant `MODEL="X-Model"`  */
    const val MODEL = "X-Model"

    /** Constant `MANUFACTURER="X-Manufacturer"`  */
    const val MANUFACTURER = "X-Brand"

    /** Constant `WIDTH="X-WidthPixels"`  */
    const val WIDTH = "X-WidthPixels"

    /** Constant `HEIGHT="X-HeightPixels"`  */
    const val HEIGHT = "X-HeightPixels"

    /** Constant `DPI="X-Dpi"`  */
    const val DPI = "X-Dpi"

    /** Constant `APP_VERSION="X-AppVersion"`  */
    const val APP_VERSION = "X-AppVersion"

    /** Constant `APP_VERSION_CODE="X-AppVersionCode"`  */
    const val APP_VERSION_CODE = "X-AppVersionCode"

    /** Constant `OS="X-OS"`  */
    const val OS = "X-OS"

    /** Constant `OS_VERSION="X-OSVersion"`  */
    const val OS_VERSION = "X-OSVersion"

    /** Constant `API="X-Api"`  */
    const val API = "X-Api"

    /** Constant `IDFA="X-Idfa"`  */
    const val IDFA = "X-Idfa"

    /** Constant `MAC="X-Mac"`  */
    const val MAC = "X-Mac"

    /** Constant `BUNDLE_ID="X-BundleId"`  */
    const val BUNDLE_ID = "X-BundleId"

    /** Constant `LANGUAGE="X-Language"`  */
    const val LANGUAGE = "X-Language"

    /** Constant `CHANNEL_ID=" X-ChannelId"`  */
    const val CHANNEL_ID = " X-ChannelId"

    /** Constant `TIME_STAMP="X-Timestamp"`  */
    const val TIME_STAMP = "X-Timestamp"
    const val TIMEZONE = "X-Timezone"
    const val FORWARDED_PREFIX = "X-Forwarded-Prefix"
    const val FORWARDED_FOR = "x-forwarded-for"
    const val FORWARDED_PROTO = "x-forwarded-proto"
    const val FORWARDED_HOST = "x-forwarded-host"

    /**
     * 地区/国家
     */
    const val REGION = "X-Region"
    const val ROLE = "X-Role"

    /**
     * android的id标识
     */
    const val XGoogleAdId = "X-Google_AdId"

    /**
     * ios的id标识
     */
    const val XAppleIDFA = "X-Apple_IDFA"
    const val XFeatureTag = "X-Feature-Tag"
    const val UID = "x-uid"
    const val CLIENTIP = "clientip"
}