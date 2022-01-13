package com.example.chequeapp.di.modules.app.newevent

import android.content.Context
import com.example.chequeapp.presentation.newevent.AbstractNewEventViewModel
import com.example.chequeapp.presentation.newevent.NewEventViewModel
import com.example.chequeapp.presentation.newevent.names.AbstractNevEventNamesPageViewModel
import com.example.chequeapp.presentation.newevent.names.NewEventNamesPageViewModel
import com.example.chequeapp.validation.newevent.INewEventNamesPageValidator
import com.example.chequeapp.validation.newevent.NewEventNamesPageValidator
import dagger.Module
import dagger.Provides

@Module
class AppNewEventModule {
    private var viewModel: AbstractNewEventViewModel? = null
    private var namesPageViewModel: AbstractNevEventNamesPageViewModel? = null
    private var namesPageValidator: INewEventNamesPageValidator? = null

    @Provides
    fun provideNewEventViewModel(): AbstractNewEventViewModel =
        viewModel ?: run {
            viewModel = NewEventViewModel()
            viewModel!!
        }

    @Provides
    fun provideNewEventNamesPageViewModel(
        context: Context,
        viewModel: AbstractNewEventViewModel,
        namesPageValidator: INewEventNamesPageValidator,
    ): AbstractNevEventNamesPageViewModel =
        namesPageViewModel ?: run {
            namesPageViewModel = NewEventNamesPageViewModel(
                context = context,
                rootViewModel = viewModel,
                validator = namesPageValidator,
            )
            namesPageViewModel!!
        }

    @Provides
    fun provideNewEventNamesPageValidator(): INewEventNamesPageValidator =
        namesPageValidator ?: run {
            namesPageValidator = NewEventNamesPageValidator()
            namesPageValidator!!
        }
}