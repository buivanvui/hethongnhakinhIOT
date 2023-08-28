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

public class dialog_doam extends AppCompatDialogFragment {
    EditText setupdoam;
    OKDialogListener listen;
    DatabaseReference setup;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_doam, null);
        builder.setView(view)
                .setTitle("Thông số cài đặt độ ẩm")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setup= FirebaseDatabase.getInstance().getReference("CÀI ĐẶT");
                        Float cd1 = Float.parseFloat(setupdoam.getText().toString());
                        setup.child("Cài đặt độ ẩm").setValue(cd1);
                        String kq=setupdoam.getText().toString();
                        listen.applyTexts2(kq);
                    }
                });
        setupdoam = view.findViewById(R.id.setupdoam);
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listen = (OKDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " ");
        }

    }
    public interface OKDialogListener{
        void applyTexts2(String data);
    }
}