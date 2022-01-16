package com.example.chequeapp.ui.newevent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.models.newevent.NewEventPage
import com.example.chequeapp.presentation.newevent.AbstractNewEventViewModel
import com.example.chequeapp.ui.newevent.names.NewEventNamesFragment
import com.example.chequeapp.ui.newevent.receipts.NewReceiptFragment
import com.example.chequeapp.ui.newevent.receipts.ReceiptsListFragment
import com.example.chequeapp.ui.newevent.users.NewEventParticipantsFragment
import com.example.chequeapp.ui.root.AbstractRootActivity
import javax.inject.Inject

class NewEventFragment(
    private val parentActivity: AbstractRootActivity,
) : Fragment() {
    @Inject
    lateinit var viewModel: AbstractNewEventViewModel

    private val namesFragment = NewEventNamesFragment()
    private val usersFragment = NewEventParticipantsFragment(parentActivity)
    private val receiptsFragment = ReceiptsListFragment()
    private val newReceiptFragment = NewReceiptFragment()
    private val resultsFragment = Fragment()

    private var activeFragment = Fragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectDependencies()
        subscribeOnViewModel()
    }

    private fun injectDependencies() =
        (parentActivity.application as App).appComponent.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_new_event, container, false)

    private fun subscribeOnViewModel() {
        viewModel.pageLive.observe(parentActivity) { stage ->
            activeFragment = when (stage) {
                NewEventPage.Names -> namesFragment
                NewEventPage.Participants -> usersFragment
                NewEventPage.Receipts -> receiptsFragment
                NewEventPage.NewReceipt -> newReceiptFragment
                NewEventPage.Results -> resultsFragment
                else -> Fragment()
            }
            switchFragment()
        }
    }

    private fun switchFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.new_container_content, activeFragment)
            .commit()
    }
}