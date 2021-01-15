package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class FunctionsJobProfileFragment extends Fragment {

    Button btnRegister;
    DatabaseReference companyRef;
    String post_key,profile_id;
    int frequency,consequence,complex;
    DecimalFormat decimalFormat;
    RecyclerView recyclerView;

    ArrayList<String> arr_frequency =new ArrayList<>();
    SpinnerDialog frequencyDialog;
    ArrayList<String> arr_consequence =new ArrayList<>();
    SpinnerDialog consequenceDialog;
    ArrayList<String> arr_complex =new ArrayList<>();
    SpinnerDialog complexDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_functions_job_profile, container, false);

        btnRegister = view.findViewById(R.id.btnRegister);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showFunctionsJob();

        decimalFormat = new DecimalFormat("0.00");

        arr_frequency.add("Todos los días");arr_frequency.add("Al menos una vez por semana");arr_frequency.add("Al menos una vez cada quince días");arr_frequency.add("Al menos una vez al mes");arr_frequency.add("Otros (Bimestral, Trimestral, Semestral, Anual)");
        arr_consequence.add("Consecuencias muy graves: pueden afectar a toda la organización en múltiples aspectos."); arr_consequence.add("Consecuencias graves: pueden afectar resultados, procesos o áreas funcionales de la organización.");
        arr_consequence.add("Consecuencias considerables: repercuten negativamente en los resultados o trabajos de otros."); arr_consequence.add("Consecuencias menores: cierta incidencia en resultados o actividades que pertenecen al mismo puesto.");
        arr_consequence.add("Consecuencias mínimas: poca o ninguna incidencia en resultados o actividades.");
        arr_complex.add("Máxima complejidad: la actividad demanda el mayor grado de esfuerzo, conocimientos, habilidades."); arr_complex.add("Alta complejidad: la actividad demanda un considerable nivel de esfuerzo, conocimientos, habilidades.");
        arr_complex.add("Complejidad moderada: la actividad requiere un grado medio de esfuerzo, conocimientos, habilidades."); arr_complex.add("Baja complejidad: la actividad requiere un bajo nivel de esfuerzo, conocimientos, habilidades.");
        arr_complex.add("Mínima complejidad: la actividad requiere un minimo nivel de esfuerzo, conocimientos o habilidades.");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_function_job_dialog,null);

                final EditText edtFunction;
                final Button btnFrequency,btnConsecuence,btnComplex,btnRegister;
                final LinearLayout rootLayout;

                edtFunction = finance_method.findViewById(R.id.edtFunction);
                btnFrequency = finance_method.findViewById(R.id.btnFrequency);
                btnConsecuence = finance_method.findViewById(R.id.btnConsecuence);
                btnComplex = finance_method.findViewById(R.id.btnComplex);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);


                btnFrequency.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        frequencyDialog.showSpinerDialog();
                    }
                });

                frequencyDialog = new SpinnerDialog(getActivity(),arr_frequency, "Selecciona la frecuencia");
                frequencyDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnFrequency.setText(item2);
                        if (item2.equals("Todos los días")) {
                            frequency = 5;
                        }
                        if (item2.equals("Al menos una vez por semana")) {
                            frequency = 4;
                        }
                        if (item2.equals("Al menos una vez cada quince días")) {
                            frequency = 3;
                        }
                        if (item2.equals("Al menos una vez al mes")) {
                            frequency = 2;
                        }
                        if (item2.equals("Otros (Bimestral, Trimestral, Semestral, Anual")) {
                            frequency = 1;
                        }

                    }
                });

                btnConsecuence.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        consequenceDialog.showSpinerDialog();
                    }
                });

                consequenceDialog = new SpinnerDialog(getActivity(),arr_consequence, "Selecciona la consecuencia");
                consequenceDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnConsecuence.setText(item2);
                        if (item2.equals("Consecuencias muy graves: pueden afectar a toda la organización en múltiples aspectos.")) {
                            consequence = 5;
                        }
                        if (item2.equals("Consecuencias graves: pueden afectar resultados, procesos o áreas funcionales de la organización.")) {
                            consequence = 4;
                        }
                        if (item2.equals("Consecuencias considerables: repercuten negativamente en los resultados o trabajos de otros.")) {
                            consequence = 3;
                        }
                        if (item2.equals("Consecuencias menores: cierta incidencia en resultados o actividades que pertenecen al mismo puesto.")) {
                            consequence = 2;
                        }
                        if (item2.equals("Consecuencias mínimas: poca o ninguna incidencia en resultados o actividades.")) {
                            consequence = 1;
                        }

                    }
                });

                btnComplex.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        complexDialog.showSpinerDialog();
                    }
                });

                complexDialog = new SpinnerDialog(getActivity(),arr_complex, "Selecciona la consecuencia");
                complexDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnComplex.setText(item2);
                        if (item2.equals("Máxima complejidad: la actividad demanda el mayor grado de esfuerzo, conocimientos, habilidades.")) {
                            complex = 5;
                        }
                        if (item2.equals("Alta complejidad: la actividad demanda un considerable nivel de esfuerzo, conocimientos, habilidades.")) {
                            complex = 4;
                        }
                        if (item2.equals("Complejidad moderada: la actividad requiere un grado medio de esfuerzo, conocimientos, habilidades.")) {
                            complex = 3;
                        }
                        if (item2.equals("Baja complejidad: la actividad requiere un bajo nivel de esfuerzo, conocimientos, habilidades.")) {
                            complex = 2;
                        }
                        if (item2.equals("Mínima complejidad: la actividad requiere un minimo nivel de esfuerzo, conocimientos o habilidades.")) {
                            complex = 1;
                        }

                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtFunction.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes especificar la función", Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(btnFrequency.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes especificar la frecuencia", Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(btnConsecuence.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes especificar la consecuencia", Snackbar.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(btnComplex.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes especificar la complejidad", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Long tsLong = System.currentTimeMillis()/1000;
                            String timestamp = tsLong.toString();

                            double total = (consequence*complex)+frequency;
                            String total_st = decimalFormat.format(total);

                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Functions").child(timestamp).child("function_description").setValue(edtFunction.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Functions").child(timestamp).child("function_frequency").setValue(btnFrequency.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Functions").child(timestamp).child("function_consequence").setValue(btnConsecuence.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Functions").child(timestamp).child("function_complex").setValue(btnComplex.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Functions").child(timestamp).child("function_score").setValue(total_st);
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        return view;
    }

    private void showFunctionsJob() {
        Query query = companyRef.child(post_key).child("Job Profile").child(profile_id).child("Functions").orderByChild("timestamp");
        FirebaseRecyclerAdapter<FunctionJobModel,FunctionJobViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FunctionJobModel, FunctionJobViewHolder>
                (FunctionJobModel.class,R.layout.function_job_profile_item,FunctionJobViewHolder.class,query) {
            @Override
            protected void populateViewHolder(FunctionJobViewHolder viewHolder, FunctionJobModel model, int position) {
                viewHolder.setFunction_description(model.getFunction_description());
                viewHolder.setFunction_score(model.getFunction_score());

                viewHolder.txtFunction.setText(viewHolder.my_function_description);
                viewHolder.txtScore.setText(viewHolder.my_function_score);

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class FunctionJobViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtFunction,txtScore;
        String my_function_description,my_function_score;

        public FunctionJobViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            txtFunction = mView.findViewById(R.id.txtFunction);
            txtScore = mView.findViewById(R.id.txtScore);
        }
        public void setFunction_description(String function_description) {
            my_function_description = function_description;
        }


        public void setFunction_score(String function_score) {
            my_function_score = function_score;
        }
    }
}
