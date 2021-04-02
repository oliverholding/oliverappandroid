package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Normativity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;

public class NormativitySumaryActivity extends AppCompatActivity {

    TextView txtTaxesNormativity,txtLabourNormantivity,txtSanitaryNormativity,txtEnvironmentalNormativity,txtCivilResponsability;
    BubbleSeekBar taxesSeekBar,labourSeekBar,sanitarySeekBar,environmentalSeekBar,civilSeekBar;
    String post_key;
    DatabaseReference companyRef;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normativity_sumary);

        txtTaxesNormativity = findViewById(R.id.txtTaxesNormativity);
        txtLabourNormantivity = findViewById(R.id.txtLabourNormantivity);
        txtSanitaryNormativity = findViewById(R.id.txtSanitaryNormativity);
        txtEnvironmentalNormativity = findViewById(R.id.txtEnvironmentalNormativity);
        txtCivilResponsability = findViewById(R.id.txtCivilResponsability);

        taxesSeekBar = findViewById(R.id.taxesSeekBar);
        labourSeekBar = findViewById(R.id.labourSeekBar);
        sanitarySeekBar = findViewById(R.id.sanitarySeekBar);
        environmentalSeekBar = findViewById(R.id.environmentalSeekBar);
        civilSeekBar = findViewById(R.id.civilSeekBar);
        taxesSeekBar.setEnabled(false);
        labourSeekBar.setEnabled(false);
        sanitarySeekBar.setEnabled(false);
        environmentalSeekBar.setEnabled(false);
        civilSeekBar.setEnabled(false);

        decimalFormat = new DecimalFormat("0.00");

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef.child(post_key).child("Normativity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Taxes")) {
                    int norm_1 = dataSnapshot.child("Taxes").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Taxes").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Taxes").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Taxes").child("norm_4").getValue(Integer.class);
                    int norm_5 = dataSnapshot.child("Taxes").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Taxes").child("norm_6").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6);
                    double total_percent_real = (total_percent/6)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);
                    txtTaxesNormativity.setText(total_percent_st+"%");

                    taxesSeekBar.setProgress((float) total_percent_real);

                } if (dataSnapshot.hasChild("Labour")) {
                    int norm_1 = dataSnapshot.child("Labour").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Labour").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Labour").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Labour").child("norm_4").getValue(Integer.class);
                    int norm_5 = dataSnapshot.child("Labour").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Labour").child("norm_6").getValue(Integer.class);
                    int norm_7 = dataSnapshot.child("Labour").child("norm_7").getValue(Integer.class);
                    int norm_8 = dataSnapshot.child("Labour").child("norm_8").getValue(Integer.class);
                    int norm_9 = dataSnapshot.child("Labour").child("norm_9").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6+norm_7+norm_8+norm_9);
                    double total_percent_real = (total_percent/9)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);
                    txtLabourNormantivity.setText(total_percent_st+"%");

                    labourSeekBar.setProgress((float) total_percent_real);

                }
                if (dataSnapshot.hasChild("Sanitary")) {
                    int norm_1 = dataSnapshot.child("Sanitary").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Sanitary").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Sanitary").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Sanitary").child("norm_4").getValue(Integer.class);
                    int norm_5 = dataSnapshot.child("Sanitary").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Sanitary").child("norm_6").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6);
                    double total_percent_real = (total_percent/6)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);
                    txtSanitaryNormativity.setText(total_percent_st+"%");

                    sanitarySeekBar.setProgress((float) total_percent_real);

                }
                if (dataSnapshot.hasChild("Environmental")) {
                    int norm_1 = dataSnapshot.child("Environmental").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Environmental").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Environmental").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Environmental").child("norm_4").getValue(Integer.class);
                    int  norm_5 = dataSnapshot.child("Environmental").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Environmental").child("norm_6").getValue(Integer.class);
                    int norm_7 = dataSnapshot.child("Environmental").child("norm_7").getValue(Integer.class);
                    int norm_8 = dataSnapshot.child("Environmental").child("norm_8").getValue(Integer.class);
                    int norm_9 = dataSnapshot.child("Environmental").child("norm_9").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6+norm_7+norm_8+norm_9);
                    double total_percent_real = (total_percent/9)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);
                    txtEnvironmentalNormativity.setText(total_percent_st+"%");

                    environmentalSeekBar.setProgress((float) total_percent_real);

                }
                if (dataSnapshot.hasChild("Civil")) {
                    int norm_1 = dataSnapshot.child("Civil").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Civil").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Civil").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Civil").child("norm_4").getValue(Integer.class);
                    int norm_5 = dataSnapshot.child("Civil").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Civil").child("norm_6").getValue(Integer.class);
                    int norm_7 = dataSnapshot.child("Civil").child("norm_7").getValue(Integer.class);
                    int norm_8 = dataSnapshot.child("Civil").child("norm_8").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6+norm_7+norm_8);
                    double total_percent_real = (total_percent/8)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);
                    txtCivilResponsability.setText(total_percent_st+"%");

                    civilSeekBar.setProgress((float) total_percent_real);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
