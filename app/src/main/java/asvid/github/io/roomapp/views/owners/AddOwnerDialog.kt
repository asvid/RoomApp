package asvid.github.io.roomapp.views.owners

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import asvid.github.io.roomapp.R
import asvid.github.io.roomapp.data.owner.OwnerRepository
import asvid.github.io.roomapp.model.OwnerModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class AddOwnerDialog : DialogFragment() {

    lateinit var ownerRepository: OwnerRepository
        @Inject set

    lateinit var ownerLoginWrapper: TextInputLayout
    lateinit var avatarUrlWrapper: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_add_owner, null)
        ownerLoginWrapper = view.findViewById(R.id.ownerLoginWrapper)
        avatarUrlWrapper = view.findViewById(R.id.avatarUrlWrapper)
        builder.setView(view).setTitle("add owner")
                .setPositiveButton(R.string.save, { dialog, id ->
                    saveOwner()
                })
                .setNegativeButton(R.string.cancel, { dialog, id ->
                    // User cancelled the dialog
                })
        return builder.create()
    }

    private fun saveOwner() {
        val ownerLogin = ownerLoginWrapper.editText?.text.toString()
        val avatarUrl = avatarUrlWrapper.editText?.text.toString()
        ownerRepository.save(OwnerModel(ownerLogin, null, avatarUrl)).subscribe()
    }
}