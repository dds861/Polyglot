package com.english.learnfast.AlertDialogsApp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.english.learnfast.R;

public class DialogFragmentNeedToPass extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int questions_rating_default = getResources().getInteger(R.integer.questions_rating_default);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Внимание");
        builder.setIcon(R.drawable.ic_warning_black_24dp);
        builder.setMessage(getString(R.string.lesson_locked,questions_rating_default) )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
