package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.oalonedeveloper.oliver.oliverappandroidapp.WebViewActivity;

public class BusinessOportunityTool4Fragment extends Fragment {

    Button btnWebView1,btnWebView2,btnWebView3,btnWebView4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_oportunity_tool4, container, false);

        btnWebView1 = view.findViewById(R.id.btnWebView1);
        btnWebView2 = view.findViewById(R.id.btnWebView2);
        btnWebView3 = view.findViewById(R.id.btnWebView3);
        btnWebView4 = view.findViewById(R.id.btnWebView4);

        btnWebView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url","https://www.crecenegocios.com/negocios-rentables-en-peru/");
                startActivity(intent);
            }
        });

        btnWebView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url","https://www.emprendedores.es/ideas-de-negocio/online");
                startActivity(intent);
            }
        });

        btnWebView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url","https://franquicias.wearejeff.com/noticias/7-ideas-de-negocios-en-peru-para-2020");
                startActivity(intent);
            }
        });

        btnWebView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url","https://www.feriasinfo.es/Ferias-Per-Z171-S1.html");
                startActivity(intent);
            }
        });

        return view;
    }
}
