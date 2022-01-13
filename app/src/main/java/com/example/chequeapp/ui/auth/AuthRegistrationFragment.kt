package com.example.chequeapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chequeapp.R
import com.example.chequeapp.models.navigation.AppSection
import com.example.chequeapp.models.auth.AuthPage
import com.example.chequeapp.presentation.auth.AbstractAuthViewModel
import com.example.chequeapp.ui.root.AbstractRootActivity
import com.example.domain.models.auth.RegistrationResult
import kotlinx.android.synthetic.main.fragment_auth_registration.*

class AuthRegistrationFragment(
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
    ): View? = inflater.inflate(R.layout.fragment_auth_registration, container, false)

    private fun subscribeOnViewModel() {
        viewModel.registrationResultLive.observe(activity) { result ->
            auth_register_tv_error.visibility = View.GONE
            when (result) {
                RegistrationResult.Ok -> activity.switchSection(AppSection.NewEvent)
                RegistrationResult.UsernameIsOccupied ->
                    auth_register_tv_error.visibility = View.VISIBLE
                RegistrationResult.UnknownError ->
                    Toast.makeText(activity, "Unable to connect", Toast.LENGTH_LONG).show()
                else -> Unit
            }
        }
    }

    private fun setListeners() {
        auth_register_b_submit.setOnClickListener {
            val username = auth_register_et_username.text.toString()
            val telegram = auth_register_et_telegram.text.toString()
            if (username.isNotEmpty() && telegram.isNotEmpty())
                viewModel.register(username, telegram)
        }
        auth_register_tv_login.setOnClickListener {
            viewModel.switchPage(AuthPage.Login)
        }
    }
}