package com.example.myapplication.ui.local

import android.content.Context

import android.content.SharedPreferences

object SPUtils {
    private var sp: SharedPreferences? = null

    /**
     * 写入boolean变量至sp中
     *
     * @param context 上下文环境
     * @param key     存储节点名称
     * @param value   存储节点的值 boolean
     */
    fun putBoolean(context: Context, key: String?, value: Boolean) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putBoolean(key, value).commit()
    }

    /**
     * 读取boolean标示从sp中
     *
     * @param context 上下文环境
     * @param key     存储节点名称
     * @param value   没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    fun getBoolean(context: Context, key: String?, value: Boolean): Boolean {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getBoolean(key, value)
    }

    /**
     * 写入String变量至sp中
     *
     * @param context 上下文环境
     * @param key     存储节点名称
     * @param value   存储节点的值string
     */
    fun putString(context: Context, key: String?, value: String?) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putString(key, value).commit()
    }

    /**
     * 读取String标示从sp中
     *
     * @param context 上下文环境
     * @param key     存储节点名称
     * @param value   没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    fun getString(context: Context, key: String?, value: String?): String? {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getString(key, value)
    }

    /**
     * 写入int变量至sp中
     *
     * @param context 上下文环境
     * @param key     存储节点名称
     * @param value   存储节点的值int
     */
    fun putInt(context: Context, key: String?, value: Int) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putInt(key, value).commit()
    }

    /**
     * 读取int标示从sp中
     *
     * @param context 上下文环境
     * @param key     存储节点名称
     * @param value   没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    fun getInt(context: Context, key: String?, value: Int): Int {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getInt(key, value)
    }
}