package edu.kpi.spmp.lab2

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ButtonsFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.clear_button -> listener?.onCleanButtonClick()
            R.id.plus_button -> listener?.onPlusButtonClick()
            R.id.minus_button -> listener?.onMinusButtonClick()
            R.id.divide_button -> listener?.onDivideButtonClick()
            R.id.mult_button -> listener?.onMultipleButtonClick()
            R.id.result_button -> listener?.onResultButtonClick()
        }
    }

    private var listener: OnCleanButtonClickListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_buttons, container, false)
        view.findViewById<Button>(R.id.clear_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.plus_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.minus_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.mult_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.divide_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.result_button).setOnClickListener(this)
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCleanButtonClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnCleanButtonClickListener {
        fun onCleanButtonClick()
        fun onPlusButtonClick()
        fun onMinusButtonClick()
        fun onMultipleButtonClick()
        fun onDivideButtonClick()
        fun onResultButtonClick()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                ButtonsFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
