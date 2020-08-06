package com.example.palleto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.room.Room;

public class Save_Pallet_Dialog extends AppCompatDialogFragment {

    private EditText palletName;
    private final String str_to_save;
    public AppDatabase db;

    public Save_Pallet_Dialog(String strtosave) {
        super();
        str_to_save = strtosave;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        db = Room.databaseBuilder(this.getContext(), AppDatabase.class, "pallet_db").build();

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.save_dialog, null);

        builder.setView(view)
                .setTitle("SAVE THE PALLET")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String pallet_name = palletName.getText().toString();
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Pallet pallet = new Pallet();
                                pallet.name = pallet_name;
                                pallet.code = str_to_save;
                                db.palletDAO().insert_Pallet(pallet);
                            }
                        });
                    }
                });
        palletName = view.findViewById(R.id.name_save);

        return builder.create();
    }


    public interface SaveDialogListener {
        void applyTexts(String name);
    }
}
