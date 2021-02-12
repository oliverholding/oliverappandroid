package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.RiskManagement;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashFlow.CashFlowActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class RegisterRiskFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key;
    Button btnRegister;
    ArrayList<String> area =new ArrayList<>();
    SpinnerDialog spinnerArea;
    ArrayList<String> riskList =new ArrayList<>();
    SpinnerDialog spinnerRisk;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_risk, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnRegister = view.findViewById(R.id.btnRegister);
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterRiskDialog();
            }
        });

        showRisks();


        return view;
    }

    private void showRisks() {
        Query query = companyRef.child(post_key).child("Risks").orderByChild("timestamp");
        FirebaseRecyclerAdapter<RisksModel, RiskViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RisksModel, RiskViewHolder>
                (RisksModel.class, R.layout.risk_item,RiskViewHolder.class,query) {
            @Override
            protected void populateViewHolder(RiskViewHolder viewHolder, RisksModel model, int position) {
                viewHolder.setRisk_area(model.getRisk_area());
                viewHolder.setRisk_name(model.getRisk_name());
                viewHolder.setRisk_cause(model.getRisk_cause());
                viewHolder.setRisk_impact(model.getRisk_impact());
                viewHolder.setRisk_probability(model.getRisk_probability());
                viewHolder.setRisk_solution(model.getRisk_solution());

                viewHolder.txtRiskName.setText(viewHolder.risk_name_st);
                viewHolder.txtRiskArea.setText("Área: "+viewHolder.risk_area_st);
                viewHolder.txtRiskCause.setText("Causa: "+viewHolder.risk_cause_st);
                viewHolder.txtRiskImpact.setText("Impacto: "+viewHolder.risk_impact_st);
                viewHolder.txtRisProbability.setText("Probabilidad de Ocurrencia: "+viewHolder.risk_probability_st);
                viewHolder.txtSolution.setText("Solución: "+viewHolder.risk_solution_st);

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showRegisterRiskDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.register_risk_dialog,null);

        final Button btnArea,btnRisk,btnRegisterRisk;
        final RelativeLayout rootLayout;

        btnArea = finance_method.findViewById(R.id.btnArea);
        btnRisk = finance_method.findViewById(R.id.btnRisk);
        btnRegisterRisk = finance_method.findViewById(R.id.btnRegisterRisk);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        area.add("Gerencia General");area.add("Gestión de Ventas");area.add("Gestión de Cadena De Suministro");area.add("Gestión de Contabilidad");area.add("Gestión de Distribución");area.add("Gestión de Finanzas");area.add("Gestión de Producción");
        area.add("Gestión de Recursos Humanos");area.add("Gestión de TI");

        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerArea.showSpinerDialog();
            }
        });

        spinnerArea = new SpinnerDialog(getActivity(),area, "Selecciona el Área");
        spinnerArea.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                if (s.equals("Gerencia General")) {
                    riskList.add("Plan de continuidad de negocio");
                }
                if (s.equals("Gestión de Ventas")) {
                    riskList.add("Planeación de ventas anual no alineada/ajustada a los resultados de la compañía, industria y país.");riskList.add("Que se determine una cuota (por canal) de venta no acorde a la demanda del mercado");riskList.add("Que se pierdan oportunidades de venta a clientes potenciales");riskList.add("Creación, modificación y ajustes del maestro de precios de venta, descuentos y promociones no autorizadas por el nivel requerido o  por colaboradores con accesos no autorizados y sin alineación con la estrategia de la Compañía.");riskList.add("Error en la toma de pedido de clientes antiguos y clientes nuevos, por parte de los vendedores.");riskList.add("Reclamaciones de clientes no registradas y gestionadas");riskList.add("Devoluciones y reclamaciones sin trazabilidad y sin conceptos no identificados que permitan su registro y análisis.");
                }
                if (s.equals("Gestión de Cadena De Suministro")) {
                    riskList.add("Poca fiabilidad del proveedor");riskList.add("Interrupciones del suministro");riskList.add("Incertidumbre en la demanda ");riskList.add("No cumplimiento de normas tecnicas");riskList.add("Huelgas o conflictos laborales");riskList.add("Escaza politica de colaboración con proveedores");riskList.add("Manipulación y transporte de productos peligroso");riskList.add("Cobertura de seguro de transporte");riskList.add("Condiciones climatologicas adversas / desastres naturales");riskList.add("Fluctuación de los precios");
                }
                if (s.equals("Gestión de Contabilidad")) {
                    riskList.add("Realización de movimientos sin documentación correpondiente");riskList.add("Error en cálculos tributarios");riskList.add("Cuadre de inventarios correctos");riskList.add("Acceso al proceso de facturación sin restricciones de usuario");
                }
                if (s.equals("Gestión de Distribución")) {
                    riskList.add("Incumplimiento de despacho por falta de disponibilidad de vehículos.");riskList.add("Accesos a realizar movimientos, ajustes y bajas de inventario a funcionarios no están autorizados.");riskList.add("Incumplimientos legales de transporte y/o permisos para transitar");riskList.add("Devoluciones físicas no verificadas y/o no registradas en el sistema de información.");riskList.add("Inadecuada, no identificación y/o falta de trazabilidad de las causales de devolución.");riskList.add("Que no se atiendan pedidos oportunamente");riskList.add("Que se contaminen los productos durante el traslado al destino");riskList.add("Que se reciba una denuncia del consumidor u organismos gubernamentales");riskList.add("Que no se pueda realizar la descarga de mercadería por no tener espacio en el almacén");
                }
                if (s.equals("Gestión de Finanzas")) {
                    riskList.add("Otorgar créditos riesgosos.");riskList.add("Tasa de interes");riskList.add("Tipo de cambio");riskList.add("Volatilidad en los precios");riskList.add("Endeudamiento");riskList.add("Fraude en el proceso de pagos en bancos");riskList.add("Fraude en el proceso de visado documentario");riskList.add("De quedarse sin acceso a lineas bancarias");
                }
                if (s.equals("Gestión de Producción")) {
                    riskList.add("Pérdidas en el proceso productivo");riskList.add("Fallas en el proceso de cálidad");riskList.add("Cumplimiento de demanda");riskList.add("Alta rotación de personal operario");riskList.add("Accidentes personales");
                }
                if (s.equals("Gestión de Recursos Humanos")) {
                    riskList.add("Contrataciones sin el perfil técnico, habilidades blandas y experiencia requerida.");riskList.add("Contrataciones de colaboradores con antecedentes legales y procesos por conductas disciplinarias.");riskList.add("Contingencia laboral por la no formalización contractual de forma oportuna y/o documentos contractuales sin las cláusulas requeridas o sin cumplimientos legales.");riskList.add("Errores en la liquidación de nómina y/o cese, y sin autorización de los niveles requeridos e información insuficiente (cuenta por cobrar pendientes y/o sin paz y salvo y sin autorización).");riskList.add("Transacciones o actividades realizadas con usuarios de colaboradores retirados que no fueron bloqueados en el sistema de información");riskList.add("Que no se entregue la liquidación de beneficios sociales en los plazos establecidos por el ente regulatorio (48 horas)");riskList.add("Accesos no autorizados para crear, modificar, visualizar y eliminar: (i) parmámetros de nómina (novedades: nómina variable, incremento salarial, vacaciones, descuentos, ausencia, licencia, falta y subsidios,cambios en el maestro y nuevos ingresos), (ii) condiciones contractuales, (iii) cuentas bancarias, (iv) certificados laborales, etc.");riskList.add("Modificación de información de los archivos de nómina por colaboradores no autorizados y/o inadecuada segregación de funciones.");riskList.add("Errores en la liquidación de nómina y/o cese, y sin autorización de los niveles requeridos e información insuficiente (cuenta por cobrar pendientes y/o sin paz y salvo y sin autorización).");riskList.add("Que no se recuperen las herramientas que fueron asignadas al personal");riskList.add("Que no se realice o no se realice de manera oportuna la baja del personal cesado en los sistemas de marcación y \"sistema de recursos humanos\", posterior de la entrega de liquidación de beneficios sociales.");riskList.add("Que no se conozca los motivos de renuncia del personal, impidiendo la toma de acciones correctivas y/o preventivas correspondientes");
                }
                if (s.equals("Gestión de TI")) {
                    riskList.add("Caída de servicios de producción");riskList.add("Caída de red");riskList.add("Extracción, modificación y destrucción de información confidencial");riskList.add("Fuga de información");riskList.add("Ataques de virus informáticos");riskList.add("Fallas en el servidor");riskList.add("Incendio del servidor");riskList.add("Fallas en la comunicación ");
                }
                btnArea.setText(s);
            }
        });

        btnRisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnArea.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar el área", Snackbar.LENGTH_SHORT).show();
                } else {


                    spinnerRisk = new SpinnerDialog(getActivity(),riskList, "Selecciona el Riesgo");

                    spinnerRisk.showSpinerDialog();


                    spinnerRisk.bindOnSpinerListener(new OnSpinerItemClick() {
                        @Override
                        public void onClick(String s, int i) {
                            btnRisk.setText(s);
                        }
                    });
                }
            }
        });


        btnRegisterRisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnArea.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar el área",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnRisk.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar el riesgo", Snackbar.LENGTH_SHORT).show();
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();

                    if (btnRisk.getText().toString().equals("Plan de continuidad de negocio")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No contar con planes en caso de incendios o terremotos");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Contar con seguro de lucro cesante");
                    }
                    if (btnRisk.getText().toString().equals("Planeación de ventas anual no alineada/ajustada a los resultados de la compañía, industria y país.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Que la estimación del cierre de ventas no se encuentre alineada a la demanda del mercado");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("No existe información de demanda potencial");
                    }
                    if (btnRisk.getText().toString().equals("Que se determine una cuota (por canal) de venta no acorde a la demanda del mercado")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Asignación de cuotas de venta no acordes a la zonificación del vendedor.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Elaborar plan de cuotas por vendedor alineado con el budget");
                    }
                    if (btnRisk.getText().toString().equals("Que se pierdan oportunidades de venta a clientes potenciales")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("- No se consideran todos los criterios necesarios en la evaluación del cliente que permite determinar si el cliente es rentable para la Compañía.\n" +
                                "- No recopilar la información necesaria para la identificación de las necesidades del cliente.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Elaborar cuadro de rentabilidades por cliente");
                    }
                    if (btnRisk.getText().toString().equals("Creación, modificación y ajustes del maestro de precios de venta, descuentos y promociones no autorizadas por el nivel requerido o  por colaboradores con accesos no autorizados y sin alineación con la estrategia de la Compañía.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No se cuentan con procedimientos formales para la realización, actualización, modificación de información sensible.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Se debe de tener los perfiles actualizados en el SISTEMA");
                    }
                    if (btnRisk.getText().toString().equals("Error en la toma de pedido de clientes antiguos y clientes nuevos, por parte de los vendedores.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de un procedimiento para la generación del pedido de venta, en donde se establezcan roles y responsables claramente definidas. No se cuenta con un sistema de información estandar que ayude a la canalización de los pedidos. ");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Implementar al momento de la materia prima una revlidación de información ");
                    }
                    if (btnRisk.getText().toString().equals("Reclamaciones de clientes no registradas y gestionadas")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No se tiene un registro de reclamo de cliente");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Se deben de contar con los procesos y autorizacionez en  SISTEMA");
                    }
                    if (btnRisk.getText().toString().equals("Devoluciones y reclamaciones sin trazabilidad y sin conceptos no identificados que permitan su registro y análisis.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No se tiene un registro de devolcuiones de cliente");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Se debe de implementar un indicador por motivos de devolucion y crear un plan de acción");
                    }
                    if (btnRisk.getText().toString().equals("Poca fiabilidad del proveedor")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Proceso de homologación de proveedores");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Efectuar un proceso de homologación de proveedores");
                    }
                    if (btnRisk.getText().toString().equals("Interrupciones del suministro")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No plan alternativo de contigencia");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Elaborar un plan de contingencia consifderando incremento de inventarios de seguridad de productos claves");
                    }
                    if (btnRisk.getText().toString().equals("Incertidumbre en la demanda")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No existe plan de demanda certero");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Medir la certeza del plan de demanda mensual e ir cerrando la brecha");
                    }
                    if (btnRisk.getText().toString().equals("No cumplimiento de normas tecnicas")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Falta de capacitación al comprador y condiciones climaticas adversas en el transporte");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Incluir un plan de mediciones en el punto de acopio que permita tener control de la compra hasta la entrega a planta");
                    }
                    if (btnRisk.getText().toString().equals("Huelgas o conflictos laborales")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No existe un plan que prevenga el plan de huelgas");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Elaborar un plan de manejo de crisis y huelgas y diagnostico de clima laboral");
                    }
                    if (btnRisk.getText().toString().equals("Escaza politica de colaboración con proveedores")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No existe un plan de trabajo formal de fidelización");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Elaborar un plan de fidelización al proveedor");
                    }
                    if (btnRisk.getText().toString().equals("Manipulación y transporte de productos peligroso")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Validación ante el minsiterio de transportes de carga peligrosa");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Efectuar un proceso de revisión ante elministerio de transportes");
                    }
                    if (btnRisk.getText().toString().equals("Cobertura de seguro de transporte")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("NO se cuenta con seguro de transporte");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Contratar empresa con seguro de transporte");
                    }
                    if (btnRisk.getText().toString().equals("Condiciones climatologicas adversas / desastres naturales")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No existe un plan que prevenga el plan de previnción ante desastres naturales");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Elaborar un plan contactando previamente a un grupo de proveedores alternativos");
                    }
                    if (btnRisk.getText().toString().equals("Fluctuación de los precios")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Inestabilidad por diversos factores, clima , epoca etc");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Tener un plan de inventarios minimos por cada estacaionalidad de producto");
                    }
                    if (btnRisk.getText().toString().equals("Realización de movimientos sin documentación correpondiente")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Perfiles");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Definir perfiles en SISTEMA");
                    }
                    if (btnRisk.getText().toString().equals("Error en cálculos tributarios")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No existe un auditor tributario");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Definir un auditor tributario");
                    }
                    if (btnRisk.getText().toString().equals("Cuadre de inventarios correctos")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No hay empresa que efectue inventario externo");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Incluir un proceso de inventario externo y valide vs la contabilidad");
                    }
                    if (btnRisk.getText().toString().equals("Acceso al proceso de facturación sin restricciones de usuario")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Perfiles por definir");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Definir en el sistema");
                    }
                    if (btnRisk.getText().toString().equals("Incumplimiento de despacho por falta de disponibilidad de vehículos.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Que no se tengan clasificados a los proveedores de transporte, ni contar con contratos para la disponibilidad de vehículos.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Procedimiento en proceso");
                    }
                    if (btnRisk.getText().toString().equals("Accesos a realizar movimientos, ajustes y bajas de inventario a funcionarios no están autorizados.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Que se otorguen, modifiquen o mantengan accesos a los sistemas de información no acorde a las funciones del personal.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("El autorizador no se encuentra dentro del flujo del SISTEMA");
                    }
                    if (btnRisk.getText().toString().equals("Incumplimientos legales de transporte y/o permisos para transitar")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de revisión de documentación de los conductores y/o vehículos; así como validación con los entes reguladores.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Por realizar un proceso de barrido de información de transportistas");
                    }
                    if (btnRisk.getText().toString().equals("Devoluciones físicas no verificadas y/o no registradas en el sistema de información.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Demoras en el registro de la mercadería devuelta en el sistema de información de la compañía.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Se deben de contar con los procesos y autorizacionez en  SISTEMA");
                    }
                    if (btnRisk.getText().toString().equals("Inadecuada, no identificación y/o falta de trazabilidad de las causales de devolución.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de un procedimiento para la ejecucion del trabajo.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Se debe de contar con indocadores por motivos de devolución alineado con plan de acción");
                    }
                    if (btnRisk.getText().toString().equals("Que no se atiendan pedidos oportunamente")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Que la información del maestro de clientes esté incorrecta, incompleta o desactualizada.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Bajo");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Procedimiento escrito");
                    }
                    if (btnRisk.getText().toString().equals("Que se contaminen los productos durante el traslado al destino")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Agrupación inadecuada de productos contaminantes con demás productos.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Bajo");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Se debe de contar con protocolo de transporte previamente firmado con los trasnportistas");
                    }
                    if (btnRisk.getText().toString().equals("Que se reciba una denuncia del consumidor u organismos gubernamentales")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Incumplimiento de normas vigentes de organismos reguladores, legales, y otros aplicables a los productos comercializados por la empresa.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("No existe un plan definido");
                    }
                    if (btnRisk.getText().toString().equals("Que no se pueda realizar la descarga de mercadería por no tener espacio en el almacén")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Que las necesidades de almacenamiento calculadas no sean las correctas");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Planifiación en proceso");
                    }
                    if (btnRisk.getText().toString().equals("Otorgar créditos riesgosos.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No contar con evaluación de crédito");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Implenentar procedimiento de activación de clientes alineado con el SISTEMA");
                    }
                    if (btnRisk.getText().toString().equals("Tasa de interes")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Poco poder de negociación con instituciones financieras");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Aprovechar liquidez de Reactiva Perú con la finalidad de salur de créditos con tasas de interes largas");
                    }
                    if (btnRisk.getText().toString().equals("Tipo de cambio")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Deuda concentrada en moneda extranjera ");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Medio");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Implementar un plan de dismonución de deuda en el corto plazo ");
                    }
                    if (btnRisk.getText().toString().equals("Volatilidad en los precios")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No haber prespuestado un rango de variación e inlación en el forecast");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("Alto");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("Implementar una nueva corrida incluyendo inflación ");
                    }
                    if (btnRisk.getText().toString().equals("Endeudamiento")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Demanda alta de capital de trabajo");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Fraude en el proceso de pagos en bancos")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("El proceso de firma es solo por una persona");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Fraude en el proceso de visado documentario")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No se evidencia firma de parte las gerencias de área");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("De quedarse sin acceso a lineas bancarias")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Elevado endeudamiento");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Pérdidas en el proceso productivo")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No contar con un sistema de seguimiento por lote ");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Fallas en el proceso de cálidad")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No contar con alertas temprana de calidad");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Cumplimiento de demanda")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Abastecimiento no se encuente enlazado con el proceso de abastecimiento");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Alta rotación de personal operario")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No existir plan de retención de personal");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Accidentes personales")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No existir plan preventivo");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Contrataciones sin el perfil técnico, habilidades blandas y experiencia requerida.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de políticas y procedimientos para la selección y contratación.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Contrataciones de colaboradores con antecedentes legales y procesos por conductas disciplinarias.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de políticas y procedimientos para la selección y contratación.\n" +
                                "- Ausencia de metodología para la evaluación y calificación de competencias de los candidatos y filtros como antecedentes penales y procesos de conductas disciplinarias.\n" +
                                "- Posiciones sin partida presupuestal disponible o autorizada.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Contingencia laboral por la no formalización contractual de forma oportuna y/o documentos contractuales sin las cláusulas requeridas o sin cumplimientos legales.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de políticas y procedimientos para la selección y contratación.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Errores en la liquidación de nómina y/o cese, y sin autorización de los niveles requeridos e información insuficiente (cuenta por cobrar pendientes y/o sin paz y salvo y sin autorización).")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de un procedimiento para la liquidación de nómina y/o Término Laboral.\n" +
                                "- Ausencia de una verificación y análisis de tendencias, bases y cálculos.\n" +
                                "- Parametrización incorrecta (Salarios, bases, deducciones legales, etc.\n" +
                                "- Colaboradores no cuentan con el expertise requerido para la ejecución y/o no se encuentran capacitados.\n" +
                                "- Registro de liquidación y novedades de manera manual, sin registro contable y/o trazabilidad.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Transacciones o actividades realizadas con usuarios de colaboradores retirados que no fueron bloqueados en el sistema de información")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de políticas y procedimientos para el término de relación laboral.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Que no se entregue la liquidación de beneficios sociales en los plazos establecidos por el ente regulatorio (48 horas)")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de un procedimiento para la liquidación de nómina.\n" +
                                "- Colaboradores no cuentan con el expertise requerido para la ejecución y/o no se encuentran capacitados.\n" +
                                "- Registro de liquidación y novedades de manera manual, sin registro contable y/o trazabilidad.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Accesos no autorizados para crear, modificar, visualizar y eliminar: (i) parmámetros de nómina (novedades: nómina variable, incremento salarial, vacaciones, descuentos, ausencia, licencia, falta y subsidios,cambios en el maestro y nuevos ingresos), (ii) condiciones contractuales, (iii) cuentas bancarias, (iv) certificados laborales, etc.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de políticas y procedimientos para la alta, baja y mantenimiento en el sistema de recursos humanos.\n" +
                                "- Ausencia de roles y responsabilidades claramente establecidas.\n" +
                                "- Ausencia de una inadecuada segregación de funciones.\n" +
                                "- Ausencia de restriciones y/o parametrizaciones en el sistema de recursos humanos.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Modificación de información de los archivos de nómina por colaboradores no autorizados y/o inadecuada segregación de funciones.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Errores en la liquidación de nómina y/o cese, y sin autorización de los niveles requeridos e información insuficiente (cuenta por cobrar pendientes y/o sin paz y salvo y sin autorización).")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de un procedimiento para la liquidación de nómina y/o Término Laboral.\n" +
                                "- Ausencia de una verificación y análisis de tendencias, bases y cálculos.\n" +
                                "- Parametrización incorrecta (Salarios, bases, deducciones legales, etc.\n" +
                                "- Colaboradores no cuentan con el expertise requerido para la ejecución y/o no se encuentran capacitados.\n" +
                                "- Registro de liquidación y novedades de manera manual, sin registro contable y/o trazabilidad.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Que no se recuperen las herramientas que fueron asignadas al personal")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Errores en la liquidación de nómina y/o cese, y sin autorización de los niveles requeridos e información insuficiente (cuenta por cobrar pendientes y/o sin paz y salvo y sin autorización).");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Que no se realice o no se realice de manera oportuna la baja del personal cesado en los sistemas de marcación y \"sistema de recursos humanos\", posterior de la entrega de liquidación de beneficios sociales.")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Ausencia de un procedimiento para la liquidación de nómina.\n" +
                                "- Colaboradores no cuentan con el expertise requerido para la ejecución y/o no se encuentran capacitados.\n" +
                                "- Registro de liquidación y novedades de manera manual, sin registro contable y/o trazabilidad.");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Que no se conozca los motivos de renuncia del personal, impidiendo la toma de acciones correctivas y/o preventivas correspondientes")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Caída de servicios de producción")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Fallas diversas en la red");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Caída de red")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("Fallas diversas en la red");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Extracción, modificación y destrucción de información confidencial")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No contar con las medidas de seguridad");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Fuga de información")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No contar con perfiles predetraminados en la contratación");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Ataques de virus informáticos")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No contar con las actulizaciones periodicas");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Fallas en el servidor")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("No contar con plan de mantenimiento establecido ");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Incendio del servidor")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }
                    if (btnRisk.getText().toString().equals("Fallas en la comunicación ")) {
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_cause").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_impact").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_probability").setValue("");
                        companyRef.child(post_key).child("Risks").child(timestamp).child("risk_solution").setValue("");
                    }



                    companyRef.child(post_key).child("Risks").child(timestamp).child("risk_area").setValue(btnArea.getText().toString());
                    companyRef.child(post_key).child("Risks").child(timestamp).child("risk_name").setValue(btnRisk.getText().toString());
                    companyRef.child(post_key).child("Risks").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                    Toasty.success(getActivity(), "Riesgo Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    public static class RiskViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String risk_area_st,risk_name_st,risk_cause_st,risk_impact_st,risk_probability_st,risk_solution_st;
        TextView txtRiskName,txtRiskArea,txtRiskCause,txtRiskImpact,txtRisProbability,txtSolution;

        public RiskViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtRiskName = mView.findViewById(R.id.txtRiskName);
            txtRiskArea = mView.findViewById(R.id.txtRiskArea);
            txtRiskCause = mView.findViewById(R.id.txtRiskCause);
            txtRiskImpact = mView.findViewById(R.id.txtRiskImpact);
            txtRisProbability = mView.findViewById(R.id.txtRisProbability);
            txtSolution = mView.findViewById(R.id.txtSolution);

        }
        public void setRisk_area(String risk_area) {
            risk_area_st = risk_area;
        }

        public void setRisk_name(String risk_name) {
            risk_name_st = risk_name;
        }
        public void setRisk_cause(String risk_cause) {
            risk_cause_st = risk_cause;
        }

        public void setRisk_impact(String risk_impact) {
            risk_impact_st = risk_impact;
        }

        public void setRisk_probability(String risk_probability) {
            risk_probability_st = risk_probability;
        }

        public void setRisk_solution(String risk_solution) {
            risk_solution_st = risk_solution;
        }
    }
}
