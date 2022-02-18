package com.example.chequeapp.presentation.newevent.receipts

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.models.errors.CompositeError
import com.example.chequeapp.models.errors.NewReceiptError
import com.example.chequeapp.models.newevent.PositionDataElement
import com.example.domain.models.SessionData
import com.example.domain.models.UserData
import kotlin.math.abs

class NewEventNewReceiptViewModel(
    private val context: Context,
): AbstractNewEventNewReceiptViewModel() {
    override var participants = emptyList<UserData>()

    override val titleLive = MutableLiveData("")
    override val payerLive = MutableLiveData<UserData?>(null)
    override val pageErrorLive = MutableLiveData<String?>(null)
    override val positionsLive = MutableLiveData<List<PositionDataElement>>(emptyList())
    override val lastModifiedPosition = MutableLiveData<PositionDataElement?>(null)

    private val mutablePositions = positionsLive.value?.toMutableList() ?: mutableListOf()

    private var idIndex = 0

    override fun updateParticipants(users: List<UserData>) {
        participants = users
    }

    override fun changeTitle(title: String) {
        titleLive.value = title
    }

    override fun changePayer(userName: String) {
        payerLive.value = participants.firstOrNull { p -> p.name == userName}
    }

    override fun createPosition(title: String) {
        if (title.isEmpty()) return
        if (isTitleOccupied(title)) {
            pageErrorLive.value = context.getString(R.string.receipt_error_unique_pos)
        } else {
            pageErrorLive.value = ""
            val pos = PositionDataElement(
                id = idIndex++,
                position = SessionData.PositionData(
                    name = title,
                    price = 0f,
                    parts = emptyList(),
                ).withDefaultParts(participants),
                error = CompositeError(),
                selectedUserData = null,
            )
            mutablePositions.add(pos)
            positionsLive.value = mutablePositions.toList()
        }
    }

    override fun changePositionTitle(positionId: Int, title: String) {
        var pos = getById(positionId) ?: return
        if (pos.position.name == title) return
        val msg =
            if (isTitleOccupied(title)) {
                context.getString(R.string.receipt_error_unique_pos)
            } else {
                pos = pos.copy(position = pos.position.copy(name = title))
                ""
            }
        pos = pos.copy(error = msg)
        updatePosition(positionId, pos)
    }

    override fun changePositionPrice(positionId: Int, price: Float) {
        var pos = getById(positionId) ?: return
        if (price <= 0) {
            pos = pos.copy(error = pos.error.apply { add(NewReceiptError(context.getString(R.string.receipt_error_negative_price))) })
        } else {

            pos = pos.copy(position = pos.position.copy(price = price))
            ""
        }
        updatePosition(positionId, pos)
    }

    override fun deletePosition(positionId: Int) {
        val pos = getById(positionId) ?: return
        mutablePositions.remove(pos)
        positionsLive.value = mutablePositions.toList()
    }

    override fun changeSelectedUser(positionId: Int, userName: String) {
        var pos = getById(positionId) ?: return
        pos = pos.copy(selectedUserData = participants.firstOrNull { u -> u.name == userName })
        updatePosition(positionId, pos)
    }

    override fun addParticipantToPosition(positionId: Int, userName: String) {
        val newUser = participants.firstOrNull { u -> u.name == userName } ?: return
        if (userName.isEmpty()) return
        var pos = getById(positionId) ?: return
        val users = pos.position.parts.map { p -> p.user }
        if (users.map(UserData::name).contains(userName)) return
        pos = pos.copy(position = pos.position.withDefaultParts(users.toMutableList().apply { add(newUser) }))
        updatePosition(positionId, pos)
    }

    override fun changePart(positionId: Int, userName: String, value: Float) {
        var pos = getById(positionId) ?: return
        val oldValue = pos.position.parts.firstOrNull { u -> u.user.name == userName}?.part ?: return
        val total =
            (pos.position.parts.sumOf { p -> p.part.toDouble() } - oldValue) / 100 + value - 1
        pos = pos.copy(error = when {
            value <= 0 -> context.getString(R.string.receipt_error_not_eq_to_one)
            abs(total) > 5e-3 ->
                context.getString(R.string.receipt_error_not_eq_to_one)
            else -> ""
        })
        pos = pos.copy(position = pos.position.withChangedPart(userName, value * 100))
        updatePosition(positionId, pos)
    }

    override fun deleteParticipant(positionId: Int, userName: String) {
        var pos = getById(positionId) ?: return
        val newUser = pos.position.parts.firstOrNull { u -> u.user.name == userName }?.user ?: return
        pos = pos.copy(position = pos.position.withDefaultParts(
            pos.position.parts.map(SessionData.PartData::user).toMutableList().apply { remove(newUser) }
        ))
        updatePosition(positionId, pos)
    }

    override fun finish() {
        App.toast("Not yet implemented")
    }

    private fun isTitleOccupied(title: String) =
        positionsLive.value?.firstOrNull { p -> p.position.name == title} != null

    private fun getById(id: Int) =
        positionsLive.value?.firstOrNull { p -> p.id == id}

    private fun updatePosition(id: Int, value: PositionDataElement, updateLiveData: Boolean = true) {
        val index = mutablePositions.indexOfFirst { p -> p.id == id }
        if (index == -1) return
        mutablePositions[index] = value
        if (updateLiveData) {
            positionsLive.value = mutablePositions.toList()
        }
    }
}