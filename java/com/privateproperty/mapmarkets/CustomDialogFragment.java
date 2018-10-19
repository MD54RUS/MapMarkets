package com.privateproperty.mapmarkets;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

public class CustomDialogFragment extends DialogFragment implements
        DialogInterface.OnClickListener {
    private View form=null;
    private String LocalClass;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        //Log.i("MM","Вызов диалога activity: " + getActivity().toString() + " CallingActivity  "+getActivity().getLocalClassName());
        LocalClass = getActivity().getLocalClassName();


        form= getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_input_text, null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        String title = "Добавить";
        if (LocalClass.equalsIgnoreCase("ListOfLists")) {
            title = "Добавить список";

        }
        else if (LocalClass.equalsIgnoreCase("ShoppingList")) {
            title = "Добавить покупку";
        }
        return (builder
                .setTitle(title)
                .setView(form)
                .setNeutralButton("подтвердить", this)

                .create());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        EditText inputBox=(EditText)form.findViewById(R.id.dialog_textBox);

        String name = inputBox.getText().toString();
        DBDataManager DataManager = DBDataManager.get(getContext());


        if (LocalClass.equalsIgnoreCase("ListOfLists")) {
            DataManager.addShopList(name);
            Intent intent = new Intent(getContext(),ShoppingList.class);
            intent.putExtra("LOLname",name);
            startActivity(intent);

        }
        else if (LocalClass.equalsIgnoreCase("ShoppingList")) {
            String shopList = getActivity().getIntent().getStringExtra("LOLname");
            DataManager.addShopInList(name,shopList);
            Intent intent = new Intent(getContext(),ShoppingList.class);
            intent.putExtra("LOLname",shopList);
            startActivity(intent);


        }



    }

    @Override
    public void onDismiss(DialogInterface unused) {
        super.onDismiss(unused);
    }

    @Override
    public void onCancel(DialogInterface unused) {
        super.onCancel(unused);
    }
}