package com.cocaine.myply.feature.ui.dialog

import android.view.View
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseDialogFragment
import com.cocaine.myply.databinding.DialogTwoButtonBinding

class MyPlyTwoButtonDialog :
    BaseDialogFragment<DialogTwoButtonBinding>(R.layout.dialog_two_button) {
    companion object {
        lateinit var title: String
        lateinit var subTitle: String
        lateinit var positiveName: String
        lateinit var negativeName: String
        lateinit var positiveAction: View.OnClickListener
        lateinit var negativeAction: View.OnClickListener

        fun setDialogContent(
            title: String="",
            subTitle: String="",
            positiveName: String="",
            negativeName: String="",
            positiveAction: View.OnClickListener,
            negativeAction: View.OnClickListener
        ) {
            this.title = title
            this.subTitle = subTitle
            this.positiveName = positiveName
            this.negativeName = negativeName
            this.positiveAction = positiveAction
            this.negativeAction = negativeAction
        }
    }

    override fun setup() {
        binding?.apply {
            dialogTitle.text = title
            dialogSubtitle.text = subTitle
            dialogPositive.text = positiveName
            dialogNegative.text = negativeName
            dialogPositive.setOnClickListener(positiveAction)
            dialogNegative.setOnClickListener(negativeAction)
        }
    }
}