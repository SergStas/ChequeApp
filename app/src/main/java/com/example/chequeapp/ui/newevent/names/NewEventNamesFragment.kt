package com.example.chequeapp.ui.newevent.names

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.presentation.newevent.names.AbstractNevEventNamesPageViewModel
import com.example.chequeapp.ui.root.AbstractRootActivity
import com.squareup.timessquare.CalendarPickerView
import kotlinx.android.synthetic.main.fragment_new_event_names.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewEventNamesFragment: Fragment() {
    @Inject
    lateinit var viewModel: AbstractNevEventNamesPageViewModel
    private lateinit var parentActivity: AbstractRootActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentActivity = activity as AbstractRootActivity
        injectDependencies()
        setCalendar()
        subscribeOnViewModel()
        setListeners()
    }

    private fun setCalendar() =
        new_calendar.run {
            init(
                Calendar.getInstance().apply { add(Calendar.YEAR, -1) }.time,
                Calendar.getInstance().apply { add(Calendar.YEAR, 1) }.time,
            ).withSelectedDate(Date())

            setOnDateSelectedListener(object : CalendarPickerView.OnDateSelectedListener {
                override fun onDateSelected(date: Date?) {
                    if (date != null)
                        viewModel.changeDate(date)
                }

                override fun onDateUnselected(date: Date?) {}
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_new_event_names, container, false)

    private fun injectDependencies() {
        (parentActivity.application as App).appComponent.inject(this)
    }

    private fun subscribeOnViewModel() {
        viewModel.calendarVisibilityLive.observe(parentActivity) { value ->
            new_calendar.isVisible = value
            new_b_show_calendar.text =
                if (value) getString(R.string.new_b_show_calendar_hide)
                else getString(R.string.new_b_calendar_show)
        }

        viewModel.dateLive.observe(parentActivity) { date ->
            new_tv_date.text = SimpleDateFormat("dd MMM yyyy", Locale.US).format(date)
            new_calendar.selectDate(date)
        }

        viewModel.eventNameLive.observe(parentActivity) { name ->
            if (new_et_name.text.toString().isEmpty())
                new_et_name.setText(name)
        }

        viewModel.errorMessageLive.observe(parentActivity) { msg ->
            new_tv_names_error.isVisible = msg.isNotEmpty()
            new_tv_names_error.text = msg
        }
    }

    private fun setListeners() {
        new_b_show_calendar.setOnClickListener {
            viewModel.switchCalendarVisibility()
        }

        new_b_to_users.setOnClickListener {
            viewModel.setName(new_et_name.text.toString())
            viewModel.nextPage()
        }
    }
}