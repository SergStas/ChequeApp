package com.example.chequeapp.ui.root

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.presentation.root.AbstractRootPageViewModel
import com.example.chequeapp.models.AppSection
import com.example.chequeapp.ui.auth.AuthFragment
import com.example.chequeapp.ui.navigation.NavigationFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AbstractRootActivity() {
    private val navBar = NavigationFragment(this)
    private val authFragment = AuthFragment(this)

    private lateinit var activeFragment: Fragment

    @Inject
    lateinit var viewModel: AbstractRootPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()
        viewModel.checkUser()
        setNavigationBar()
        subscribeOnViewModel()
    }

    private fun injectDependencies() =
        (application as App).appComponent.inject(this)

    private fun setNavigationBar() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container_nav, navBar)
            .commit()
    }

    private fun subscribeOnViewModel() {
        viewModel.userLive.observe(this) { user ->
            if (user == null) {
                viewModel.switchPage(AppSection.Auth)
            }
        }

        viewModel.activePageLive.observe(this) { section ->
            activeFragment = when (section) {
                AppSection.NewEvent -> Fragment()
                AppSection.Auth -> authFragment
                AppSection.History -> Fragment()
                else -> Fragment()
            }
            setActivePage()
        }
    }

    override fun switchSection(newSection: AppSection) =
        viewModel.switchPage(newSection)

    private fun setActivePage() {
        main_container_nav.isVisible = viewModel.activePageLive.value != AppSection.Auth
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container_page, activeFragment)
            .commit()
    }
}