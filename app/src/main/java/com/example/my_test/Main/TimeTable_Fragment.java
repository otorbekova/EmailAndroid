package com.example.my_test.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_test.R;

public class TimeTable_Fragment extends Fragment {
private WebView webview;
private Button loadd;
private ImageView avn,rasp_group,rasp_teach,poisk;
    public TimeTable_Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_table_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview=view.findViewById(R.id.webview);
        avn=view.findViewById(R.id.avn);
        rasp_group=view.findViewById(R.id.rasp_stud);
        rasp_teach=view.findViewById(R.id.rasp_teach);
        poisk=view.findViewById(R.id.poisk);
      //  rasp_group=view.findViewById(R.id.rasp_stud);
        seton();



    }

    private void seton() {
        avn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // webview.loadUrl("view-source:https://avn.kstu.kg/ReportServer/Pages/ReportViewer.aspx?%2fVUZ%2fraspisanie_group&rs:Command=Render");

            webview.loadUrl("avn.kstu.kg");

            /*    String unencodedHtml =
                        "\n" +
                                "</table></td><td width=\"6px\"></td><td class=\"SubmitButtonCell\"><table>";
                String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                        Base64.NO_PADDING);
                webview.loadData(encodedHtml, "text/html", "base64");*/
        }
    });

        rasp_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl("https://ru.wikipedia.org/wiki/%D0%92%D0%B8%D0%BA%D0%B8%D0%BF%D0%B5%D0%B4%D0%B8%D1%8F:%D0%A1%D0%BE%D0%B4%D0%B5%D1%80%D0%B6%D0%B0%D0%BD%D0%B8%D0%B5");
            }
        });

        rasp_teach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D1%80%D1%82%D0%B0%D0%BB:%D0%A2%D0%B5%D0%BA%D1%83%D1%89%D0%B8%D0%B5_%D1%81%D0%BE%D0%B1%D1%8B%D1%82%D0%B8%D1%8F");
            }
        });

        poisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl(" https://ru.wikipedia.org/wiki/%D0%92%D0%B8%D0%BA%D0%B8%D0%BF%D0%B5%D0%B4%D0%B8%D1%8F:%D0%98%D0%B7%D0%B1%D1%80%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5_%D1%81%D1%82%D0%B0%D1%82%D1%8C%D0%B8");
            }
        });
    }
}