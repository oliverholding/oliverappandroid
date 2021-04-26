package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.FODA;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.MarketInformation.MarketInformationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class FodaInternFragment extends Fragment {

    ImageView btnAddItem1,btnAddItem2;
    RecyclerView recyclerView1,recyclerView2;
    TextView txtResult;
    DatabaseReference companyRef;
    String post_key;
    double factor,sum1,sum2;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_foda_intern, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnAddItem1 = view.findViewById(R.id.btnAddItem1);
        btnAddItem2 = view.findViewById(R.id.btnAddItem2);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        txtResult = view.findViewById(R.id.txtResult);

        decimalFormat = new DecimalFormat("0.00");

        btnAddItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Mis Fortalezas";
                String question_1 = "Ingresa la fortaleza de tu empresa (Ejemplos de Fotalezas: Logística; Servicio al Cliente; Medio Ambiente;  Personal; Inversión e Innovación; etc)";
                String question_2 = "¿Qué tan importante es esta fortaleza? (0 = Menos Importante, 100 = Muy Importante) ";
                String question_3 = "Selecciona una característica para tu Fortaleza";
                String path = "fortaleza";

                showAddFodaDataDialog(title,question_1,question_2,question_3,path);
            }
        });

        btnAddItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Mis Debilidades";
                String question_1 = "Ingresa la debilidad de tu empresa (Ejemplo: Falta de Liderazgo del dueño, socios o gerente; poco capital para re-invertir, personal mal capacitado, etc)";
                String question_2 = "¿Qué tan importante es esta debilidad? (0 = Menos Importante, 100 = Muy Importante)";
                String question_3 = "Selecciona una característica para tu Debilidad";
                String path = "debilidad";

                showAddFodaDataDialog(title,question_1,question_2,question_3,path);
            }
        });

        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView1.setLayoutManager(linearLayoutManager);

        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setReverseLayout(false);
        linearLayoutManager2.setStackFromEnd(false);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        showFodaItems();

        getResult();

        return view;
    }

    private void getResult() {
        companyRef.child(post_key).child("FODA").orderByChild("item_type").equalTo("fortaleza").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object item_weighted_value = map.get("item_weighted_value");
                    double total_value = Double.parseDouble(String.valueOf(item_weighted_value));
                    sum1 += total_value;

                }

                companyRef.child(post_key).child("FODA").orderByChild("item_type").equalTo("debilidad").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sum2 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object item_weighted_value = map.get("item_weighted_value");
                            double total_value = Double.parseDouble(String.valueOf(item_weighted_value));
                            sum2 += total_value;

                            double result = sum1+sum2;
                            String result_st = decimalFormat.format(result);
                            txtResult.setText("Resultado "+result_st);

                            if (result <= 0.99) {
                                txtResult.setText("Los factores internos revelan que es debilidad catastrófica");
                            }
                            if (result >=1 && result <= 1.49) {
                                txtResult.setText("Los factores internos revelan que es debilidad muy grave");
                            }
                            if (result >=1.50 && result <= 1.99) {
                                txtResult.setText("Los factores internos revelan que es es grave");
                            }
                            if (result >=2.00 && result <= 2.49) {
                                txtResult.setText("Los factores internos revelan que es debilidad subsanable");
                            }
                            if (result >=2.50 && result <= 2.99) {
                                txtResult.setText("Los factores internos revelan que es debilidad en proceso de mejora");
                            }
                            if (result >=3.00 && result <= 3.49) {
                                txtResult.setText("Los factores internos revelan que es fortaleza débil");
                            }
                            if (result >=3.50 && result <= 3.99) {
                                txtResult.setText("Los factores internos revelan que es fortaleza normal");
                            }
                            if (result >=4.00 && result <= 4.49) {
                                txtResult.setText("Los factores internos revelan que es fortaleza importante");
                            }
                            if (result >=4.50 && result <= 4.99) {
                                txtResult.setText("Los factores internos revelan que es fortaleza sólida");
                            }
                            if (result >= 5.00) {
                                txtResult.setText("Los factores internos revelan que es fortaleza única y valiosa");
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

    private void showFodaItems() {
        Query query = companyRef.child(post_key).child("FODA").orderByChild("item_type").equalTo("fortaleza");
        FirebaseRecyclerAdapter<FodaModel,FodaViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FodaModel, FodaViewHolder>
                (FodaModel.class, R.layout.foda_item,FodaViewHolder.class,query) {
            @Override
            protected void populateViewHolder(FodaViewHolder viewHolder, FodaModel model, int position) {
                viewHolder.setItem_name(model.getItem_name());
                viewHolder.setItem_score(model.getItem_score());
                viewHolder.setItem_type(model.getItem_type());
                viewHolder.setItem_weight(model.getItem_weight());
                viewHolder.setItem_weighted_value(model.getItem_weighted_value());

                viewHolder.txtItemName.setText(viewHolder.my_item_name);
            }
        };
        recyclerView1.setAdapter(firebaseRecyclerAdapter);

        Query query2 = companyRef.child(post_key).child("FODA").orderByChild("item_type").equalTo("debilidad");
        FirebaseRecyclerAdapter<FodaModel,FodaViewHolder> firebaseRecyclerAdapter2 = new FirebaseRecyclerAdapter<FodaModel, FodaViewHolder>
                (FodaModel.class, R.layout.foda_item,FodaViewHolder.class,query2) {
            @Override
            protected void populateViewHolder(FodaViewHolder viewHolder, FodaModel model, int position) {
                viewHolder.setItem_name(model.getItem_name());
                viewHolder.setItem_score(model.getItem_score());
                viewHolder.setItem_type(model.getItem_type());
                viewHolder.setItem_weight(model.getItem_weight());
                viewHolder.setItem_weighted_value(model.getItem_weighted_value());

                viewHolder.txtItemName.setText(viewHolder.my_item_name);
            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter2);
    }

    private void showAddFodaDataDialog(String title,String question_1,String question_2,String question_3, final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.foda_add_data_dialog,null);

        TextView txtQuestion1,txtQuestion2,txtQuestion3,txtTitle;
        final EditText edtInput;
        final BubbleSeekBar seekBar;
        final Button btnSelection,btnFinish;
        final SpinnerDialog spinnerDialog;
        ArrayList<String> arr =new ArrayList<>();
        final LinearLayout rootLayout;

        txtQuestion1 = finance_method.findViewById(R.id.txtQuestion1);
        txtQuestion2 = finance_method.findViewById(R.id.txtQuestion2);
        txtQuestion3 = finance_method.findViewById(R.id.txtQuestion3);
        edtInput = finance_method.findViewById(R.id.edtInput);
        seekBar = finance_method.findViewById(R.id.seekBar);
        btnSelection = finance_method.findViewById(R.id.btnSelection);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        txtTitle = finance_method.findViewById(R.id.txtTitle);

        txtTitle.setText(title);
        txtQuestion1.setText(question_1);
        txtQuestion2.setText(question_2);
        txtQuestion3.setText(question_3);

        arr.add("es debilidad catastrófica");arr.add("es debilidad muy grave");arr.add("es grave");arr.add("es debilidad subsanable");arr.add("es debilidad en proceso de mejora");
        arr.add("es fortaleza débil");arr.add("es fortaleza normal");arr.add("es fortaleza importante");arr.add("es fortaleza sólida");arr.add("es fortaleza única y valiosa");


        spinnerDialog = new SpinnerDialog(getActivity(),arr, "Selecciona una de las alternativas");
        btnSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnSelection.setText(item2);
                if (item2.equals("es debilidad catastrófica")) {
                    factor = 0.5;
                }if (item2.equals("es debilidad muy grave")) {
                    factor = 1.0;
                }if (item2.equals("es grave")) {
                    factor = 1.5;
                }if (item2.equals("es debilidad subsanable")) {
                    factor = 2.0;
                }if (item2.equals("es debilidad en proceso de mejora")) {
                    factor = 2.5;
                }if (item2.equals("es fortaleza débil")) {
                    factor = 3.0;
                }if (item2.equals("es fortaleza normal")) {
                    factor = 3.5;
                }if (item2.equals("es fortaleza importante")) {
                    factor = 4.0;
                }if (item2.equals("es fortaleza sólida")) {
                    factor = 4.5;
                }if (item2.equals("es fortaleza única y valiosa")) {
                    factor = 5.0;
                }



                btnFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtInput.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes ingresar un valor",Snackbar.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(btnFinish.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Long tsLong = System.currentTimeMillis()/1000;
                            String timestamp = tsLong.toString();

                            double weight = (seekBar.getProgress());

                            double value_1000 = (weight/1000);

                            double weighted_value = value_1000*factor;

                            companyRef.child(post_key).child("FODA").child(timestamp).child("item_name").setValue(edtInput.getText().toString());
                            companyRef.child(post_key).child("FODA").child(timestamp).child("item_score").setValue(factor+"");
                            companyRef.child(post_key).child("FODA").child(timestamp).child("item_weight").setValue(seekBar.getProgress()+"");
                            companyRef.child(post_key).child("FODA").child(timestamp).child("item_weighted_value").setValue(weighted_value+"");
                            companyRef.child(post_key).child("FODA").child(timestamp).child("item_type").setValue(path);
                            companyRef.child(post_key).child("FODA").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                            Toasty.success(getActivity(), "Actualizado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        }
                    }
                });


            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    public static class FodaViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtItemName;
        ImageView btnActionButton;
        String my_item_name,my_item_score,my_item_weight,my_item_weighted_value,my_item_type;

        public FodaViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtItemName = mView.findViewById(R.id.txtItemName);
            btnActionButton = mView.findViewById(R.id.btnActionButton);

        }

        public void setItem_name(String item_name) {
            my_item_name = item_name;
        }

        public void setItem_score(String item_score) {
            my_item_score = item_score;
        }

        public void setItem_weight(String item_weight) {
            my_item_weight = item_weight;
        }

        public void setItem_weighted_value(String item_weighted_value) {
            my_item_weighted_value = item_weighted_value;
        }


        public void setItem_type(String item_type) {
            my_item_type = item_type;
        }
    }
}
