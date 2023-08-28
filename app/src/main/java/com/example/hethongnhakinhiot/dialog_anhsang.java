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

public class dialog_anhsang extends AppCompatDialogFragment {
    EditText setupanhsang;
    DatabaseReference setup;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_anhsang, null);
        builder.setView(view)
                .setTitle("Thông số cài đặt ánh sáng")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setup= FirebaseDatabase.getInstance().getReference("SETTING");
                        Float cd1 = Float.parseFloat(setupanhsang.getText().toString());
                        setup.child("SETTING_LIGHT").setValue(cd1);
                    }
                });
        setupanhsang = view.findViewById(R.id.setupanhsang);
        return builder.create();

    }
}