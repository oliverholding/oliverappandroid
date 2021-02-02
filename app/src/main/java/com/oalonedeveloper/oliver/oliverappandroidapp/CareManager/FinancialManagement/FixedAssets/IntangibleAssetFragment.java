package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FixedAssets;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import org.joda.time.Period;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class IntangibleAssetFragment extends Fragment {

    Button btnRegister;
    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;

    ArrayList<String> arr_asset =new ArrayList<>();
    SpinnerDialog assetTypeDialog;

    DatabaseReference companyRef;
    String post_key, total_assets_value;

    DecimalFormat decimalFormat;

    RecyclerView recyclerView;

    int day,month,year;

    double sum;

    TextView txtTotalAssetsAmount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intangible_asset, container, false);
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        btnRegister = view.findViewById(R.id.btnRegister);
        txtTotalAssetsAmount = view.findViewById(R.id.txtTotalAssetsAmount);

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        calculateTotalSum();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_new_tangible_asset_dialog,null);

                final Button btnAssetType,edtBthDay,edtBthMonth,edtBthYear,btnRegisterAsset;
                final EditText edtFixedAssetName,edtPrice,edtQuantity;
                final LinearLayout rootLayout;

                btnAssetType = finance_method.findViewById(R.id.btnAssetType);
                edtBthDay = finance_method.findViewById(R.id.edtBthDay);
                edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
                edtBthYear = finance_method.findViewById(R.id.edtBthYear);
                btnRegisterAsset = finance_method.findViewById(R.id.btnRegisterAsset);
                edtFixedAssetName = finance_method.findViewById(R.id.edtFixedAssetName);
                edtPrice = finance_method.findViewById(R.id.edtPrice);
                edtQuantity = finance_method.findViewById(R.id.edtQuantity);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnAssetType.setEnabled(false);
                btnAssetType.setText("Intangible");

                arr_asset.add("Ganado de trabajo y reproducción; redes de pesca"); arr_asset.add("Vehículos de transporte terrestre (excepto ferrocarriles); hornos en general"); arr_asset.add("Maquinaria y equipo utilizados por las actividades minera, petrolera y de construcción, excepto muebles, enseres y equipos de oficina");
                arr_asset.add("Equipos de procesamiento de datos"); arr_asset.add("Maquinaria y equipo adquirido a partir del 1.1.1991"); arr_asset.add("Otros bienes del activo fijo");

                btnAssetType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assetTypeDialog.showSpinerDialog();
                    }
                });

                assetTypeDialog = new SpinnerDialog(getActivity(),arr_asset, "Selecciona el Tipo de Activo");
                assetTypeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnAssetType.setText(item2);
                    }
                });

                bthDay.add("1"); bthDay.add("2"); bthDay.add("3"); bthDay.add("4"); bthDay.add("5"); bthDay.add("6"); bthDay.add("7"); bthDay.add("8"); bthDay.add("9"); bthDay.add("10");
                bthDay.add("11"); bthDay.add("12"); bthDay.add("13"); bthDay.add("14"); bthDay.add("15"); bthDay.add("16"); bthDay.add("17"); bthDay.add("18"); bthDay.add("19"); bthDay.add("20");
                bthDay.add("21"); bthDay.add("22"); bthDay.add("23"); bthDay.add("24"); bthDay.add("25"); bthDay.add("26"); bthDay.add("27"); bthDay.add("28"); bthDay.add("29"); bthDay.add("30");
                bthDay.add("31");

                edtBthDay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthDayDialog.showSpinerDialog();
                    }
                });

                bthDayDialog = new SpinnerDialog(getActivity(),bthDay, "Selecciona tu Día de Nacimiento");
                bthDayDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthDay.setText(item2);
                    }
                });

                bthMonth.add("1");bthMonth.add("2");bthMonth.add("3");bthMonth.add("4");bthMonth.add("5");bthMonth.add("6");bthMonth.add("7");bthMonth.add("8");bthMonth.add("9");bthMonth.add("10");
                bthMonth.add("11");bthMonth.add("12");

                edtBthMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthMonthDialog.showSpinerDialog();
                    }
                });

                bthMonthDialog = new SpinnerDialog(getActivity(),bthMonth, "Selecciona tu Mes de Nacimiento");
                bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthMonth.setText(item2);
                    }
                });

                bthYear.add("2021");bthYear.add("2020");bthYear.add("2019");bthYear.add("2018");bthYear.add("2017");bthYear.add("2016");bthYear.add("2015");bthYear.add("2014");bthYear.add("2013");bthYear.add("2012");bthYear.add("2011");bthYear.add("2010");
                bthYear.add("2009");bthYear.add("2008");bthYear.add("2007");bthYear.add("2006");bthYear.add("2005");bthYear.add("2004");bthYear.add("2003");bthYear.add("2002");bthYear.add("2001");bthYear.add("2000");
                bthYear.add("1999");bthYear.add("1998");bthYear.add("1997");bthYear.add("1996");bthYear.add("1995");bthYear.add("1994");bthYear.add("1993");bthYear.add("1992");bthYear.add("1991");bthYear.add("1990");
                bthYear.add("1989");bthYear.add("1988");bthYear.add("1987");bthYear.add("1986");bthYear.add("1985");bthYear.add("1984");bthYear.add("1983");bthYear.add("1982");bthYear.add("1981");bthYear.add("1980");
                bthYear.add("1979");bthYear.add("1978");bthYear.add("1977");bthYear.add("1976");bthYear.add("1975");bthYear.add("1974");bthYear.add("1973");bthYear.add("1972");bthYear.add("1971");bthYear.add("1970");
                bthYear.add("1969");bthYear.add("1968");bthYear.add("1967");bthYear.add("1966");bthYear.add("1965");bthYear.add("1964");bthYear.add("1963");bthYear.add("1962");bthYear.add("1961");bthYear.add("1960");
                bthYear.add("1959");bthYear.add("1958");bthYear.add("1957");bthYear.add("1956");bthYear.add("1955");bthYear.add("1954");bthYear.add("1953");bthYear.add("1952");bthYear.add("1951");bthYear.add("1950");
                bthYear.add("1949");bthYear.add("1948");bthYear.add("1947");bthYear.add("1946");bthYear.add("1945");bthYear.add("1944");bthYear.add("1943");bthYear.add("1942");bthYear.add("1941");bthYear.add("1940");
                bthYear.add("1939");bthYear.add("1938");bthYear.add("1937");bthYear.add("1936");bthYear.add("1935");bthYear.add("1934");bthYear.add("1933");bthYear.add("1932");bthYear.add("1931");bthYear.add("1930");

                edtBthYear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthYearDialog.showSpinerDialog();
                    }
                });

                bthYearDialog = new SpinnerDialog(getActivity(),bthYear, "Selecciona tu Año de Nacimiento");
                bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthYear.setText(item2);

                    }
                });

                btnRegisterAsset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtFixedAssetName.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes ingresar el nombre del activo",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(btnAssetType.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes seleccionar el tipo de activo",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(edtBthDay.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes seleccionar el día",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(edtBthMonth.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes seleccionar el mes",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(edtBthYear.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes seleccionar el año",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(edtPrice.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes ingresar el precio",Snackbar.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(edtQuantity.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes ingresar la cantidad", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Long tsLong = System.currentTimeMillis()/1000;
                            String timestamp = tsLong.toString();

                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("asset_name").setValue(edtFixedAssetName.getText().toString());
                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("asset_type").setValue(btnAssetType.getText().toString());
                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("asset_purchased_day").setValue(edtBthDay.getText().toString());
                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("asset_purchased_month").setValue(edtBthMonth.getText().toString());
                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("asset_purchased_year").setValue(edtBthYear.getText().toString());
                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("asset_price").setValue(edtPrice.getText().toString());
                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("asset_quantity").setValue(edtQuantity.getText().toString());
                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                            companyRef.child(post_key).child("Fixed Assets").child("Intangible").child(timestamp).child("asset_depreciation_rate").setValue("0");

                            Toasty.success(getActivity(), "Activo Registrado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            calculateTotalSum();

                        }
                    }
                });


                dialog.setView(finance_method);
                dialog.show();
            }
        });

        showFixedAssets();

        return view;
    }

    private void calculateTotalSum() {
        companyRef.child(post_key).child("Fixed Assets").child("Intangible").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object asset_price = map.get("asset_price");
                    Object asset_quantity = map.get("asset_quantity");
                    double asset_price_db = Double.parseDouble((String) asset_price);
                    double asset_quantity_db = Double.parseDouble((String) asset_quantity);
                    double total = asset_price_db*asset_quantity_db;
                    sum += total;
                    total_assets_value = decimalFormat.format(sum);
                    txtTotalAssetsAmount.setText("Total Activos Fijos Tangibles: S/ "+total_assets_value);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showFixedAssets() {
        Query query = companyRef.child(post_key).child("Fixed Assets").child("Intangible").orderByChild("timestamp");
        FirebaseRecyclerAdapter<FixedAssetTangibleModel, FixedAssetTangibleViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FixedAssetTangibleModel, FixedAssetTangibleViewHolder>
                (FixedAssetTangibleModel.class,R.layout.fixed_asset_tangible_item,FixedAssetTangibleViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final FixedAssetTangibleViewHolder viewHolder, FixedAssetTangibleModel model, int position) {
                viewHolder.setAsset_depreciation_rate(model.getAsset_depreciation_rate());
                viewHolder.setAsset_name(model.getAsset_name());
                viewHolder.setAsset_price(model.getAsset_price());
                viewHolder.setAsset_purchased_day(model.getAsset_purchased_day());
                viewHolder.setAsset_purchased_month(model.getAsset_purchased_month());
                viewHolder.setAsset_purchased_year(model.getAsset_purchased_year());
                viewHolder.setAsset_quantity(model.getAsset_quantity());
                viewHolder.setAsset_type(model.getAsset_type());

                viewHolder.txtAssetName.setText(viewHolder.my_asset_name);
                viewHolder.txtUnitCost.setText("S/ "+viewHolder.my_asset_price);
                viewHolder.txtQuantity.setText(viewHolder.my_asset_quantity);

                if (viewHolder.my_asset_price != null && viewHolder.my_asset_quantity != null) {
                    double price = Double.parseDouble(viewHolder.my_asset_price);
                    double quantity = Double.parseDouble(viewHolder.my_asset_quantity);
                    final double total = price*quantity;
                    String total_st = decimalFormat.format(total);
                    viewHolder.txtTotal.setText("S/ "+total_st);
                }

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class FixedAssetTangibleViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_asset_depreciation_rate,my_asset_name,my_asset_price,my_asset_purchased_day,my_asset_purchased_month,my_asset_purchased_year,my_asset_quantity,my_asset_type;
        TextView txtAssetName,txtUnitCost,txtQuantity,txtTotal,txtShowDeprecation;

        public FixedAssetTangibleViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            txtAssetName = mView.findViewById(R.id.txtAssetName);
            txtUnitCost = mView.findViewById(R.id.txtUnitCost);
            txtQuantity = mView.findViewById(R.id.txtQuantity);
            txtTotal = mView.findViewById(R.id.txtTotal);
            txtShowDeprecation = mView.findViewById(R.id.txtShowDeprecation);

            txtShowDeprecation.setVisibility(View.GONE);

        }

        public void setAsset_depreciation_rate(String asset_depreciation_rate) {
            my_asset_depreciation_rate = asset_depreciation_rate;
        }

        public void setAsset_name(String asset_name) {
            my_asset_name = asset_name;
        }

        public void setAsset_price(String asset_price) {
            my_asset_price = asset_price;
        }

        public void setAsset_purchased_day(String asset_purchased_day) {
            my_asset_purchased_day = asset_purchased_day;
        }

        public void setAsset_purchased_month(String asset_purchased_month) {
            my_asset_purchased_month = asset_purchased_month;
        }
        public void setAsset_purchased_year(String asset_purchased_year) {
            my_asset_purchased_year = asset_purchased_year;
        }

        public void setAsset_quantity(String asset_quantity) {
            my_asset_quantity = asset_quantity;
        }

        public void setAsset_type(String asset_type) {
            my_asset_type = asset_type;
        }
    }
}
