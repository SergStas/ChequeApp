package com.example.chequeapp.presentation.newevent.participants

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.R
import com.example.chequeapp.models.newevent.NewEventPage
import com.example.chequeapp.presentation.newevent.AbstractNewEventViewModel
import com.example.chequeapp.ui.newevent.users.adapters.NewEventParticipantsAdapter
import com.example.domain.models.UserData
import com.example.domain.usecases.users.GetAllUsersUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewEventParticipantsPageViewModel(
    private val allUsersUseCase: GetAllUsersUseCase,
    private val context: Context,
    private val rootViewModel: AbstractNewEventViewModel,
): AbstractNewEventParticipantsPageViewModel() {
    override val loadingLive = MutableLiveData(false)
    override val availableUsersLive = MutableLiveData<List<UserData>>(emptyList())
    override val addedUsersLive = MutableLiveData<List<UserData>>(mutableListOf())
    override val errorMessageLive = MutableLiveData("")

    private var disposable: Disposable? = null

    override fun loadUsers() {
        loadingLive.value = true
        disposable = Observable.create<List<UserData>> { emitter ->
            emitter.onNext(allUsersUseCase.execute())
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                if (list.isEmpty()) {
                    errorMessageLive.value = context.getString(R.string.new_uses_error_failed_to_load)
                    return@subscribe
                }
                availableUsersLive.value = list.distinct().filter { e ->
                    addedUsersLive.value?.contains(e)?.not() ?: true
                }
                addedUsersLive.value = addedUsersLive.value
                    ?.filter { e -> list.contains(e) }?.toMutableList() ?: mutableListOf()
            }, {
                errorMessageLive.value = context.getString(R.string.new_uses_error_failed_to_load)
            }, {
                disposable?.dispose()
                loadingLive.value = false
            })
    }

    override fun addUser(userData: UserData) {
        addedUsersLive.value = addedUsersLive.value?.toMutableList()?.apply {
            add(userData)
            distinct()
        }
        availableUsersLive.value = availableUsersLive.value?.toMutableList()?.apply {
            remove(userData)
            distinct()
        }
    }

    override fun removeUser(userData: UserData) {
        addedUsersLive.value = addedUsersLive.value?.toMutableList()?.apply {
            remove(userData)
            distinct()
        }
        availableUsersLive.value = availableUsersLive.value?.toMutableList()?.apply {
            add(userData)
            distinct()
        }
    }

    override fun nextPage() {
        errorMessageLive.value = 
            if (addedUsersLive.value.isNullOrEmpty() || addedUsersLive.value!!.size < 2) {
                context.getString(R.string.new_users_error_at_least_2)
            } else {
                rootViewModel.switchPage(NewEventPage.Receipts)
                ""
            }
    }

    override fun back() {
        rootViewModel.switchPage(NewEventPage.Names)
    }
}