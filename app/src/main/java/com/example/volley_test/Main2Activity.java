package com.example.volley_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    String userId, Id, Title, Complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            userId = String.valueOf(bundle.getInt("userId"));
            Id = String.valueOf(bundle.getInt("Id"));
            Title = bundle.getString("title");
            Complete = String.valueOf(bundle.getBoolean("complete"));
        }

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);
        TextView tv_userId_dialog = view.findViewById(R.id.userId_dialog);
        TextView tv_Id_dialog = view.findViewById(R.id.Id_dialog);
        TextView tv_Title_dialog = view.findViewById(R.id.title_dialog);
        TextView tv_Complete_dialog = view.findViewById(R.id.complete_dialog);

        tv_userId_dialog.setText(userId);
        tv_Id_dialog.setText(Id);
        tv_Title_dialog.setText(Title);
        tv_Complete_dialog.setText(Complete);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông tin chi tiết");
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }
}
