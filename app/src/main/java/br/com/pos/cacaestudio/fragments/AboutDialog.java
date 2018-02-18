package br.com.pos.cacaestudio.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.pos.cacaestudio.R;

/**
 * Created by livia on 17/02/2018.
 */

public class AboutDialog extends DialogFragment {
    //Método utilitário para mostrar o dialog
    public static void showAbout(FragmentManager fm){
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("dialog_about");
        if(prev!=null){
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        new AboutDialog().show(ft,"dialog_about");
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Cria HTML com o texto de sobre o aplicativo
        SpannableStringBuilder aboutBody = new SpannableStringBuilder();

        //Converte o texto do string.xml para HTML
        aboutBody.append(Html.fromHtml(getString(R.string.about_dialog_text)));

        //Infla o Layout
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        TextView view = (TextView) inflater.inflate(R.layout.dialog_about,null);
        view.setText(aboutBody);
        view.setMovementMethod(new LinkMovementMethod());

        //Cria o dialog customizado
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.about_dialog_tittle)
                .setView(view)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }
                )
                .create();
    }
}
