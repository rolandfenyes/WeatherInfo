package aut.bme.hu.weatherinfo.feature.city

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment
import aut.bme.hu.weatherinfo.R
import kotlinx.android.synthetic.main.dialog_new_city.view.*

class AddCityDialogFragment: AppCompatDialogFragment() {

    private lateinit var listener: AddCityDialogListener
    private lateinit var contentView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = if (targetFragment != null){
                targetFragment as AddCityDialogListener
            } else {
                activity as AddCityDialogListener
            }
        } catch ( e: ClassCastException){
            throw RuntimeException(e)
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        contentView = getContentView()
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_city)
            .setView(contentView)
            .setPositiveButton(R.string.ok) { dialogInterface, i ->
                listener!!.onCityAdded(contentView.NewCityDialogEditText!!.text.toString())
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun getContentView(): View{
        return LayoutInflater.from(context).inflate(R.layout.dialog_new_city, null)
    }

    interface AddCityDialogListener {
        fun onCityAdded(city: String?)
    }
}