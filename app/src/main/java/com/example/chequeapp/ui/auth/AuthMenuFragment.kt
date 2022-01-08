package com.example.chequeapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chequeapp.R
import com.example.chequeapp.models.auth.AuthPage
import com.example.chequeapp.presentation.auth.AbstractAuthViewModel
import kotlinx.android.synthetic.main.fragment_auth_menu.*

class AuthMenuFragment(
    private val viewModel: AbstractAuthViewModel,
) : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_auth_menu, container, false)

    private fun setListeners() {
        auth_menu_b_login.setOnClickListener {
            viewModel.switchPage(AuthPage.Login)
        }

        auth_menu_b_register.setOnClickListener {
            viewModel.switchPage(AuthPage.Registration)
        }
    }
}