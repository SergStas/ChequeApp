package com.example.chequeapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.chequeapp.R
import com.example.chequeapp.models.AppSection
import com.example.chequeapp.models.auth.AuthPage
import com.example.chequeapp.presentation.auth.AbstractAuthViewModel
import com.example.chequeapp.ui.root.AbstractRootActivity
import com.example.domain.models.auth.LoginResult
import kotlinx.android.synthetic.main.fragment_auth_login.*

class AuthLoginFragment(
    private val viewModel: AbstractAuthViewModel,
    private val activity: AbstractRootActivity,
) : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        subscribeOnViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_auth_login, container, false)

    private fun subscribeOnViewModel() {
        viewModel.loginResultLive.observe(activity) { result ->
            auth_login_tv_error.visibility = View.GONE
            when (result) {
                is LoginResult.Success -> activity.switchSection(AppSection.NewEvent)
                is LoginResult.UserNotFound -> auth_login_tv_error.isVisible = true
                is LoginResult.Error -> toast("An error has occurred: ${result.throwable::class.java}")
                else -> Unit
            }
        }
    }

    private fun setListeners() {
        auth_login_b_submit.setOnClickListener {
            val username = auth_login_et_username.text.toString()
            if (username.isNotEmpty())
                viewModel.login(username)
        }
        auth_login_tv_register.setOnClickListener {
            viewModel.switchPage(AuthPage.Registration)
        }
    }

    private fun toast(message: String) =
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}