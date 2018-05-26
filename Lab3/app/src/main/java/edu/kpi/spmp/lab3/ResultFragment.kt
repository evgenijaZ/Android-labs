package edu.kpi.spmp.lab3

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_result.*


private const val ARG_RESULT = "result"


class ResultFragment : Fragment() {
    private var result: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            result = it.getString(ARG_RESULT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    fun clean() {
        result_text_view.text = ""
    }

    fun setResultValue(value:String, size:Float){
        result = value
        result_text_view.text = value
        result_text_view.setTextSize(TypedValue.COMPLEX_UNIT_SP,size)
    }

    companion object {
        @JvmStatic
        fun newInstance(result: String) =
                ResultFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_RESULT, result)
                    }
                }
    }
}
