package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.OrganizationalLearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class OrganizationLearningActivity extends AppCompatActivity {

    ImageView btnItem1,btnItem2,btnItem3,btnItem4,btnItem5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_learning);

        btnItem1 = findViewById(R.id.btnItem1);
        btnItem2 = findViewById(R.id.btnItem2);
        btnItem3 = findViewById(R.id.btnItem3);
        btnItem4 = findViewById(R.id.btnItem4);
        btnItem5 = findViewById(R.id.btnItem5);

        btnItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Cultura de Aprendizaje";
                String objective = "Mi empresa deberá orientarse a la cultura del aprendizaje para:";
                String activities = "Facilitar los procesos de desarrollo de conocimiento\nDesarrollo de conocimientos y habilidades, destrezas y vivencias de todos los colaboradores\nHabilidaes blandas: stán asociadas al comportamiento de la persona, su desempeño social, liderazgo y manejo emocional\nHabilidades duras: son todas aquellas competencias vinculadas directamente con las tareas realizadas por el colaborado";

                showInformationDialog(title,objective,activities);
            }
        });

        btnItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Ambiente para el Aprendizaje";
                String objective = "Mi empresa debe tener como finalidad desarrollar lo siguiente:";
                String activities = "Confianza: creer firmemente en las capcidades y en la experiencia del colaborador\nEmpoderamiento: delegando poder y autoridad a los colaboardores a través de ascensos\nInformación compartida\nValoración de los talentos de tus colaboradores";

                showInformationDialog(title,objective,activities);
            }
        });

        btnItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Comunicación Abierta y Compartida";
                String objective = "Lograr un alto grado de certeza en las decisiones";
                String activities = "Compartir información pertinente entre los niveles jerárquicos y áreas de trabajo\nEnrequecimiento de datos para la anulacion de dudas";

                showInformationDialog(title,objective,activities);
            }
        });

        btnItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Integración escalar entre sus miembros";
                String objective = "Favorecerá el crecimiento productivo y eficiente de actividades que multiplicarán tus resultados.";
                String activities = "Trabajo compartido entre cada colaborador, equipo, área y organización en conjunto\nCompartir experiencias\nTransferir conocimientos a fin de desarrollar competencias\nPotenciar talentos";

                showInformationDialog(title,objective,activities);
            }
        });

        btnItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Razones de crecimiento";
                String objective = "La necesidad de una organización estará orientada a cubrir objetivos: financieros, comerciales, productivos, de mercado, etc., para lograrlos en un entorno impredecible, los factores ambientales (tanto internos como externos) se convierten en la razón de crecimiento.";
                String activities = "Capacidad de sobrevivencia: capacidad de aprendizaje para saber resolver problemas y enfrentar conflictos que impidan el desarrollo de los individuos, grupos y empresa en general.\nAportaciones de los colaboradores de la empresa\nCrear, asimilar, difundir y utilizar los conocimientos antiguos y nuevos, dentro de la dinámica procedimental y operativa de las jornadas de trabajo.";

                showInformationDialog(title,objective,activities);
            }
        });

    }

    private void showInformationDialog(String title, String objective,String activities) {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.organizational_learning_information_dialog,null);

        TextView txtTitle,txtObjective,txtActivities;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtObjective = finance_method.findViewById(R.id.txtObjective);
        txtActivities = finance_method.findViewById(R.id.txtActivities);

        txtTitle.setText(title);
        txtObjective.setText(objective);
        txtActivities.setText(activities);

        dialog.setView(finance_method);
        dialog.show();

    }
}
