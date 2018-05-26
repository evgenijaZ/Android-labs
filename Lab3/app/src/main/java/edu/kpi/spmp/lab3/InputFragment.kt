package edu.kpi.spmp.lab3

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_input.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InputFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    fun setOperationSign(operationSign: String?) {
        operation.text = operationSign
    }

    fun getParams(): Bundle? {
        arguments = Bundle().apply {
            putString(ARG_PARAM1, number1.text.toString())
            putString(ARG_PARAM2, number2.text.toString())
        }
        return arguments
    }

    fun clean() {
        number1.text.clear()
        number2.text.clear()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                InputFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
