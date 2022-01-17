package com.example.chequeapp.ui.newevent.receipts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chequeapp.App
import com.example.chequeapp.R
import com.example.chequeapp.ui.root.AbstractRootActivity

class NewReceiptFragment(
    private val parentActivity: AbstractRootActivity,
) : Fragment(R.layout.fragment_new_receipt) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectDependencies()
    }

    private fun injectDependencies() {
        (parentActivity.application as App).appComponent.inject(this)
    }
}