package edu.kpi.spmp.lab3

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset


class HistoryActivity : AppCompatActivity(), HistoryFragment.OnFragmentInteractionListener {
    private val manager = supportFragmentManager
    private val FILE_NAME = "history.txt"

    override fun onClearHistoryButtonClick() {
        val historyFragment = manager.findFragmentById(R.id.fragment_history) as HistoryFragment
        historyFragment.clean()
        cleanFile()
    }

    override fun onBackButtonClick() {
        finish()
    }

    private fun cleanFile() {
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            fos!!.write("".toByteArray(Charset.defaultCharset()))
            showMessage("History deleted")
        } catch (ex: IOException) {
            showMessage("Error during opening file")
            ex.printStackTrace()

        } finally {
            try {
                fos!!.close()
            } catch (ex: IOException) {
                showMessage("Error during closing file stream")
                ex.printStackTrace()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val transaction = manager.beginTransaction()
        val historyFragment = HistoryFragment.newInstance(loadFromFile())
        transaction.replace(R.id.fragment_history, historyFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun loadFromFile(): String {
        var fin: FileInputStream? = null
        var text = ""
        try {
            fin = openFileInput(FILE_NAME)
            val bytes = ByteArray(fin!!.available())
            fin.read(bytes)
            text = String(bytes)
        } catch (ex: IOException) {
            showMessage("Error during opening file")
            ex.printStackTrace()
        } finally {
            try {
                fin!!.close()
            } catch (ex: IOException) {
                showMessage("Error. Cannot close file stream")
                ex.printStackTrace()
            }
        }
        return text
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
