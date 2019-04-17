package com.english.learnfast.AlertDialogsApp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.english.learnfast.Models.ConstantsApp;
import com.english.learnfast.Models.PreferencesApp;
import com.english.learnfast.R;

public class DialogFragmentFirstUserEntranceNewWords extends DialogFragment {

    public CheckBox dontShowAgain;
    private CheckBox checkBox;
    private LinearLayout mRootLayout;
    private PreferencesApp preferencesApp;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        preferencesApp = new PreferencesApp(getContext());

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater adbInflater = LayoutInflater.from(getActivity());
        View eulaLayout = adbInflater.inflate(R.layout.alert_dialog_checkbox, null);
        initView(eulaLayout);

        builder.setView(eulaLayout);
        builder.setTitle(R.string.alert_dialog_title);
        builder.setIcon(R.drawable.instructions);

        builder.setMessage(R.string.dialog_text_first_new_words)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (checkBox.isChecked()) {
                            preferencesApp.onSaveBoolean(ConstantsApp.IS_SHOW_ALERT_DIALOG_ON_FIRST_USER_ENTRANCE_NEW_WORDS_KEY, true);

                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void initView(@NonNull final View itemView) {
        checkBox = (CheckBox) itemView.findViewById(R.id.chechbox_dont_show);
        mRootLayout = (LinearLayout) itemView.findViewById(R.id.layout_root);
    }


}
