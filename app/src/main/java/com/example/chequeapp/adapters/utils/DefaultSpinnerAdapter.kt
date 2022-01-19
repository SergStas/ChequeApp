package com.example.chequeapp.adapters.utils

import android.widget.ArrayAdapter
import com.example.chequeapp.App
import com.example.chequeapp.R

class DefaultSpinnerAdapter(labels: List<String>): ArrayAdapter<String>(
    App.instance,
    R.layout.support_simple_spinner_dropdown_item,
    labels,
) {
    init {
        setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
    }
}