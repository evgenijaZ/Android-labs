package edu.kpi.spmp.lab3

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment(), View.OnClickListener {
    private var listener: OnFragmentInteractionListener? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.clear_history_button -> listener?.onClearHistoryButtonClick()
            R.id.back_button -> listener?.onBackButtonClick()
        }
    }

    private var content: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            content = it.getString("context")
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_history, container, false)
        view.findViewById<Button>(R.id.clear_history_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.back_button).setOnClickListener(this)
        view.findViewById<TextView>(R.id.history_content_text_view).text =
               arguments!!.getString("content")
        return view
    }

    fun setHistoryContent(content: String) {
        history_content_text_view.text = content
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun clean() {
        history_content_text_view.text =""
    }


    interface OnFragmentInteractionListener {
        fun onClearHistoryButtonClick()
        fun onBackButtonClick()

    }

    companion object {
        @JvmStatic
        fun newInstance(content: String) =
                HistoryFragment().apply {
                    arguments = Bundle().apply {
                        putString("content", content)
                    }
                }
    }
}
