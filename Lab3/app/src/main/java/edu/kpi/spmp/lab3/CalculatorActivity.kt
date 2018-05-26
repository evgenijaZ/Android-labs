package edu.kpi.spmp.lab3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset


class CalculatorActivity :
        AppCompatActivity(),
        ButtonsFragment.OnCleanButtonClickListener {

    private val manager = supportFragmentManager
    private var currentOperation: Operation = Operation.ADDITION
    private var currentSign = R.string.addition_sign
    private val FILE_NAME = "history.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        showFragments()
        createFileByName(FILE_NAME)
    }


    override fun onPlusButtonClick() {
        currentSign = R.string.addition_sign
        currentOperation = Operation.ADDITION
        setOperation(currentSign)
    }

    override fun onMinusButtonClick() {
        currentSign = R.string.subtraction_sign
        currentOperation = Operation.SUBTRACTION
        setOperation(currentSign)
    }

    override fun onMultipleButtonClick() {
        currentSign = R.string.multiplication_sign
        currentOperation = Operation.MULTIPLICATION
        setOperation(currentSign)
    }

    override fun onDivideButtonClick() {
        currentSign = R.string.division_sign
        currentOperation = Operation.DIVISION
        setOperation(currentSign)
    }

    override fun onResultButtonClick() {
        val transaction = manager.beginTransaction()
        val inputFragment = manager.findFragmentById(R.id.fragment_input) as InputFragment
        val arguments = inputFragment.getParams()

        val aText = arguments?.getString("param1")
        val bText = arguments?.getString("param2")
        val warningToast: Toast
        if (aText == null || aText.isEmpty()) {
            showMessage(getString(R.string.input_warning) + " first operand!")
            return
        } else if (bText == null || bText.isEmpty()) {
            showMessage(getString(R.string.input_warning) + " second operand!")
            return
        }

        val a = aText.toFloat()
        val b = bText.toFloat()
        val result = performOperation(a, b)
        val size = if (result.length > 9)
            54 * 9f / result.length
        else
            54f

        val resultFragment = manager.findFragmentById(R.id.fragment_result) as ResultFragment
        appendText("$aText${getString(currentSign)}$bText=$result\n")
        resultFragment.setResultValue(result, size)
        transaction.commit()

    }

    override fun onOpenHistoryButtonClick() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    private fun performOperation(a: Float, b: Float): String {
        return when (currentOperation) {
            Operation.ADDITION -> (a + b).toString()
            Operation.SUBTRACTION -> (a - b).toString()
            Operation.MULTIPLICATION -> (a * b).toString()
            Operation.DIVISION -> String.format("%.5f", (a / b))

        }
    }

    override fun onCleanButtonClick() {
        val transaction = manager.beginTransaction()
        val resultFragment = manager.findFragmentById(R.id.fragment_result) as ResultFragment
        resultFragment.clean()
        val inputFragment = manager.findFragmentById(R.id.fragment_input) as InputFragment
        inputFragment.clean()
        transaction.commit()
    }

    private fun showFragments() {
        val transaction = manager.beginTransaction()
        val inputFragment = InputFragment()
        transaction.replace(R.id.fragment_input, inputFragment)
        val buttonsFragment = ButtonsFragment()
        transaction.replace(R.id.fragment_buttons, buttonsFragment)
        val resultFragment = ResultFragment()
        transaction.replace(R.id.fragment_result, resultFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun setOperation(signId: Int) {
        val operationSign = getString(signId)
        val transaction = manager.beginTransaction()
        val inputFragment = manager.findFragmentById(R.id.fragment_input) as InputFragment
        inputFragment.setOperationSign(operationSign)
        transaction.commit()
    }

    private fun createFileByName(fileName: String) {
        val fos = openFileOutput(fileName, MODE_APPEND)
        fos.close()
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun appendText(message: String) {

        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_APPEND)
            fos!!.write(message.toByteArray(Charset.defaultCharset()))
        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            try {
                if (fos != null)
                    fos.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }


}
