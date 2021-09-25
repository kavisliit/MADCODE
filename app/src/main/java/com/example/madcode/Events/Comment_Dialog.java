package com.example.madcode.Events;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.madcode.R;

public class Comment_Dialog  extends AppCompatDialogFragment {

    private EditText comment;
    private Commentdialoglistner listner;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.event_comment_layout,null);
        builder.setView(view)
                .setTitle("Enter the Comment")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String commentt = comment.getText().toString();
                        listner.applytext(commentt);
                    }
                });
        comment = view.findViewById(R.id.comment_txt);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listner = (Commentdialoglistner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"need to implement commentdialoglistner");
        }
    }

    public interface Commentdialoglistner{
        void applytext(String Comment);
    }
}
