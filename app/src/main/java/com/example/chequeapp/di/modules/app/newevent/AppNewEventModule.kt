package com.example.chequeapp.di.modules.app.newevent

import android.content.Context
import com.example.chequeapp.presentation.newevent.AbstractNewEventViewModel
import com.example.chequeapp.presentation.newevent.NewEventViewModel
import com.example.chequeapp.presentation.newevent.names.AbstractNevEventNamesPageViewModel
import com.example.chequeapp.presentation.newevent.names.NewEventNamesPageViewModel
import com.example.chequeapp.presentation.newevent.participants.AbstractNewEventParticipantsPageViewModel
import com.example.chequeapp.presentation.newevent.participants.NewEventParticipantsPageViewModel
import com.example.chequeapp.validation.newevent.INewEventNamesPageValidator
import com.example.chequeapp.validation.newevent.NewEventNamesPageValidator
import com.example.domain.usecases.users.GetAllUsersUseCase
import dagger.Module
import dagger.Provides

@Module
class AppNewEventModule {
    private var viewModel: AbstractNewEventViewModel? = null
    private var namesPageViewModel: AbstractNevEventNamesPageViewModel? = null
    private var participantsPageViewModel: AbstractNewEventParticipantsPageViewModel? = null
    private var namesPageValidator: INewEventNamesPageValidator? = null

    @Provides
    fun provideRootViewModel(): AbstractNewEventViewModel =
        viewModel ?: run {
            viewModel = NewEventViewModel()
            viewModel!!
        }

    @Provides
    fun provideNamesPageViewModel(
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
    fun provideParticipantsPageViewModel(
        context: Context,
        viewModel: AbstractNewEventViewModel,
        useCase: GetAllUsersUseCase,
    ): AbstractNewEventParticipantsPageViewModel =
        participantsPageViewModel ?: run {
            participantsPageViewModel = NewEventParticipantsPageViewModel(
                context = context,
                rootViewModel = viewModel,
                allUsersUseCase = useCase,
            )
            participantsPageViewModel!!
        }

    @Provides
    fun provideNamesPageValidator(): INewEventNamesPageValidator =
        namesPageValidator ?: run {
            namesPageValidator = NewEventNamesPageValidator()
            namesPageValidator!!
        }
}