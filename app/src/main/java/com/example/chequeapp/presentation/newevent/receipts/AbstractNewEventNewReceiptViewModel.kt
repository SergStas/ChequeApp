package com.example.chequeapp.presentation.newevent.receipts

import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.models.newevent.PositionDataElement
import com.example.domain.models.UserData

abstract class AbstractNewEventNewReceiptViewModel {
    abstract val participants: List<UserData>

    abstract val titleLive: MutableLiveData<String>
    abstract val payerLive: MutableLiveData<UserData>
    abstract val pageErrorLive: MutableLiveData<String?>
    abstract val positionsLive: MutableLiveData<List<PositionDataElement>>
    abstract val selectedUsersMapLive: MutableLiveData<Map<Int, String>>
    abstract val lastModifiedPosition: MutableLiveData<PositionDataElement?>

    abstract fun changeTitle(title: String)
    abstract fun changePayer(userName: String)
    abstract fun createPosition(title: String)
    abstract fun changePositionTitle(positionId: Int, title: String)
    abstract fun changePositionPrice(positionId: Int, price: Float)
    abstract fun deletePosition(positionId: Int)
    abstract fun changeSelectedUser(positionId: Int, userName: String)
    abstract fun addParticipantToPosition(positionId: Int, userName: String)
    abstract fun changePart(positionId: Int, userName: String, value: Float)
    abstract fun deleteParticipant(positionId: Int, userName: String)
    abstract fun finish()
}