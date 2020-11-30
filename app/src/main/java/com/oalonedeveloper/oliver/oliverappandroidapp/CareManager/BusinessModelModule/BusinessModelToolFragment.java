package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessModelModule;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class BusinessModelToolFragment extends Fragment {

    ImageView btnAddKeyPartners,btnAddKeyActivities,btnKeyResources,btnAddValueProposal,btnCustomerRelationship,btnAddChannels,btnCustomerSegment,btnAddOutcomeFlow,btnAddIncomeFlow;
    LinearLayout btnAddPaymentMethod;
    String post_key;
    DatabaseReference companyRef;
    int value1,value2,value3,value4,id001A,id001B,id001C,id002A,id002B,id002C,id002D,id003A,id003B,id003C,id003D,id004A,id004B,id004C,id004D,id005A,id005B,id005C,id005D,id006A,id006B,id006C,id006D,id007A,id007B,id007C,id007D,
            id008A,id008B,id008C,id008D,id009A,id009B,id009C,id009D,id010A,id010B,id010C,id010D;
    TextView txtKeyPartners,txtKeyActivities,txtKeyResources,txtValueProposal,txtCustomerRelationship,txtChannels,txtCustomerSegment,txtOutcomeFlow,txtIncomeFlow,txtPaymentMethod;
    AnyChartView anyChartView,anyChartView2,anyChartView3,anyChartView4,anyChartView5,anyChartView6,anyChartView7,anyChartView8,anyChartView9,anyChartView10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_model_tool, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnAddKeyPartners = view.findViewById(R.id.btnAddKeyPartners);
        btnAddKeyActivities = view.findViewById(R.id.btnAddKeyActivities);
        btnKeyResources = view.findViewById(R.id.btnKeyResources);
        btnAddValueProposal = view.findViewById(R.id.btnAddValueProposal);
        btnCustomerRelationship = view.findViewById(R.id.btnCustomerRelationship);
        btnAddChannels = view.findViewById(R.id.btnAddChannels);
        btnCustomerSegment = view.findViewById(R.id.btnCustomerSegment);
        btnAddOutcomeFlow = view.findViewById(R.id.btnAddOutcomeFlow);
        btnAddIncomeFlow = view.findViewById(R.id.btnAddIncomeFlow);
        btnAddPaymentMethod = view.findViewById(R.id.btnAddPaymentMethod);

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        txtKeyPartners = view.findViewById(R.id.txtKeyPartners);
        txtKeyActivities = view.findViewById(R.id.txtKeyActivities);
        txtKeyResources = view.findViewById(R.id.txtKeyResources);
        txtValueProposal = view.findViewById(R.id.txtValueProposal);
        txtCustomerRelationship = view.findViewById(R.id.txtCustomerRelationship);
        txtChannels = view.findViewById(R.id.txtChannels);
        txtCustomerSegment = view.findViewById(R.id.txtCustomerSegment);
        txtOutcomeFlow = view.findViewById(R.id.txtOutcomeFlow);
        txtIncomeFlow = view.findViewById(R.id.txtIncomeFlow);
        txtPaymentMethod = view.findViewById(R.id.txtPaymentMethod);

        anyChartView = view.findViewById(R.id.anyChartView);
        anyChartView2 = view.findViewById(R.id.anyChartView2);
        anyChartView3 = view.findViewById(R.id.anyChartView3);
        anyChartView4 = view.findViewById(R.id.anyChartView4);
        anyChartView5 = view.findViewById(R.id.anyChartView5);
        anyChartView6 = view.findViewById(R.id.anyChartView6);
        anyChartView7 = view.findViewById(R.id.anyChartView7);
        anyChartView8 = view.findViewById(R.id.anyChartView8);
        anyChartView9 = view.findViewById(R.id.anyChartView9);
        anyChartView10 = view.findViewById(R.id.anyChartView10);


        companyRef.child(post_key).child("Module 4").child("Lean Canvas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    if (dataSnapshot.hasChild("001")) {
                        if (dataSnapshot.child("001").hasChild("001A")) {
                             id001A = dataSnapshot.child("001").child("001A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("001").hasChild("001B")) {
                             id001B = dataSnapshot.child("001").child("001B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("001").hasChild("001C")) {
                             id001C = dataSnapshot.child("001").child("001C").getValue(Integer.class);
                        }



                        txtKeyPartners.setText("Mis socios clave están compuestos de la siguiente forma: "+id001A+"% por Empresas Privadas, "+id001B+"% por entidades estatales y "+id001C+"% por ONG's y Fundaciones");
                    }

                    APIlib.getInstance().setActiveAnyChartView(anyChartView);

                    Pie pie = AnyChart.pie();
                    List<DataEntry> data = new ArrayList<>();
                    data.add(new ValueDataEntry("Empresas Privadas", id001A));
                    data.add(new ValueDataEntry("Entidades Estatales", id001B));
                    data.add(new ValueDataEntry("ONG's y Fundaciones", id001C));
                    data.add(new ValueDataEntry("Otross", 23));
                    pie.data(data);
                    anyChartView.setChart(pie);

                    if (dataSnapshot.hasChild("002")) {
                        if (dataSnapshot.child("002").hasChild("002A")) {
                            id002A = dataSnapshot.child("002").child("002A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("002").hasChild("002B")) {
                            id002B = dataSnapshot.child("002").child("002B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("002").hasChild("002C")) {
                            id002C = dataSnapshot.child("002").child("002C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("002").hasChild("002D")) {
                            id002D = dataSnapshot.child("002").child("002D").getValue(Integer.class);
                        }




                        APIlib.getInstance().setActiveAnyChartView(anyChartView2);

                        Pie pie2 = AnyChart.pie();
                        List<DataEntry> data2 = new ArrayList<>();
                        data2.add(new ValueDataEntry("Atención rápida y eficiente", id002A));
                        data2.add(new ValueDataEntry("Personal capacitado", id002B));
                        data2.add(new ValueDataEntry("Ofertas y Promocione", id002C));
                        data2.add(new ValueDataEntry("Ubicación del Negocio", id002D));
                        pie2.data(data2);
                        anyChartView2.setChart(pie2);



                        txtKeyActivities.setText("Mis actividades clave están compuestos de la siguiente forma: "+id002A+"% por Atención rápida y eficiente, "+id002B+"% por Personal capacitado, "+id002C+"% por las Ofertas y Promociones, y "+id002D+"% por la Ubicación del Negocio.");
                    }

                    if (dataSnapshot.hasChild("003")) {
                        if (dataSnapshot.child("003").hasChild("003A")) {
                            id003A = dataSnapshot.child("003").child("003A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("003").hasChild("003B")) {
                            id003B = dataSnapshot.child("003").child("003B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("003").hasChild("003C")) {
                            id003C = dataSnapshot.child("003").child("003C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("003").hasChild("003D")) {
                            id003D = dataSnapshot.child("003").child("003D").getValue(Integer.class);
                        }

                        APIlib.getInstance().setActiveAnyChartView(anyChartView3);

                        Pie pie3 = AnyChart.pie();
                        List<DataEntry> data3 = new ArrayList<>();
                        data3.add(new ValueDataEntry("Vendedores", id003A));
                        data3.add(new ValueDataEntry("Capital de Trabajo", id003B));
                        data3.add(new ValueDataEntry("Condiciones del local", id003C));
                        data3.add(new ValueDataEntry("Permisos y Licencias", id003D));
                        pie3.data(data3);
                        anyChartView3.setChart(pie3);

                        txtKeyResources.setText("Mis recursos clave están compuestos de la siguiente forma: "+id003A+"% por Vendedores, "+id003B+"% por Capital de Trabajo, "+id003C+"% por las Condiciones del local/puesto del negocio, y "+id003D+"% por los Permisos y Licencias.");
                    }

                    if (dataSnapshot.hasChild("004")) {
                        if (dataSnapshot.child("004").hasChild("004A")) {
                            id004A = dataSnapshot.child("004").child("004A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("004").hasChild("004B")) {
                            id004B = dataSnapshot.child("004").child("004B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("004").hasChild("004C")) {
                            id004C = dataSnapshot.child("004").child("004C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("004").hasChild("004D")) {
                            id004D = dataSnapshot.child("004").child("004D").getValue(Integer.class);
                        }

                        APIlib.getInstance().setActiveAnyChartView(anyChartView4);

                        Pie pie4 = AnyChart.pie();
                        List<DataEntry> data4 = new ArrayList<>();
                        data4.add(new ValueDataEntry("Garantía", id004A));
                        data4.add(new ValueDataEntry("Puestos de trabajos limpios", id004B));
                        data4.add(new ValueDataEntry("Producto novedoso y 100% peruano", id004C));
                        data4.add(new ValueDataEntry("Entrega rápida", id004D));
                        pie4.data(data4);
                        anyChartView4.setChart(pie4);

                        txtValueProposal.setText("Mis propuesta de valor está compuesta de la siguiente forma: "+id004A+"% por Garantía del producto ó servicio, "+id004B+"% por Puestos de trabajos limpios, "+id004C+"% por un Producto novedoso y 100% peruano, y "+id004D+"% por Entrega rápida.");
                    }

                    if (dataSnapshot.hasChild("005")) {
                        if (dataSnapshot.child("005").hasChild("005A")) {
                            id005A = dataSnapshot.child("005").child("005A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("005").hasChild("005B")) {
                            id005B = dataSnapshot.child("005").child("005B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("005").hasChild("005C")) {
                            id005C = dataSnapshot.child("005").child("005C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("005").hasChild("005D")) {
                            id005D = dataSnapshot.child("005").child("005D").getValue(Integer.class);
                        }
                        APIlib.getInstance().setActiveAnyChartView(anyChartView5);

                        Pie pie5 = AnyChart.pie();
                        List<DataEntry> data5 = new ArrayList<>();
                        data5.add(new ValueDataEntry("Trato amable y de calidad", id005A));
                        data5.add(new ValueDataEntry("Información detallada de tu producto", id005B));
                        data5.add(new ValueDataEntry("Descuentos", id005C));
                        data5.add(new ValueDataEntry("Entrega de complementos o adicionales", id005D));
                        pie5.data(data5);
                        anyChartView5.setChart(pie5);

                        txtCustomerRelationship.setText("Mis relación con los clientes está compuesta de la siguiente forma: "+id005A+"% por Trato amable y de calidad, "+id005B+"% por Información detallada de tu producto/servicio, "+id005C+"% por Descuentos del producto/servicio, y "+id005D+"% por Entrega de complementos o adicionales.");
                    }

                    if (dataSnapshot.hasChild("006")) {
                        if (dataSnapshot.child("006").hasChild("006A")) {
                            id006A = dataSnapshot.child("006").child("006A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("006").hasChild("006B")) {
                            id006B = dataSnapshot.child("006").child("006B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("006").hasChild("006C")) {
                            id006C = dataSnapshot.child("006").child("006C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("006").hasChild("006D")) {
                            id006D = dataSnapshot.child("006").child("006D").getValue(Integer.class);
                        }

                        APIlib.getInstance().setActiveAnyChartView(anyChartView6);

                        Pie pie6 = AnyChart.pie();
                        List<DataEntry> data6 = new ArrayList<>();
                        data6.add(new ValueDataEntry("Redes sociales", id006A));
                        data6.add(new ValueDataEntry("Página web propia", id006B));
                        data6.add(new ValueDataEntry("Distribuidores", id006C));
                        data6.add(new ValueDataEntry("Puesto del Negocio", id006D));
                        pie6.data(data6);
                        anyChartView6.setChart(pie6);


                        txtChannels.setText("Mis canales de comunicación con los clientes está compuesta de la siguiente forma: "+id006A+"% por Redes sociales, "+id006B+"% por Página web propia, "+id006C+"% por Distribuidores, y "+id006D+"% por Local o Puesto del Negocio.");
                    }

                    if (dataSnapshot.hasChild("007")) {
                        if (dataSnapshot.child("007").hasChild("007A")) {
                            id007A = dataSnapshot.child("007").child("007A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("007").hasChild("007B")) {
                            id007B = dataSnapshot.child("007").child("007B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("007").hasChild("007C")) {
                            id007C = dataSnapshot.child("007").child("007C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("007").hasChild("007D")) {
                            id007D = dataSnapshot.child("007").child("007D").getValue(Integer.class);
                        }

                        APIlib.getInstance().setActiveAnyChartView(anyChartView7);

                        Pie pie7 = AnyChart.pie();
                        List<DataEntry> data7 = new ArrayList<>();
                        data7.add(new ValueDataEntry("Trabajadores", id007A));
                        data7.add(new ValueDataEntry("Funcionarios", id007B));
                        data7.add(new ValueDataEntry("Comerciantes", id007C));
                        data7.add(new ValueDataEntry("Extranjeros", id007D));
                        pie7.data(data7);
                        anyChartView7.setChart(pie7);

                        txtCustomerSegment.setText("Mi segmento de clientes está compuesto de la siguiente forma: "+id007A+"% por  Trabajadores, "+id007B+"% por Funcionarios, "+id007C+"% por Comerciantes, y "+id007D+"% por Extranjeros.");
                    }

                    if (dataSnapshot.hasChild("008")) {
                        if (dataSnapshot.child("008").hasChild("008A")) {
                            id008A = dataSnapshot.child("008").child("008A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("008").hasChild("008B")) {
                            id008B = dataSnapshot.child("008").child("008B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("008").hasChild("008C")) {
                            id008C = dataSnapshot.child("008").child("008C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("008").hasChild("008D")) {
                            id008D = dataSnapshot.child("008").child("008D").getValue(Integer.class);
                        }

                        APIlib.getInstance().setActiveAnyChartView(anyChartView8);

                        Pie pie8 = AnyChart.pie();
                        List<DataEntry> data8 = new ArrayList<>();
                        data8.add(new ValueDataEntry("Servicios Públicos", id008A));
                        data8.add(new ValueDataEntry("Sueldos y Salarios", id008B));
                        data8.add(new ValueDataEntry("Promoción y Publicidad", id008C));
                        data8.add(new ValueDataEntry("Máquinas y Equipos", id008D));
                        pie8.data(data8);
                        anyChartView8.setChart(pie8);

                        txtOutcomeFlow.setText("Mi flujo de egresos está compuesto de la siguiente forma: "+id008A+"% por  Servicios Públicos, "+id008B+"% por Sueldos y Salarios, "+id008C+"% por Promoción y Publicidad, y "+id008D+"% por Máquinas y Equipos.");
                    }

                    if (dataSnapshot.hasChild("009")) {
                        if (dataSnapshot.child("009").hasChild("009A")) {
                            id009A = dataSnapshot.child("009").child("009A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("009").hasChild("009B")) {
                            id009B = dataSnapshot.child("009").child("009B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("009").hasChild("009C")) {
                            id009C = dataSnapshot.child("009").child("009C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("009").hasChild("009D")) {
                            id009D = dataSnapshot.child("009").child("009D").getValue(Integer.class);
                        }

                        APIlib.getInstance().setActiveAnyChartView(anyChartView9);

                        Pie pie9 = AnyChart.pie();
                        List<DataEntry> data9 = new ArrayList<>();
                        data9.add(new ValueDataEntry("Precio bajo", id009A));
                        data9.add(new ValueDataEntry("Precio medio", id009B));
                        data9.add(new ValueDataEntry("Precio alto", id009C));
                        data9.add(new ValueDataEntry("Precio diferenciado", id009D));
                        pie9.data(data9);
                        anyChartView9.setChart(pie9);

                        txtIncomeFlow.setText("Mis clientes están dispuesto a pagar un precio según la siguiente distribución: "+id009A+"%   Precio bajo, "+id009B+"% Precio medio, "+id009C+"%  Precio alto, y "+id009D+"%  Precio diferenciado.");
                    }

                    if (dataSnapshot.hasChild("010")) {
                        if (dataSnapshot.child("010").hasChild("010A")) {
                            id010A = dataSnapshot.child("010").child("010A").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("010").hasChild("010B")) {
                            id010B = dataSnapshot.child("010").child("010B").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("010").hasChild("010C")) {
                            id010C = dataSnapshot.child("010").child("010C").getValue(Integer.class);
                        }
                        if (dataSnapshot.child("010").hasChild("010D")) {
                            id010D = dataSnapshot.child("010").child("010D").getValue(Integer.class);
                        }

                        APIlib.getInstance().setActiveAnyChartView(anyChartView10);

                        Pie pie10 = AnyChart.pie();
                        List<DataEntry> data10 = new ArrayList<>();
                        data10.add(new ValueDataEntry("Efectivo", id010A));
                        data10.add(new ValueDataEntry("Tarjetas de Débito y/o Crédito", id010B));
                        data10.add(new ValueDataEntry("Transferencias", id010C));
                        data10.add(new ValueDataEntry("Al Crédito", id010D));
                        pie10.data(data10);
                        anyChartView10.setChart(pie10);

                        APIlib.getInstance().setActiveAnyChartView(anyChartView);

                        txtPaymentMethod.setText("Mis clientes pagan con estos medios de pago según la siguiente distribución: "+id010A+"% Efectivo, "+id010B+"% Tarjetas de Débito y/o Crédito, "+id010C+"% Transferencias, y "+id010D+"% Al Crédito.");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnAddKeyPartners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddKeyPartnersDialog();
            }
        });

        btnAddKeyActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddKeyActivitiesDialog();
            }
        });
        btnKeyResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddKeyResourcesDialog();
            }
        });
        btnAddValueProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddValueProposalDialog();
            }
        });
        btnCustomerRelationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCustomerRelationshipDialog();
            }
        });
        btnAddChannels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddChannelsDialog();
            }
        });
        btnCustomerSegment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomerSegmentDialog();
            }
        });
        btnAddOutcomeFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOutcomeFlowdDialog();
            }
        });
        btnAddIncomeFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIncomeFlowDialog();
            }
        });
        btnAddPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaymentMethodDialog();
            }
        });


        return view;
    }

    private void showPaymentMethodDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_payment_method_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("010").child("010A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("010").child("010B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("010").child("010C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("010").child("010D").setValue(value4);


                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }

            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showIncomeFlowDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_income_flow_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("009").child("009A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("009").child("009B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("009").child("009C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("009").child("009D").setValue(value4);


                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }

            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showOutcomeFlowdDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_outcome_flow_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("008").child("008A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("008").child("008B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("008").child("008C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("008").child("008D").setValue(value4);


                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }

            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showCustomerSegmentDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_customer_segments_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("007").child("007A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("007").child("007B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("007").child("007C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("007").child("007D").setValue(value4);


                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }

            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showAddChannelsDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_channels_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("006").child("006A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("006").child("006B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("006").child("006C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("006").child("006D").setValue(value4);


                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }

            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showAddCustomerRelationshipDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_customer_relationship_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("005").child("005A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("005").child("005B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("005").child("005C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("005").child("005D").setValue(value4);


                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }

            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showAddValueProposalDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_customer_relationship_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("004").child("004A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("004").child("004B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("004").child("004C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("004").child("004D").setValue(value4);


                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }

            }
        });


        dialog.setView(finance_method);
        dialog.show();

    }

    private void showAddKeyResourcesDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_key_resources_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("003").child("003A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("003").child("003B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("003").child("003C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("003").child("003D").setValue(value4);


                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }

            }
        });


        dialog.setView(finance_method);
        dialog.show();

    }

    private void showAddKeyActivitiesDialog() {

        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_key_activities_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E,rd004A,rd004B,rd004C,rd004D,rd004E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);
        rd004A = finance_method.findViewById(R.id.rd004A);
        rd004B = finance_method.findViewById(R.id.rd004B);
        rd004C = finance_method.findViewById(R.id.rd004C);
        rd004D = finance_method.findViewById(R.id.rd004D);
        rd004E = finance_method.findViewById(R.id.rd004E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        rd004A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 0;
            }
        });
        rd004B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 25;
            }
        });
        rd004C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 50;
            }
        });
        rd004D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 75;
            }
        });
        rd004E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value4 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3+value4;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("002").child("002A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("002").child("002B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("002").child("002C").setValue(value3);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("002").child("002D").setValue(value4);



                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }



            }
        });


        dialog.setView(finance_method);
        dialog.show();

    }

    private void showAddKeyPartnersDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_key_partners_dialog,null);

        final RadioGroup radioGroup1,radioGroup2,radioGroup3;
        final Button btnFinish;
        final RelativeLayout rootLayout;
        RadioButton rd001A,rd001B,rd001C,rd001D,rd001E,rd002A,rd002B,rd002C,rd002D,rd002E,rd003A,rd003B,rd003C,rd003D,rd003E;

        rd001A = finance_method.findViewById(R.id.rd001A);
        rd001B = finance_method.findViewById(R.id.rd001B);
        rd001C = finance_method.findViewById(R.id.rd001C);
        rd001D = finance_method.findViewById(R.id.rd001D);
        rd001E = finance_method.findViewById(R.id.rd001E);
        rd002A = finance_method.findViewById(R.id.rd002A);
        rd002B = finance_method.findViewById(R.id.rd002B);
        rd002C = finance_method.findViewById(R.id.rd002C);
        rd002D = finance_method.findViewById(R.id.rd002D);
        rd002E = finance_method.findViewById(R.id.rd002E);
        rd003A = finance_method.findViewById(R.id.rd003A);
        rd003B = finance_method.findViewById(R.id.rd003B);
        rd003C = finance_method.findViewById(R.id.rd003C);
        rd003D = finance_method.findViewById(R.id.rd003D);
        rd003E = finance_method.findViewById(R.id.rd003E);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rd001A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 0;
            }
        });
        rd001B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 25;
            }
        });
        rd001C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 50;
            }
        });
        rd001D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 75;
            }
        });
        rd001E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = 100;
            }
        });

        rd002A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 0;
            }
        });
        rd002B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 25;
            }
        });
        rd002C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 50;
            }
        });
        rd002D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 75;
            }
        });
        rd002E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2 = 100;
            }
        });

        rd003A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 0;
            }
        });
        rd003B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 25;
            }
        });
        rd003C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 50;
            }
        });
        rd003D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 75;
            }
        });
        rd003E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value3 = 100;
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = value1+value2+value3;

                if (sum != 100) {
                    Snackbar.make(rootLayout, "Los valores seleccionados deben sumar 100%", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    btnFinish.setEnabled(false);
                    btnFinish.setText("Cargando...");

                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("001").child("001A").setValue(value1);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("001").child("001B").setValue(value2);
                    companyRef.child(post_key).child("Module 4").child("Lean Canvas").child("001").child("001C").setValue(value3);



                    dialog.dismiss();

                    Toasty.success(getActivity(), "Datos Cargados con éxito", Toast.LENGTH_LONG).show();
                }



            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }
}
