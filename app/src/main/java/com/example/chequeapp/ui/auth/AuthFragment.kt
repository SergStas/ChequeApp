package com.example.chequeapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.models.auth.AuthPage
import com.example.chequeapp.presentation.auth.AbstractAuthViewModel
import com.example.chequeapp.ui.root.AbstractRootActivity
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject

class AuthFragment(
    private val activity: AbstractRootActivity,
) : Fragment() {
    private lateinit var menuFragment: AuthMenuFragment
    private lateinit var loginFragment: AuthLoginFragment
    private lateinit var registerFragment: AuthRegistrationFragment

    private var activeFragment = Fragment()

    @Inject
    lateinit var viewModel: AbstractAuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectDependencies()
        subscribeOnViewModel()
    }

    private fun injectDependencies() {
        (activity.application as App).appComponent.inject(this)
        menuFragment = AuthMenuFragment(viewModel)
        loginFragment = AuthLoginFragment(viewModel, activity)
        registerFragment = AuthRegistrationFragment(viewModel, activity)
    }

    private fun subscribeOnViewModel() {
        viewModel.loadingStateLive.observe(activity) { b ->
            auth_pb.isVisible = b
        }

        viewModel.pageLive.observe(activity) { page ->
            activeFragment = when (page) {
                AuthPage.Menu -> menuFragment
                AuthPage.Login -> loginFragment
                AuthPage.Registration -> registerFragment
                else -> Fragment()
            }
            switchPage()
        }
    }

    private fun switchPage() {
        childFragmentManager.beginTransaction()
            .replace(R.id.auth_container_content, activeFragment)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_auth, container, false)
}