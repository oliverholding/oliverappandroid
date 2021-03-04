package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.StrategicMatrix;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class CompetitorsFragment extends Fragment {

    TextView txtCompetitorName1,txtCompetitorName2,txtCompetitorName3,txtCompetitorName4,txtAnswer1_1,txtAnswer1_2,txtAnswer1_3,txtAnswer1_4,txtAnswer1_5,txtAnswer1_6,
            txtAnswer2_1,txtAnswer2_2,txtAnswer2_3,txtAnswer2_4,txtAnswer2_5,txtAnswer2_6,txtAnswer3_1,txtAnswer3_2,txtAnswer3_3,txtAnswer3_4,txtAnswer3_5,txtAnswer3_6,
            txtAnswer4_1,txtAnswer4_2,txtAnswer4_3,txtAnswer4_4,txtAnswer4_5,txtAnswer4_6,txtResult1,txtResult2,txtResult3,txtResult4;
    ImageView btnItem1,btnItem2,btnItem3,btnItem4;
    DatabaseReference companyRef;
    String post_key;
    double factor_1,factor_2,factor_3,factor_4,factor_5,factor_6;
    CircleImageView imgCompanyProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_competitors, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtCompetitorName1 = view.findViewById(R.id.txtCompetitorName1);
        txtCompetitorName2 = view.findViewById(R.id.txtCompetitorName2);
        txtCompetitorName3 = view.findViewById(R.id.txtCompetitorName3);
        txtCompetitorName4 = view.findViewById(R.id.txtCompetitorName4);

        txtAnswer1_1 = view.findViewById(R.id.txtAnswer1_1);
        txtAnswer1_2 = view.findViewById(R.id.txtAnswer1_2);
        txtAnswer1_3 = view.findViewById(R.id.txtAnswer1_3);
        txtAnswer1_4 = view.findViewById(R.id.txtAnswer1_4);
        txtAnswer1_5 = view.findViewById(R.id.txtAnswer1_5);
        txtAnswer1_6 = view.findViewById(R.id.txtAnswer1_6);

        txtAnswer2_1 = view.findViewById(R.id.txtAnswer2_1);
        txtAnswer2_2 = view.findViewById(R.id.txtAnswer2_2);
        txtAnswer2_3 = view.findViewById(R.id.txtAnswer2_3);
        txtAnswer2_4 = view.findViewById(R.id.txtAnswer2_4);
        txtAnswer2_5 = view.findViewById(R.id.txtAnswer2_5);
        txtAnswer2_6 = view.findViewById(R.id.txtAnswer2_6);

        txtAnswer3_1 = view.findViewById(R.id.txtAnswer3_1);
        txtAnswer3_2 = view.findViewById(R.id.txtAnswer3_2);
        txtAnswer3_3 = view.findViewById(R.id.txtAnswer3_3);
        txtAnswer3_4 = view.findViewById(R.id.txtAnswer3_4);
        txtAnswer3_5 = view.findViewById(R.id.txtAnswer3_5);
        txtAnswer3_6 = view.findViewById(R.id.txtAnswer3_6);

        txtAnswer4_1 = view.findViewById(R.id.txtAnswer4_1);
        txtAnswer4_2 = view.findViewById(R.id.txtAnswer4_2);
        txtAnswer4_3 = view.findViewById(R.id.txtAnswer4_3);
        txtAnswer4_4 = view.findViewById(R.id.txtAnswer4_4);
        txtAnswer4_5 = view.findViewById(R.id.txtAnswer4_5);
        txtAnswer4_6 = view.findViewById(R.id.txtAnswer4_6);

        btnItem1 = view.findViewById(R.id.btnItem1);
        btnItem2 = view.findViewById(R.id.btnItem2);
        btnItem3 = view.findViewById(R.id.btnItem3);
        btnItem4 = view.findViewById(R.id.btnItem4);

        imgCompanyProfile = view.findViewById(R.id.imgCompanyProfile);

        txtResult1 = view.findViewById(R.id.txtResult1);
        txtResult2 = view.findViewById(R.id.txtResult2);
        txtResult3 = view.findViewById(R.id.txtResult3);
        txtResult4 = view.findViewById(R.id.txtResult4);

        companyRef.child(post_key).child("Strategic Matrix").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("competitor_name")) {
                    String value = dataSnapshot.child("competitor_name").getValue().toString();
                    txtCompetitorName1.setText("Competidor 1: "+value);
                }
                if (dataSnapshot.hasChild("answer_1")) {
                    String value = dataSnapshot.child("answer_1").getValue().toString();
                    txtAnswer1_1.setText("Ubicación Estratégica: "+value);
                }
                if (dataSnapshot.hasChild("answer_2")) {
                    String value = dataSnapshot.child("answer_2").getValue().toString();
                    txtAnswer1_2.setText("Cantidad de Clientes: "+value);
                }
                if (dataSnapshot.hasChild("answer_3")) {
                    String value = dataSnapshot.child("answer_3").getValue().toString();
                    txtAnswer1_3.setText("Incentivos para los clientes: "+value);
                }
                if (dataSnapshot.hasChild("answer_4")) {
                    String value = dataSnapshot.child("answer_4").getValue().toString();
                    txtAnswer1_4.setText("Infraestructura: "+value);
                }
                if (dataSnapshot.hasChild("answer_5")) {
                    String value = dataSnapshot.child("answer_5").getValue().toString();
                    txtAnswer1_5.setText("Fuerte presencia en Imagen y Marca: "+value);
                }
                if (dataSnapshot.hasChild("answer_6")) {
                    String value = dataSnapshot.child("answer_6").getValue().toString();
                    txtAnswer1_6.setText("Estrategia de Promociones y Publicidad: "+value);
                }
                if (dataSnapshot.hasChild("score")) {
                    double value = dataSnapshot.child("score").getValue(Double.class);
                    if (value < 1) {
                        txtResult1.setText("Resultado: Débil");
                    } else if (value >= 1 && value < 3) {
                        txtResult1.setText("Resultado: Medio");
                    } else if (value >= 3) {
                        txtResult1.setText("Resultado: Fuerte");
                    }
                }

                companyRef.child(post_key).child("Strategic Matrix").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("competitor_name")) {
                            String value = dataSnapshot.child("competitor_name").getValue().toString();
                            txtCompetitorName2.setText("Competidor 2: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_1")) {
                            String value = dataSnapshot.child("answer_1").getValue().toString();
                            txtAnswer2_1.setText("Ubicación Estratégica: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_2")) {
                            String value = dataSnapshot.child("answer_2").getValue().toString();
                            txtAnswer2_2.setText("Cantidad de Clientes: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_3")) {
                            String value = dataSnapshot.child("answer_3").getValue().toString();
                            txtAnswer2_3.setText("Incentivos para los clientes: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_4")) {
                            String value = dataSnapshot.child("answer_4").getValue().toString();
                            txtAnswer2_4.setText("Infraestructura: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_5")) {
                            String value = dataSnapshot.child("answer_5").getValue().toString();
                            txtAnswer2_5.setText("Fuerte presencia en Imagen y Marca: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_6")) {
                            String value = dataSnapshot.child("answer_6").getValue().toString();
                            txtAnswer2_6.setText("Estrategia de Promociones y Publicidad: "+value);
                        }
                        if (dataSnapshot.hasChild("score")) {
                            double value = dataSnapshot.child("score").getValue(Double.class);
                            if (value < 1) {
                                txtResult2.setText("Resultado: Débil");
                            } else if (value >= 1 && value < 3) {
                                txtResult2.setText("Resultado: Medio");
                            } else if (value >= 3) {
                                txtResult2.setText("Resultado: Fuerte");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                companyRef.child(post_key).child("Strategic Matrix").child("item_3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("competitor_name")) {
                            String value = dataSnapshot.child("comptitor_name").getValue().toString();
                            txtCompetitorName3.setText("Competidor 3: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_1")) {
                            String value = dataSnapshot.child("answer_1").getValue().toString();
                            txtAnswer3_1.setText("Ubicación Estratégica: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_2")) {
                            String value = dataSnapshot.child("answer_2").getValue().toString();
                            txtAnswer3_2.setText("Cantidad de Clientes: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_3")) {
                            String value = dataSnapshot.child("answer_3").getValue().toString();
                            txtAnswer3_3.setText("Incentivos para los clientes: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_4")) {
                            String value = dataSnapshot.child("answer_4").getValue().toString();
                            txtAnswer3_4.setText("Infraestructura: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_5")) {
                            String value = dataSnapshot.child("answer_5").getValue().toString();
                            txtAnswer3_5.setText("Fuerte presencia en Imagen y Marca: "+value);
                        }
                        if (dataSnapshot.hasChild("answer_6")) {
                            String value = dataSnapshot.child("answer_6").getValue().toString();
                            txtAnswer3_6.setText("Estrategia de Promociones y Publicidad: "+value);
                        }
                        if (dataSnapshot.hasChild("score")) {
                            double value = dataSnapshot.child("score").getValue(Double.class);
                            if (value < 1) {
                                txtResult3.setText("Resultado: Débil");
                            } else if (value >= 1 && value < 3) {
                                txtResult3.setText("Resultado: Medio");
                            } else if (value >= 3) {
                                txtResult3.setText("Resultado: Fuerte");
                            }
                        }

                        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                                String company_image = dataSnapshot.child("company_image").getValue().toString();
                                txtCompetitorName4.setText(company_social_reason);
                                Picasso.with(getActivity()).load(company_image).fit().into(imgCompanyProfile);

                                companyRef.child(post_key).child("Strategic Matrix").child("item_4").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("answer_1")) {
                                            String value = dataSnapshot.child("answer_1").getValue().toString();
                                            txtAnswer4_1.setText("Ubicación Estratégica: "+value);
                                        }
                                        if (dataSnapshot.hasChild("answer_2")) {
                                            String value = dataSnapshot.child("answer_2").getValue().toString();
                                            txtAnswer4_2.setText("Cantidad de Clientes: "+value);
                                        }
                                        if (dataSnapshot.hasChild("answer_3")) {
                                            String value = dataSnapshot.child("answer_3").getValue().toString();
                                            txtAnswer4_3.setText("Incentivos para los clientes: "+value);
                                        }
                                        if (dataSnapshot.hasChild("answer_4")) {
                                            String value = dataSnapshot.child("answer_4").getValue().toString();
                                            txtAnswer4_4.setText("Infraestructura: "+value);
                                        }
                                        if (dataSnapshot.hasChild("answer_5")) {
                                            String value = dataSnapshot.child("answer_5").getValue().toString();
                                            txtAnswer4_5.setText("Fuerte presencia en Imagen y Marca: "+value);
                                        }
                                        if (dataSnapshot.hasChild("answer_6")) {
                                            String value = dataSnapshot.child("answer_6").getValue().toString();
                                            txtAnswer4_6.setText("Estrategia de Promociones y Publicidad: "+value);
                                        }
                                        if (dataSnapshot.hasChild("score")) {
                                            double value = dataSnapshot.child("score").getValue(Double.class);
                                            if (value < 1) {
                                                txtResult4.setText("Resultado: Débil");
                                            } else if (value >= 1 && value < 3) {
                                                txtResult4.setText("Resultado: Medio");
                                            } else if (value >= 3) {
                                                txtResult4.setText("Resultado: Fuerte");
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_1";


                showAddCompetitorDialog(path);
            }
        });

        btnItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_2";


                showAddCompetitorDialog(path);
            }
        });

        btnItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_3";

                showAddCompetitorDialog(path);
            }
        });

        btnItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_4";

                showAddCompetitorDialog(path);
            }
        });

        return view;
    }

    private void showAddCompetitorDialog(final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_competitor_dialog,null);

        final EditText edtCompetitorName;
        final Button btnQuestion1,btnQuestion2,btnQuestion3,btnQuestion4,btnQuestion5,btnQuestion6,btnFinish;
        ArrayList<String> arr =new ArrayList<>();
        final SpinnerDialog spinnerDialog1,spinnerDialog2,spinnerDialog3,spinnerDialog4,spinnerDialog5,spinnerDialog6;
        final RelativeLayout rootLayout;
        CardView cardCompetitorName;


        edtCompetitorName = finance_method.findViewById(R.id.edtCompetitorName);
        btnQuestion1 = finance_method.findViewById(R.id.btnQuestion1);
        btnQuestion2 = finance_method.findViewById(R.id.btnQuestion2);
        btnQuestion3 = finance_method.findViewById(R.id.btnQuestion3);
        btnQuestion4 = finance_method.findViewById(R.id.btnQuestion4);
        btnQuestion5 = finance_method.findViewById(R.id.btnQuestion5);
        btnQuestion6 = finance_method.findViewById(R.id.btnQuestion6);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        cardCompetitorName = finance_method.findViewById(R.id.cardCompetitorName);

        if (path.equals("item_4")) {
            cardCompetitorName.setVisibility(View.GONE);
            edtCompetitorName.setText("company_social_reason");
        }


        arr.add("Debilidad Principal"); arr.add("Debilidad Menor"); arr.add("Fortaleza Menor"); arr.add("Fortaleza Principal");

        spinnerDialog1 = new SpinnerDialog(getActivity(),arr, "Selecciona una clasificación");
        btnQuestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog1.showSpinerDialog();
            }
        });
        spinnerDialog1.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnQuestion1.setText(item2);
                if (item2.equals("Debilidad Principal")) {
                    factor_1 = (0.20*1);
                }
                if (item2.equals("Debilidad Menor")) {
                    factor_1 = (0.20*2);
                }
                if (item2.equals("Fortaleza Menor")) {
                    factor_1 = (0.20*3);
                }
                if (item2.equals("Fortaleza Principal")) {
                    factor_1 = (0.20*4);
                }

            }
        });

        spinnerDialog2 = new SpinnerDialog(getActivity(),arr, "Selecciona una clasificación");
        btnQuestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog2.showSpinerDialog();
            }
        });
        spinnerDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnQuestion2.setText(item2);
                if (item2.equals("Debilidad Principal")) {
                    factor_2 = (0.10*1);
                }
                if (item2.equals("Debilidad Menor")) {
                    factor_2 = (0.10*2);
                }
                if (item2.equals("Fortaleza Menor")) {
                    factor_2 = (0.10*3);
                }
                if (item2.equals("Fortaleza Principal")) {
                    factor_2 = (0.10*4);
                }
            }
        });

        spinnerDialog3 = new SpinnerDialog(getActivity(),arr, "Selecciona una clasificación");
        btnQuestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog3.showSpinerDialog();
            }
        });
        spinnerDialog3.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnQuestion3.setText(item2);
                if (item2.equals("Debilidad Principal")) {
                    factor_3 = (0.15*1);
                }
                if (item2.equals("Debilidad Menor")) {
                    factor_3 = (0.15*2);
                }
                if (item2.equals("Fortaleza Menor")) {
                    factor_3 = (0.15*3);
                }
                if (item2.equals("Fortaleza Principal")) {
                    factor_3 = (0.15*4);
                }
            }
        });

        spinnerDialog4 = new SpinnerDialog(getActivity(),arr, "Selecciona una clasificación");
        btnQuestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog4.showSpinerDialog();
            }
        });
        spinnerDialog4.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnQuestion4.setText(item2);
                if (item2.equals("Debilidad Principal")) {
                    factor_4 = (0.15*1);
                }
                if (item2.equals("Debilidad Menor")) {
                    factor_4 = (0.15*2);
                }
                if (item2.equals("Fortaleza Menor")) {
                    factor_4 = (0.15*3);
                }
                if (item2.equals("Fortaleza Principal")) {
                    factor_4 = (0.15*4);
                }
            }
        });

        spinnerDialog5 = new SpinnerDialog(getActivity(),arr, "Selecciona una clasificación");
        btnQuestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog5.showSpinerDialog();
            }
        });
        spinnerDialog5.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnQuestion5.setText(item2);
                if (item2.equals("Debilidad Principal")) {
                    factor_5 = (0.20*1);
                }
                if (item2.equals("Debilidad Menor")) {
                    factor_5 = (0.20*2);
                }
                if (item2.equals("Fortaleza Menor")) {
                    factor_5 = (0.20*3);
                }
                if (item2.equals("Fortaleza Principal")) {
                    factor_5 = (0.20*4);
                }
            }
        });


        spinnerDialog6 = new SpinnerDialog(getActivity(),arr, "Selecciona una clasificación");
        btnQuestion6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog6.showSpinerDialog();
            }
        });
        spinnerDialog6.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnQuestion6.setText(item2);
                if (item2.equals("Debilidad Principal")) {
                    factor_6 = (0.20*1);
                }
                if (item2.equals("Debilidad Menor")) {
                    factor_6 = (0.20*2);
                }
                if (item2.equals("Fortaleza Menor")) {
                    factor_6 = (0.20*3);
                }
                if (item2.equals("Fortaleza Principal")) {
                    factor_6 = (0.20*4);
                }

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double total_factor = factor_1+factor_2+factor_3+factor_4+factor_5+factor_6;
                if (TextUtils.isEmpty(edtCompetitorName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes ingresar el nombre del competidor",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnQuestion1.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una califación",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnQuestion2.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una califación",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnQuestion3.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una califación",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnQuestion4.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una califación",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnQuestion5.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una califación",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnQuestion6.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar una califación", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Strategic Matrix").child(path).child("competitor_name").setValue(edtCompetitorName.getText().toString());
                    companyRef.child(post_key).child("Strategic Matrix").child(path).child("answer_1").setValue(btnQuestion1.getText().toString());
                    companyRef.child(post_key).child("Strategic Matrix").child(path).child("answer_2").setValue(btnQuestion2.getText().toString());
                    companyRef.child(post_key).child("Strategic Matrix").child(path).child("answer_3").setValue(btnQuestion3.getText().toString());
                    companyRef.child(post_key).child("Strategic Matrix").child(path).child("answer_4").setValue(btnQuestion4.getText().toString());
                    companyRef.child(post_key).child("Strategic Matrix").child(path).child("answer_5").setValue(btnQuestion5.getText().toString());
                    companyRef.child(post_key).child("Strategic Matrix").child(path).child("answer_6").setValue(btnQuestion6.getText().toString());
                    companyRef.child(post_key).child("Strategic Matrix").child(path).child("score").setValue(total_factor);

                    Toasty.success(getActivity(), "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
