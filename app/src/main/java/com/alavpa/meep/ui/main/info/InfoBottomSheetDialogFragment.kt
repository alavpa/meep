package com.alavpa.meep.ui.main.info

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.ui.model.RenderManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject

class InfoBottomSheetDialogFragment : BottomSheetDialogFragment() {

    interface OnDismissListener{
        fun onDismiss()
    }

    private val renderManager: RenderManager by inject()

    companion object {
        fun newInstance(): InfoBottomSheetDialogFragment {
            return InfoBottomSheetDialogFragment()
        }
    }

    var resource: MeepResource? = null
    var onDismissListener: OnDismissListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return renderManager.getView(resource, inflater, container) ?: super.onCreateView(
            inflater,
            container,
            savedInstanceState
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resource?.run {
            renderManager.populateInfo(view, this)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss()
    }
}