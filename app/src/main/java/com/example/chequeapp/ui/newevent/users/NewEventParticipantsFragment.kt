package com.example.chequeapp.ui.newevent.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.presentation.newevent.participants.AbstractNewEventParticipantsPageViewModel
import com.example.chequeapp.ui.newevent.users.adapters.NewEventParticipantsAdapter
import com.example.chequeapp.ui.root.AbstractRootActivity
import com.example.domain.models.UserData
import kotlinx.android.synthetic.main.fragment_new_event_users.*
import javax.inject.Inject

class NewEventParticipantsFragment(
    private val parentActivity: AbstractRootActivity,
): Fragment(R.layout.fragment_new_event_users) {
    @Inject
    lateinit var viewModel: AbstractNewEventParticipantsPageViewModel

    private lateinit var adapter: NewEventParticipantsAdapter
    private var selectedUser: UserData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectDependencies()
        setListAdapter()
        subscribeOnViewModel()
        setSpinner()
        setListeners()

        viewModel.loadUsers()
    }

    private fun setListeners() {
        new_users_b_add.setOnClickListener {
            if (selectedUser != null) {
                viewModel.addUser(selectedUser!!)
            }
        }
        new_b_back_to_names.setOnClickListener {
            viewModel.back()
        }
        new_b_to_receipts.setOnClickListener {
            viewModel.nextPage()
        }
    }

    private fun setListAdapter() {
        new_rv_users?.let {
            it.layoutManager = LinearLayoutManager(
                parentActivity,
                LinearLayoutManager.VERTICAL,
                false,
            )
            adapter = NewEventParticipantsAdapter(viewModel::removeUser)
            it.adapter = adapter
        }
    }

    private fun setSpinner() {
        new_users_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val name = parent?.getItemAtPosition(position) as String
                if (name != getString(R.string.new_users_spinner_empty_ph)) {
                    selectedUser = UserData(name)
                }
            }
        }
        submitSpinner(emptyList())
    }

    private fun submitSpinner(users: List<UserData>) {
        new_users_spinner.adapter =
            ArrayAdapter(
                parentActivity,
                R.layout.support_simple_spinner_dropdown_item,
                if (users.isEmpty()) listOf(getString(R.string.new_users_spinner_empty_ph)) else users.map(UserData::name),
            ).apply { setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item) }
    }

    private fun subscribeOnViewModel() {
        viewModel.loadingLive.observe(parentActivity) { b ->
            new_users_spinner_pb.isVisible = b
        }
        viewModel.errorMessageLive.observe(parentActivity) { msg ->
            new_users_tv_error.isVisible = !msg.isNullOrEmpty()
            new_users_tv_error.text = msg
        }
        viewModel.availableUsersLive.observe(parentActivity) { users ->
            submitSpinner(users ?: emptyList())
        }
        viewModel.addedUsersLive.observe(parentActivity) { list ->
            adapter.submitList(list)
            new_tv_empty_users_list.isVisible = list.isEmpty()
        }
    }

    private fun injectDependencies() {
        (parentActivity.application as App).appComponent.inject(this)
    }
}