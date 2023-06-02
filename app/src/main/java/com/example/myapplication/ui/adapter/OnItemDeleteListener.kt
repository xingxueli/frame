package com.example.myapplication.ui.adapter

import android.view.View


interface OnItemDeleteListener {
    fun onItemDelete(view: View?, position: Int)
}