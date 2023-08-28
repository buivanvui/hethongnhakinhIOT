package com.example.hethongnhakinhiot;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dialog_passkey extends AppCompatDialogFragment {
    EditText setup_key;
    DatabaseReference setup;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_passkey, null);
        setup_key=view.findViewById(R.id.setup_passkey);
        builder.setView(view)
                .setTitle("Cài đặt mật khẩu")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setup= FirebaseDatabase.getInstance().getReference("SETTING");
                        if(setup_key.getText().toString().length()>4)
                        {
                            Toast.makeText(getActivity().getApplicationContext(), "Mật khẩu không dài quá 4 số" , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            setup.child("setting_keypass").setValue(setup_key.getText().toString());
                            Toast.makeText(getActivity().getApplicationContext(), "Thay đổi mật khẩu thành công" , Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        return builder.create();

    }
}
