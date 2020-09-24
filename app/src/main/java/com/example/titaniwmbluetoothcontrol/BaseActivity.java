package com.example.titaniwmbluetoothcontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BaseActivity implements ViewTreeObserver.OnGlobalLayoutListener {

   private View view;
   private Context context;

    public void setView(View view, Context context)
    {
        this.view = view;
        this.context  = context;
    }

    @Override
    public void onGlobalLayout() {
        TelaNovoLayoutsPersonalizados tela = (TelaNovoLayoutsPersonalizados) context;

        int heightDiff = view.getRootView().getHeight() - view.getHeight();
        int contentViewTop = tela.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(tela);

        if (heightDiff <= contentViewTop) {
            //onHideKeyboard();
            Toast.makeText(tela, " keyboard esconder", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("KeyboardWillHide");
            broadcastManager.sendBroadcast(intent);
        } else {
            int keyboardHeight = heightDiff - contentViewTop;
            //onShowKeyboard(keyboardHeight);
            Toast.makeText(tela, " keyboard visivel", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent("KeyboardWillShow");
            intent.putExtra("KeyboardHeight", keyboardHeight);
            broadcastManager.sendBroadcast(intent);
        }
    }
}