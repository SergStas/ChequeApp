package com.example.chequeapp.ui.root

import androidx.appcompat.app.AppCompatActivity
import com.example.chequeapp.models.AppSection

abstract class AbstractRootActivity: AppCompatActivity() {
    abstract fun switchSection(newSection: AppSection)
}