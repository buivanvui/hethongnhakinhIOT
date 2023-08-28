package com.example.hethongnhakinhiot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dialog_nhietdo extends AppCompatDialogFragment {
    EditText setupnhietdo;
    DatabaseReference setup;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_nhietdo, null);
        builder.setView(view)
                .setTitle("Thông số cài đặt nhiệt độ")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setup= FirebaseDatabase.getInstance().getReference("SETTING");
                        Integer cd1 = Integer.parseInt(setupnhietdo.getText().toString());
                        setup.child("SETTING_TEMPERATURE").setValue(cd1);
                    }
                });
        setupnhietdo = view.findViewById(R.id.setupnhietdo);
        return builder.create();

    }
}