package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.RiskManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class RiskSumaryFragment extends Fragment {

    TextView txtHighRisk1,txtMiddleRisk1,txtTotalRisk1,txtHighRisk2,txtMiddleRisk2,txtTotalRisk2,txtHighRisk3,txtMiddleRisk3,txtTotalRisk3,txtHighRisk4,txtMiddleRisk4,txtTotalRisk4,txtHighRisk5,txtMiddleRisk5,txtTotalRisk5,
            txtHighRisk6,txtMiddleRisk6,txtTotalRisk6,txtHighRisk7,txtMiddleRisk7,txtTotalRisk7,txtHighRisk8,txtMiddleRisk8,txtTotalRisk8,txtHighRisk9,txtMiddleRisk9,txtTotalRisk9,txtTotalHigh,txtTotalMiddle,txtTotal;
    DatabaseReference companyRef;
    String post_key;
    int count_high_1,count_high_2,count_high_3,count_high_4,count_high_5,count_high_6,count_high_7,count_high_8,count_high_9,count_middle_1,count_middle_2,count_middle_3,count_middle_4,count_middle_5,count_middle_6,count_middle_7,
            count_middle_8,count_middle_9,total_1,total_2,total_3,total_4,total_5,total_6,total_7,total_8,total_9,total_high,total_middle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_risk_sumary, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtHighRisk1 = view.findViewById(R.id.txtHighRisk1);
        txtHighRisk2 = view.findViewById(R.id.txtHighRisk2);
        txtHighRisk3 = view.findViewById(R.id.txtHighRisk3);
        txtHighRisk4 = view.findViewById(R.id.txtHighRisk4);
        txtHighRisk5 = view.findViewById(R.id.txtHighRisk5);
        txtHighRisk6 = view.findViewById(R.id.txtHighRisk6);
        txtHighRisk7 = view.findViewById(R.id.txtHighRisk7);
        txtHighRisk8 = view.findViewById(R.id.txtHighRisk8);
        txtHighRisk9 = view.findViewById(R.id.txtHighRisk9);
        txtMiddleRisk1 = view.findViewById(R.id.txtMiddleRisk1);
        txtMiddleRisk2 = view.findViewById(R.id.txtMiddleRisk2);
        txtMiddleRisk3 = view.findViewById(R.id.txtMiddleRisk3);
        txtMiddleRisk4 = view.findViewById(R.id.txtMiddleRisk4);
        txtMiddleRisk5 = view.findViewById(R.id.txtMiddleRisk5);
        txtMiddleRisk6 = view.findViewById(R.id.txtMiddleRisk6);
        txtMiddleRisk7 = view.findViewById(R.id.txtMiddleRisk7);
        txtMiddleRisk8 = view.findViewById(R.id.txtMiddleRisk8);
        txtMiddleRisk9 = view.findViewById(R.id.txtMiddleRisk9);
        txtTotalRisk1 = view.findViewById(R.id.txtTotalRisk1);
        txtTotalRisk2 = view.findViewById(R.id.txtTotalRisk2);
        txtTotalRisk3 = view.findViewById(R.id.txtTotalRisk3);
        txtTotalRisk4 = view.findViewById(R.id.txtTotalRisk4);
        txtTotalRisk5 = view.findViewById(R.id.txtTotalRisk5);
        txtTotalRisk6 = view.findViewById(R.id.txtTotalRisk6);
        txtTotalRisk7 = view.findViewById(R.id.txtTotalRisk7);
        txtTotalRisk8 = view.findViewById(R.id.txtTotalRisk8);
        txtTotalRisk9 = view.findViewById(R.id.txtTotalRisk9);
        txtTotalHigh = view.findViewById(R.id.txtTotalHigh);
        txtTotalMiddle = view.findViewById(R.id.txtTotalMiddle);
        txtTotal = view.findViewById(R.id.txtTotal);

        companyRef.child(post_key).child("Risks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count_high_1 = 0;
                count_high_2 = 0;
                count_high_3 = 0;
                count_high_4 = 0;
                count_high_5 = 0;
                count_high_6 = 0;
                count_high_7 = 0;
                count_high_8 = 0;
                count_high_9 = 0;
                count_middle_1 = 0;
                count_middle_2 = 0;
                count_middle_3 = 0;
                count_middle_4 = 0;
                count_middle_5 = 0;
                count_middle_6 = 0;
                count_middle_7 = 0;
                count_middle_8 = 0;
                count_middle_9 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.hasChild("risk_area")) {
                        String risk_area = ds.child("risk_area").getValue().toString();
                        String risk_impact = ds.child("risk_impact").getValue().toString();

                        if (risk_area.equals("Gerencia General")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_1 += 1;
                                txtHighRisk1.setText(count_high_1+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_1 += 1;
                                txtMiddleRisk1.setText(count_middle_1+"");
                            }

                            total_1 = count_high_1+count_middle_1;
                            txtTotalRisk1.setText(total_1+"");
                        }

                        if (risk_area.equals("Gestión de Ventas")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_2 += 1;
                                txtHighRisk2.setText(count_high_2+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_2 += 1;
                                txtMiddleRisk2.setText(count_middle_2+"");
                            }
                            total_2 = count_high_2+count_middle_2;
                            txtTotalRisk2.setText(total_2+"");
                        }

                        if (risk_area.equals("Gestión de Cadena De Suministro")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_3 += 1;
                                txtHighRisk3.setText(count_high_3+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_3 += 1;
                                txtMiddleRisk3.setText(count_middle_3+"");
                            }
                            total_3 = count_high_3+count_middle_3;
                            txtTotalRisk3.setText(total_3+"");
                        }

                        if (risk_area.equals("Gestión de Contabilidad")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_4 += 1;
                                txtHighRisk4.setText(count_high_4+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_4 += 1;
                                txtMiddleRisk4.setText(count_middle_4+"");
                            }

                            total_4 = count_high_4+count_middle_4;
                            txtTotalRisk4.setText(total_4+"");
                        }

                        if (risk_area.equals("Gestión de Distribución")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_5 += 1;
                                txtHighRisk5.setText(count_high_5+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_5 += 1;
                                txtMiddleRisk5.setText(count_middle_5+"");
                            }

                            total_5 = count_high_5+count_middle_5;
                            txtTotalRisk5.setText(total_5+"");
                        }

                        if (risk_area.equals("Gestión de Finanzas")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_6 += 1;
                                txtHighRisk6.setText(count_high_6+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_6 += 1;
                                txtMiddleRisk6.setText(count_middle_6+"");
                            }

                            total_6 = count_high_6+count_middle_6;
                            txtTotalRisk6.setText(total_6+"");
                        }

                        if (risk_area.equals("Gestión de Producción")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_7 += 1;
                                txtHighRisk7.setText(count_high_7+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_7 += 1;
                                txtMiddleRisk7.setText(count_middle_7+"");
                            }

                            total_7 = count_high_7+count_middle_7;
                            txtTotalRisk7.setText(total_7+"");
                        }

                        if (risk_area.equals("Gestión de Recursos Humanos")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_8 += 1;
                                txtHighRisk8.setText(count_high_8+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_8 += 1;
                                txtMiddleRisk8.setText(count_middle_8+"");
                            }

                            total_8 = count_high_8+count_middle_8;
                            txtTotalRisk8.setText(total_8+"");
                        }

                        if (risk_area.equals("Gestión de TI")) {

                            if (risk_impact.equals("Alto")) {
                                count_high_9 += 1;
                                txtHighRisk9.setText(count_high_9+"");
                            }
                            if (risk_impact.equals("Medio")) {
                                count_middle_9 += 1;
                                txtMiddleRisk9.setText(count_middle_9+"");
                            }

                            total_9 = count_high_9+count_middle_9;
                            txtTotalRisk9.setText(total_9+"");
                        }
                    }

                    total_high = count_high_1+count_high_2+count_high_3+count_high_4+count_high_5+count_high_6+count_high_7+count_high_8+count_high_9;
                    total_middle = count_middle_1+count_middle_2+count_middle_3+count_middle_4+count_middle_5+count_middle_6+count_middle_7+count_middle_8+count_middle_9;

                    txtTotalHigh.setText(total_high+"");
                    txtTotalMiddle.setText(total_middle+"");

                    int total_risks = total_high+total_middle;

                    txtTotal.setText(total_risks+"");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
