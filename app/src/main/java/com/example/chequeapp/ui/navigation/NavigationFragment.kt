package com.example.chequeapp.ui.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chequeapp.R
import com.example.chequeapp.models.AppSection
import com.example.chequeapp.ui.root.AbstractRootActivity
import kotlinx.android.synthetic.main.fragment_navigation.*

class NavigationFragment(
    private val activity: AbstractRootActivity,
) : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        nav_iv_new.setOnClickListener {
            activity.switchSection(AppSection.NewEvent)
        }
        nav_iv_history.setOnClickListener {
            activity.switchSection(AppSection.History)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_navigation, container, false)
}