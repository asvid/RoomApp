package asvid.github.io.roomapp.views.gists

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.widget.ArrayAdapter
import android.widget.Spinner
import asvid.github.io.roomapp.R
import asvid.github.io.roomapp.data.gist.GistRepository
import asvid.github.io.roomapp.data.owner.OwnerRepository
import asvid.github.io.roomapp.model.GistModel
import asvid.github.io.roomapp.model.OwnerModel
import dagger.android.AndroidInjection
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class AddGistDialog : DialogFragment() {

    lateinit var ownerRepository: OwnerRepository
        @Inject set

    lateinit var gistRepository: GistRepository
        @Inject set

    lateinit var gistDescWrapper: TextInputLayout
    lateinit var ownersSpinner: Spinner
    lateinit var ownersList: Collection<OwnerModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_gist, null)
        gistDescWrapper = view.findViewById(R.id.gistDescWrapper)
        ownersSpinner = view.findViewById(R.id.ownersSpinner)
        builder.setView(view).setTitle("add gist")
                .setPositiveButton(R.string.save, { dialog, id ->
                    saveGist()
                })
                .setNegativeButton(R.string.cancel, { dialog, id ->
                    // User cancelled the dialog
                })
        populateOwnersSpinner()

        return builder.create()
    }

    private fun populateOwnersSpinner() {
        ownerRepository.fetchAll().subscribe({
            ownersList = it
            val spinnerArray = ArrayList<String>()
            it.map { spinnerArray.add(it.login) }

            Timber.d("owners array: $spinnerArray")
            val adapter = ArrayAdapter<String>(
                    activity, android.R.layout.simple_spinner_item, spinnerArray)

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            ownersSpinner.adapter = adapter
        }, { Timber.e("error populating owners spinner: $it") })

    }

    private fun saveGist() {
        val gistDesc = gistDescWrapper.editText?.text.toString()
        val owner = ownersList.elementAt(ownersSpinner.selectedItemPosition)
        gistRepository.save(GistModel(null, gistDesc, owner, false, Date())).subscribe()
    }
}