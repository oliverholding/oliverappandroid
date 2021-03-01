package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Values;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrderProductDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import es.dmoral.toasty.Toasty;

public class OrganizationalValuesActivity extends AppCompatActivity {

    LinearLayout step1value1,step1value2,step1value3,step1value4,step1value5,step1value6,step1value7,step1value8,step1value9,step1value10,step1value11,step1value12,step1value13,step1value14,step1value15,step1value16,step1value17;
    RadioButton rdIdentified1,rdIdentified2,rdIdentified3,rdIdentified4,rdIdentified5,rdIdentified6,rdIdentified7,rdIdentified8,rdIdentified9,rdIdentified10,rdIdentified11,rdIdentified12,rdIdentified13,rdIdentified14,rdIdentified15,rdIdentified16,rdIdentified17;
    RadioButton rdNonIdentified1,rdNonIdentified2,rdNonIdentified3,rdNonIdentified4,rdNonIdentified5,rdNonIdentified6,rdNonIdentified7,rdNonIdentified8,rdNonIdentified9,rdNonIdentified10,rdNonIdentified11,rdNonIdentified12,rdNonIdentified13,rdNonIdentified14,rdNonIdentified15,rdNonIdentified16,rdNonIdentified17;
    LinearLayout step2value1,step2value2,step2value3,step2value4,step2value5,step2value6,step2value7,step2value8,step2value9,step2value10,step2value11,step2value12,step2value13,step2value14,step2value15,step2value16,step2value17;
    RadioButton rdYes1,rdYes2,rdYes3,rdYes4,rdYes5,rdYes6,rdYes7,rdYes8,rdYes9,rdYes10,rdYes11,rdYes12,rdYes13,rdYes14,rdYes15,rdYes16,rdYes17;
    RadioButton rdNo1,rdNo2,rdNo3,rdNo4,rdNo5,rdNo6,rdNo7,rdNo8,rdNo9,rdNo10,rdNo11,rdNo12,rdNo13,rdNo14,rdNo15,rdNo16,rdNo17;
    LinearLayout step3value1,step3value2,step3value3,step3value4,step3value5,step3value6,step3value7,step3value8,step3value9,step3value10,step3value11,step3value12,step3value13,step3value14,step3value15,step3value16,step3value17;
    RadioButton rdYesHabits1,rdYesHabits2,rdYesHabits3,rdYesHabits4,rdYesHabits5,rdYesHabits6,rdYesHabits7,rdYesHabits8,rdYesHabits9,rdYesHabits10,rdYesHabits11,rdYesHabits12,rdYesHabits13,rdYesHabits14,rdYesHabits15,rdYesHabits16,rdYesHabits17;
    RadioButton rdNoHabits1,rdNoHabits2,rdNoHabits3,rdNoHabits4,rdNoHabits5,rdNoHabits6,rdNoHabits7,rdNoHabits8,rdNoHabits9,rdNoHabits10,rdNoHabits11,rdNoHabits12,rdNoHabits13,rdNoHabits14,rdNoHabits15,rdNoHabits16,rdNoHabits17;
    LinearLayout step4value1,step4value2,step4value3,step4value4,step4value5,step4value6,step4value7,step4value8,step4value9,step4value10,step4value11,step4value12,step4value13,step4value14,step4value15,step4value16,step4value17;
    RadioButton rdYesAdvantage1,rdYesAdvantage2,rdYesAdvantage3,rdYesAdvantage4,rdYesAdvantage5,rdYesAdvantage6,rdYesAdvantage7,rdYesAdvantage8,rdYesAdvantage9,rdYesAdvantage10,rdYesAdvantage11,rdYesAdvantage12,rdYesAdvantage13,rdYesAdvantage14,rdYesAdvantage15,rdYesAdvantage16,rdYesAdvantage17;
    RadioButton rdAdvantage1,rdAdvantage2,rdAdvantage3,rdAdvantage4,rdAdvantage5,rdAdvantage6,rdAdvantage7,rdAdvantage8,rdAdvantage9,rdAdvantage10,rdAdvantage11,rdAdvantage12,rdAdvantage13,rdAdvantage14,rdAdvantage15,rdAdvantage16,rdAdvantage17;
    LinearLayout step5value1,step5value2,step5value3,step5value4,step5value5,step5value6,step5value7,step5value8,step5value9,step5value10,step5value11,step5value12,step5value13,step5value14,step5value15,step5value16,step5value17;
    CardView cardValue1,cardValue2,cardValue3,cardValue4,cardValue5,cardValue6,cardValue7,cardValue8,cardValue9,cardValue10,cardValue11,cardValue12,cardValue13,cardValue14,cardValue15,cardValue16,cardValue17;
    ImageView btnValue1,btnValue2,btnValue3,btnValue4,btnValue5,btnValue6,btnValue7,btnValue8,btnValue9,btnValue10,btnValue11,btnValue12,btnValue13,btnValue14,btnValue15,btnValue16,btnValue17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizational_values);

        step1value1 = findViewById(R.id.step1value1);
        step1value2 = findViewById(R.id.step1value2);
        step1value3 = findViewById(R.id.step1value3);
        step1value4 = findViewById(R.id.step1value4);
        step1value5 = findViewById(R.id.step1value5);
        step1value6 = findViewById(R.id.step1value6);
        step1value7 = findViewById(R.id.step1value7);
        step1value8 = findViewById(R.id.step1value8);
        step1value9 = findViewById(R.id.step1value9);
        step1value10 = findViewById(R.id.step1value10);
        step1value11 = findViewById(R.id.step1value11);
        step1value12 = findViewById(R.id.step1value12);
        step1value13 = findViewById(R.id.step1value13);
        step1value14 = findViewById(R.id.step1value14);
        step1value15 = findViewById(R.id.step1value15);
        step1value16 = findViewById(R.id.step1value16);
        step1value17 = findViewById(R.id.step1value17);

        rdIdentified1 = findViewById(R.id.rdIdentified1);
        rdIdentified2 = findViewById(R.id.rdIdentified2);
        rdIdentified3 = findViewById(R.id.rdIdentified3);
        rdIdentified4 = findViewById(R.id.rdIdentified4);
        rdIdentified5 = findViewById(R.id.rdIdentified5);
        rdIdentified6 = findViewById(R.id.rdIdentified6);
        rdIdentified7 = findViewById(R.id.rdIdentified7);
        rdIdentified8 = findViewById(R.id.rdIdentified8);
        rdIdentified9 = findViewById(R.id.rdIdentified9);
        rdIdentified10 = findViewById(R.id.rdIdentified10);
        rdIdentified11 = findViewById(R.id.rdIdentified11);
        rdIdentified12 = findViewById(R.id.rdIdentified12);
        rdIdentified13 = findViewById(R.id.rdIdentified13);
        rdIdentified14 = findViewById(R.id.rdIdentified14);
        rdIdentified15 = findViewById(R.id.rdIdentified15);
        rdIdentified16 = findViewById(R.id.rdIdentified16);
        rdIdentified17 = findViewById(R.id.rdIdentified17);

        rdNonIdentified1 = findViewById(R.id.rdNonIdentified1);
        rdNonIdentified2 = findViewById(R.id.rdNonIdentified2);
        rdNonIdentified3 = findViewById(R.id.rdNonIdentified3);
        rdNonIdentified4 = findViewById(R.id.rdNonIdentified4);
        rdNonIdentified5 = findViewById(R.id.rdNonIdentified5);
        rdNonIdentified6 = findViewById(R.id.rdNonIdentified6);
        rdNonIdentified7 = findViewById(R.id.rdNonIdentified7);
        rdNonIdentified8 = findViewById(R.id.rdNonIdentified8);
        rdNonIdentified9 = findViewById(R.id.rdNonIdentified9);
        rdNonIdentified10 = findViewById(R.id.rdNonIdentified10);
        rdNonIdentified11 = findViewById(R.id.rdNonIdentified11);
        rdNonIdentified12 = findViewById(R.id.rdNonIdentified12);
        rdNonIdentified13 = findViewById(R.id.rdNonIdentified13);
        rdNonIdentified14 = findViewById(R.id.rdNonIdentified14);
        rdNonIdentified15 = findViewById(R.id.rdNonIdentified15);
        rdNonIdentified16 = findViewById(R.id.rdNonIdentified16);
        rdNonIdentified17 = findViewById(R.id.rdNonIdentified17);

        step2value1 = findViewById(R.id.step2value1);
        step2value2 = findViewById(R.id.step2value2);
        step2value3 = findViewById(R.id.step2value3);
        step2value4 = findViewById(R.id.step2value4);
        step2value5 = findViewById(R.id.step2value5);
        step2value6 = findViewById(R.id.step2value6);
        step2value7 = findViewById(R.id.step2value7);
        step2value8 = findViewById(R.id.step2value8);
        step2value9 = findViewById(R.id.step2value9);
        step2value10 = findViewById(R.id.step2value10);
        step2value11 = findViewById(R.id.step2value11);
        step2value12 = findViewById(R.id.step2value12);
        step2value13 = findViewById(R.id.step2value13);
        step2value14 = findViewById(R.id.step2value14);
        step2value15 = findViewById(R.id.step2value15);
        step2value16 = findViewById(R.id.step2value16);
        step2value17 = findViewById(R.id.step2value17);

        rdYes1 = findViewById(R.id.rdYes1);
        rdYes2 = findViewById(R.id.rdYes2);
        rdYes3 = findViewById(R.id.rdYes3);
        rdYes4 = findViewById(R.id.rdYes4);
        rdYes5 = findViewById(R.id.rdYes5);
        rdYes6 = findViewById(R.id.rdYes6);
        rdYes7 = findViewById(R.id.rdYes7);
        rdYes8 = findViewById(R.id.rdYes8);
        rdYes9 = findViewById(R.id.rdYes9);
        rdYes10 = findViewById(R.id.rdYes10);
        rdYes11 = findViewById(R.id.rdYes11);
        rdYes12 = findViewById(R.id.rdYes12);
        rdYes13 = findViewById(R.id.rdYes13);
        rdYes14 = findViewById(R.id.rdYes14);
        rdYes15 = findViewById(R.id.rdYes15);
        rdYes16 = findViewById(R.id.rdYes16);
        rdYes17 = findViewById(R.id.rdYes17);

        rdNo1 = findViewById(R.id.rdNo1);
        rdNo2 = findViewById(R.id.rdNo2);
        rdNo3 = findViewById(R.id.rdNo3);
        rdNo4 = findViewById(R.id.rdNo4);
        rdNo5 = findViewById(R.id.rdNo5);
        rdNo6 = findViewById(R.id.rdNo6);
        rdNo7 = findViewById(R.id.rdNo7);
        rdNo8 = findViewById(R.id.rdNo8);
        rdNo9 = findViewById(R.id.rdNo9);
        rdNo10 = findViewById(R.id.rdNo10);
        rdNo11 = findViewById(R.id.rdNo11);
        rdNo12 = findViewById(R.id.rdNo12);
        rdNo13 = findViewById(R.id.rdNo13);
        rdNo14 = findViewById(R.id.rdNo14);
        rdNo15 = findViewById(R.id.rdNo15);
        rdNo16 = findViewById(R.id.rdNo16);
        rdNo17 = findViewById(R.id.rdNo17);

        step3value1 = findViewById(R.id.step3value1);
        step3value2 = findViewById(R.id.step3value2);
        step3value3 = findViewById(R.id.step3value3);
        step3value4 = findViewById(R.id.step3value4);
        step3value5 = findViewById(R.id.step3value5);
        step3value6 = findViewById(R.id.step3value6);
        step3value7 = findViewById(R.id.step3value7);
        step3value8 = findViewById(R.id.step3value8);
        step3value9 = findViewById(R.id.step3value9);
        step3value10 = findViewById(R.id.step3value10);
        step3value11 = findViewById(R.id.step3value11);
        step3value12 = findViewById(R.id.step3value12);
        step3value13 = findViewById(R.id.step3value13);
        step3value14 = findViewById(R.id.step3value14);
        step3value15 = findViewById(R.id.step3value15);
        step3value16 = findViewById(R.id.step3value16);
        step3value17 = findViewById(R.id.step3value17);

        rdYesHabits1 = findViewById(R.id.rdYesHabits1);
        rdYesHabits2 = findViewById(R.id.rdYesHabits2);
        rdYesHabits3 = findViewById(R.id.rdYesHabits3);
        rdYesHabits4 = findViewById(R.id.rdYesHabits4);
        rdYesHabits5 = findViewById(R.id.rdYesHabits5);
        rdYesHabits6 = findViewById(R.id.rdYesHabits6);
        rdYesHabits7 = findViewById(R.id.rdYesHabits7);
        rdYesHabits8 = findViewById(R.id.rdYesHabits8);
        rdYesHabits9 = findViewById(R.id.rdYesHabits9);
        rdYesHabits10 = findViewById(R.id.rdYesHabits10);
        rdYesHabits11 = findViewById(R.id.rdYesHabits11);
        rdYesHabits12 = findViewById(R.id.rdYesHabits12);
        rdYesHabits13 = findViewById(R.id.rdYesHabits13);
        rdYesHabits14 = findViewById(R.id.rdYesHabits14);
        rdYesHabits15 = findViewById(R.id.rdYesHabits15);
        rdYesHabits16 = findViewById(R.id.rdYesHabits16);
        rdYesHabits17 = findViewById(R.id.rdYesHabits17);

        rdNoHabits1 = findViewById(R.id.rdNoHabits1);
        rdNoHabits2 = findViewById(R.id.rdNoHabits2);
        rdNoHabits3 = findViewById(R.id.rdNoHabits3);
        rdNoHabits4 = findViewById(R.id.rdNoHabits4);
        rdNoHabits5 = findViewById(R.id.rdNoHabits5);
        rdNoHabits6 = findViewById(R.id.rdNoHabits6);
        rdNoHabits7 = findViewById(R.id.rdNoHabits7);
        rdNoHabits8 = findViewById(R.id.rdNoHabits8);
        rdNoHabits9 = findViewById(R.id.rdNoHabits9);
        rdNoHabits10 = findViewById(R.id.rdNoHabits10);
        rdNoHabits11 = findViewById(R.id.rdNoHabits11);
        rdNoHabits12 = findViewById(R.id.rdNoHabits12);
        rdNoHabits13 = findViewById(R.id.rdNoHabits13);
        rdNoHabits14 = findViewById(R.id.rdNoHabits14);
        rdNoHabits15 = findViewById(R.id.rdNoHabits15);
        rdNoHabits16 = findViewById(R.id.rdNoHabits16);
        rdNoHabits17 = findViewById(R.id.rdNoHabits17);

        step4value1 = findViewById(R.id.step4value1);
        step4value2 = findViewById(R.id.step4value2);
        step4value3 = findViewById(R.id.step4value3);
        step4value4 = findViewById(R.id.step4value4);
        step4value5 = findViewById(R.id.step4value5);
        step4value6 = findViewById(R.id.step4value6);
        step4value7 = findViewById(R.id.step4value7);
        step4value8 = findViewById(R.id.step4value8);
        step4value9 = findViewById(R.id.step4value9);
        step4value10 = findViewById(R.id.step4value10);
        step4value11 = findViewById(R.id.step4value11);
        step4value12 = findViewById(R.id.step4value12);
        step4value13 = findViewById(R.id.step4value13);
        step4value14 = findViewById(R.id.step4value14);
        step4value15 = findViewById(R.id.step4value15);
        step4value16 = findViewById(R.id.step4value16);
        step4value17 = findViewById(R.id.step4value17);

        rdYesAdvantage1 = findViewById(R.id.rdYesAdvantage1);
        rdYesAdvantage2 = findViewById(R.id.rdYesAdvantage2);
        rdYesAdvantage3 = findViewById(R.id.rdYesAdvantage3);
        rdYesAdvantage4 = findViewById(R.id.rdYesAdvantage4);
        rdYesAdvantage5 = findViewById(R.id.rdYesAdvantage5);
        rdYesAdvantage6 = findViewById(R.id.rdYesAdvantage6);
        rdYesAdvantage7 = findViewById(R.id.rdYesAdvantage7);
        rdYesAdvantage8 = findViewById(R.id.rdYesAdvantage8);
        rdYesAdvantage9 = findViewById(R.id.rdYesAdvantage9);
        rdYesAdvantage10 = findViewById(R.id.rdYesAdvantage10);
        rdYesAdvantage11 = findViewById(R.id.rdYesAdvantage11);
        rdYesAdvantage12 = findViewById(R.id.rdYesAdvantage12);
        rdYesAdvantage13 = findViewById(R.id.rdYesAdvantage13);
        rdYesAdvantage14 = findViewById(R.id.rdYesAdvantage14);
        rdYesAdvantage15 = findViewById(R.id.rdYesAdvantage15);
        rdYesAdvantage16 = findViewById(R.id.rdYesAdvantage16);
        rdYesAdvantage17 = findViewById(R.id.rdYesAdvantage17);

        rdAdvantage1 = findViewById(R.id.rdAdvantage1);
        rdAdvantage2 = findViewById(R.id.rdAdvantage2);
        rdAdvantage3 = findViewById(R.id.rdAdvantage3);
        rdAdvantage4 = findViewById(R.id.rdAdvantage4);
        rdAdvantage5 = findViewById(R.id.rdAdvantage5);
        rdAdvantage6 = findViewById(R.id.rdAdvantage6);
        rdAdvantage7 = findViewById(R.id.rdAdvantage7);
        rdAdvantage8 = findViewById(R.id.rdAdvantage8);
        rdAdvantage9 = findViewById(R.id.rdAdvantage9);
        rdAdvantage10 = findViewById(R.id.rdAdvantage10);
        rdAdvantage11 = findViewById(R.id.rdAdvantage11);
        rdAdvantage12 = findViewById(R.id.rdAdvantage12);
        rdAdvantage13 = findViewById(R.id.rdAdvantage13);
        rdAdvantage14 = findViewById(R.id.rdAdvantage14);
        rdAdvantage15 = findViewById(R.id.rdAdvantage15);
        rdAdvantage16 = findViewById(R.id.rdAdvantage16);
        rdAdvantage17 = findViewById(R.id.rdAdvantage17);

        step5value1 = findViewById(R.id.step5value1);
        step5value2 = findViewById(R.id.step5value2);
        step5value3 = findViewById(R.id.step5value3);
        step5value4 = findViewById(R.id.step5value4);
        step5value5 = findViewById(R.id.step5value5);
        step5value6 = findViewById(R.id.step5value6);
        step5value7 = findViewById(R.id.step5value7);
        step5value8 = findViewById(R.id.step5value8);
        step5value9 = findViewById(R.id.step5value9);
        step5value10 = findViewById(R.id.step5value10);
        step5value11 = findViewById(R.id.step5value11);
        step5value12 = findViewById(R.id.step5value12);
        step5value13 = findViewById(R.id.step5value13);
        step5value14 = findViewById(R.id.step5value14);
        step5value15 = findViewById(R.id.step5value15);
        step5value16 = findViewById(R.id.step5value16);
        step5value17 = findViewById(R.id.step5value17);

        cardValue1 = findViewById(R.id.cardValue1);
        cardValue2 = findViewById(R.id.cardValue2);
        cardValue3 = findViewById(R.id.cardValue3);
        cardValue4 = findViewById(R.id.cardValue4);
        cardValue5 = findViewById(R.id.cardValue5);
        cardValue6 = findViewById(R.id.cardValue6);
        cardValue7 = findViewById(R.id.cardValue7);
        cardValue8 = findViewById(R.id.cardValue8);
        cardValue9 = findViewById(R.id.cardValue9);
        cardValue10 = findViewById(R.id.cardValue10);
        cardValue11 = findViewById(R.id.cardValue11);
        cardValue12 = findViewById(R.id.cardValue12);
        cardValue13 = findViewById(R.id.cardValue13);
        cardValue14 = findViewById(R.id.cardValue14);
        cardValue15 = findViewById(R.id.cardValue15);
        cardValue16 = findViewById(R.id.cardValue16);
        cardValue17 = findViewById(R.id.cardValue17);

        btnValue1 = findViewById(R.id.btnValue1);
        btnValue2 = findViewById(R.id.btnValue2);
        btnValue3 = findViewById(R.id.btnValue3);
        btnValue4 = findViewById(R.id.btnValue4);
        btnValue5 = findViewById(R.id.btnValue5);
        btnValue6 = findViewById(R.id.btnValue6);
        btnValue7 = findViewById(R.id.btnValue7);
        btnValue8 = findViewById(R.id.btnValue8);
        btnValue9 = findViewById(R.id.btnValue9);
        btnValue10 = findViewById(R.id.btnValue10);
        btnValue11 = findViewById(R.id.btnValue11);
        btnValue12 = findViewById(R.id.btnValue12);
        btnValue13 = findViewById(R.id.btnValue13);
        btnValue14 = findViewById(R.id.btnValue14);
        btnValue15 = findViewById(R.id.btnValue15);
        btnValue16 = findViewById(R.id.btnValue16);
        btnValue17 = findViewById(R.id.btnValue17);

        btnValue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Transparencia";
                String description = "En un entorno social donde cada vez es menos frecuente, dentro de nuestros valores empresariales podemos integrar la transparencia hacia nuestro equipo y hacia nuestros clientes. Transparencia implica confianza y las relaciones humanas, incluidas las comerciales, se forjan con confianza.";

                showValueDescriptionDialog(title,description);
            }
        });

        btnValue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Puntualidad";
                String description = "El tiempo es dinero, y la gente cada vez valora más el suyo. No solo es importante tenerlo en cuenta para nuestro equipo y su hora de llegada a su trabajo, sino también en otras situaciones que afectan directamente al cliente potencial, como las reuniones de venta; al cliente actual, como los envíos y plazos de entrega; e incluso a nuestro equipo, como el pago de facturas y nóminas.";

                showValueDescriptionDialog(title,description);
            }
        });

        btnValue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Diferencia";
                String description = " ¿Qué aportamos que no aporte nuestra competencia? La respuesta a esta pregunta será clave para que los clientes potenciales quieran comprarnos y para que haya personas con talento dispuestas a trabajar con nosotros.";

                showValueDescriptionDialog(title,description);
            }
        });

        btnValue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Excelencia";
                String description = "La calidad llevada al máximo, eso es la excelencia. Si nos exigimos lo mejor, podremos dar lo mejor. Y que un cliente y un empleado vean que les ofrecemos algo excelente les impulsará a quedarse con nosotros.";

                showValueDescriptionDialog(title,description);
            }
        });

        btnValue5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Libertad";
                String description = "Sobre todo hacia nuestro equipo. Las personas creativas son cada vez más valiosas para una empresa, pero exigen libertad de pensamiento y de creación de ideas. Si no se la ofrecemos, nos abandonarán.";

                showValueDescriptionDialog(title,description);
            }
        });

        btnValue6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Escucha";
                String description = "La escucha es un valor empresarial que implica libertad de expresión. Es muy importante que nuestro equipo y nuestros clientes sientan que les escuchamos y que tenemos en cuenta lo que nos dicen.";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Responsabilidad";
                String description = "Tanto en la vertiente social como en la ecológica, si demostramos ser responsables con la sociedad y el medio ambiente, haremos ver que no nos interesan únicamente los beneficios económicos.";

                showValueDescriptionDialog(title,description);
            }
        });

        btnValue8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Emocionalidad";
                String description = "Las personas son diferentes de las máquinas en varios aspectos, y uno de los más importantes son sus emociones. Las personas sienten, y si integramos la emocionalidad como uno de nuestros valores empresariales conseguiremos hacerles sentir. Esto contribuirá a que se unan a nosotros.";

                showValueDescriptionDialog(title,description);
            }
        });

        btnValue9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Pasión";
                String description = "Este valor está directamente ligado al anterior, pero implica una mayor fuerza. Cuando alguien se muestra apasionado con su trabajo y con lo que hace, contagia esa pasión y esa energía a los demás. Si nuestra empresa les transmite a su equipo y sus clientes dicha pasión, ellos también se sentirán así.";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Resolución";
                String description = "Es la capacidad para resolver problemas. ¿Verdad que si consideras que una persona es resolutiva acudirás a ella cuando tengas un problema? Con tu empresa puede ocurrir lo mismo si implantas la resolución como uno de tus valores empresariales.";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Claridad";
                String description = "A la hora de transmitir una idea, a la hora de cerrar un acuerdo, a la hora de explicar una serie de ventajas… Es vital ser claro, ya que a nadie le gusta no entender algo y tener la sensación de que quedan puntos sin tratar y sin aclarar.";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Lealtad";
                String description = "Si nos mostramos leales y fieles con nuestro equipo y nuestros clientes, ellos nos devolverán esa fidelidad. El impulso de reciprocidad está insertado en la naturaleza humana desde sus orígenes.";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Excelencia en la gestión";
                String description = "centrado en el flujo de la información del cliente (entrada y salida), supervisado por las áreas responsables de los mismos, de forma transparente y profesional, teniendo como modelo los Procesos Iso 9000 que se detalla en la siguiente imagen: ";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Investigación + Desarrollo + Innovación + Calidad";
                String description = "motivando y gestionando el uso de la tecnología (software y hardware) y aplicando metodologías (competencias TIC: utilización estratégica de la información), en todas y cada una de las áreas de negocio para asegurar el éxito al cliente.";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Orientación al Cliente y Confidencialidad";
                String description = "Fijar al cliente interno (empleados y aliados) y externo (clientes) como centro de nuestras actividades, para lo cual se deberá establecer canales eficaces de comunicación y participación con los clientes, para conocer sus verdaderas necesidades y expectativas, que nos faciliten adecuarnos a ellos con la finalidad de potenciar relaciones estables y duraderas.";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Integración Profesional y Personal";
                String description = "Lograr en nuestros empleados, la identificación y sentido de pertenencia a la empresa, dando las mayores facilidades para la conciliación laboral y familiar.   ";

                showValueDescriptionDialog(title,description);
            }
        });
        btnValue17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Trabajo en Equipo";
                String description = "A través de relaciones horizontales y de puertas abiertas para el mutuo apoyo entre nosotros, con escuchas activas y pasivas.";

                showValueDescriptionDialog(title,description);
            }
        });

        value1();
        value2();
        value3();
        value4();
        value5();
        value6();
        value7();
        value8();
        value9();
        value10();
        value11();
        value12();
        value13();
        value14();
        value15();
        value16();
        value17();

    }

    private void showValueDescriptionDialog(String title, String description) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.organizational_values_dialog,null);

        TextView txtValueName,txtValueDescription;

        txtValueName = finance_method.findViewById(R.id.txtValueName);
        txtValueDescription = finance_method.findViewById(R.id.txtValueDescription);

        txtValueName.setText(title);
        txtValueDescription.setText(description);


        dialog.setView(finance_method);
        dialog.show();
    }


    private void value17() {
        step2value17.setVisibility(View.GONE);
        step3value17.setVisibility(View.GONE);
        step4value17.setVisibility(View.GONE);
        step5value17.setVisibility(View.GONE);

        rdIdentified17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value17.setVisibility(View.GONE);
                step2value17.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue17.setVisibility(View.GONE);
            }
        });


        rdYes17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue17.setVisibility(View.GONE);
            }
        });

        rdNo17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value17.setVisibility(View.GONE);
                step3value17.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value17.setVisibility(View.GONE);
                step4value17.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue17.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value17.setVisibility(View.VISIBLE);
                step4value17.setVisibility(View.GONE);
            }
        });

        rdAdvantage17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue17.setVisibility(View.GONE);
            }
        });
    }

    private void value16() {
        step2value16.setVisibility(View.GONE);
        step3value16.setVisibility(View.GONE);
        step4value16.setVisibility(View.GONE);
        step5value16.setVisibility(View.GONE);

        rdIdentified16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value16.setVisibility(View.GONE);
                step2value16.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue16.setVisibility(View.GONE);
            }
        });


        rdYes16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue16.setVisibility(View.GONE);
            }
        });

        rdNo16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value16.setVisibility(View.GONE);
                step3value16.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value16.setVisibility(View.GONE);
                step4value16.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue16.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value16.setVisibility(View.VISIBLE);
                step4value16.setVisibility(View.GONE);
            }
        });

        rdAdvantage16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue16.setVisibility(View.GONE);
            }
        });
    }

    private void value15() {
        step2value15.setVisibility(View.GONE);
        step3value15.setVisibility(View.GONE);
        step4value15.setVisibility(View.GONE);
        step5value15.setVisibility(View.GONE);

        rdIdentified15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value15.setVisibility(View.GONE);
                step2value15.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue15.setVisibility(View.GONE);
            }
        });


        rdYes15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue15.setVisibility(View.GONE);
            }
        });

        rdNo15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value15.setVisibility(View.GONE);
                step3value15.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value15.setVisibility(View.GONE);
                step4value15.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue15.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value15.setVisibility(View.VISIBLE);
                step4value15.setVisibility(View.GONE);
            }
        });

        rdAdvantage15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue15.setVisibility(View.GONE);
            }
        });
    }

    private void value14() {
        step2value14.setVisibility(View.GONE);
        step3value14.setVisibility(View.GONE);
        step4value14.setVisibility(View.GONE);
        step5value14.setVisibility(View.GONE);

        rdIdentified14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value14.setVisibility(View.GONE);
                step2value14.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue14.setVisibility(View.GONE);
            }
        });


        rdYes14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue14.setVisibility(View.GONE);
            }
        });

        rdNo14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value14.setVisibility(View.GONE);
                step3value14.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value14.setVisibility(View.GONE);
                step4value14.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue13.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value14.setVisibility(View.VISIBLE);
                step4value14.setVisibility(View.GONE);
            }
        });

        rdAdvantage14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue14.setVisibility(View.GONE);
            }
        });
    }

    private void value13() {
        step2value13.setVisibility(View.GONE);
        step3value13.setVisibility(View.GONE);
        step4value13.setVisibility(View.GONE);
        step5value13.setVisibility(View.GONE);

        rdIdentified13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value13.setVisibility(View.GONE);
                step2value13.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue13.setVisibility(View.GONE);
            }
        });


        rdYes13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue13.setVisibility(View.GONE);
            }
        });

        rdNo13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value13.setVisibility(View.GONE);
                step3value13.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value13.setVisibility(View.GONE);
                step4value13.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue13.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value13.setVisibility(View.VISIBLE);
                step4value13.setVisibility(View.GONE);
            }
        });

        rdAdvantage13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue13.setVisibility(View.GONE);
            }
        });
    }

    private void value12() {
        step2value12.setVisibility(View.GONE);
        step3value12.setVisibility(View.GONE);
        step4value12.setVisibility(View.GONE);
        step5value12.setVisibility(View.GONE);

        rdIdentified12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value12.setVisibility(View.GONE);
                step2value12.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue12.setVisibility(View.GONE);
            }
        });


        rdYes12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue12.setVisibility(View.GONE);
            }
        });

        rdNo12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value12.setVisibility(View.GONE);
                step3value12.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value12.setVisibility(View.GONE);
                step4value12.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue12.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value12.setVisibility(View.VISIBLE);
                step4value12.setVisibility(View.GONE);
            }
        });

        rdAdvantage12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue12.setVisibility(View.GONE);
            }
        });
    }

    private void value11() {
        step2value11.setVisibility(View.GONE);
        step3value11.setVisibility(View.GONE);
        step4value11.setVisibility(View.GONE);
        step5value11.setVisibility(View.GONE);

        rdIdentified11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value11.setVisibility(View.GONE);
                step2value11.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue11.setVisibility(View.GONE);
            }
        });


        rdYes11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue11.setVisibility(View.GONE);
            }
        });

        rdNo11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value11.setVisibility(View.GONE);
                step3value11.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value11.setVisibility(View.GONE);
                step4value11.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue11.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value11.setVisibility(View.VISIBLE);
                step4value11.setVisibility(View.GONE);
            }
        });

        rdAdvantage11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue11.setVisibility(View.GONE);
            }
        });
    }

    private void value10() {
        step2value10.setVisibility(View.GONE);
        step3value10.setVisibility(View.GONE);
        step4value10.setVisibility(View.GONE);
        step5value10.setVisibility(View.GONE);

        rdIdentified10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value10.setVisibility(View.GONE);
                step2value10.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue10.setVisibility(View.GONE);
            }
        });


        rdYes10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue10.setVisibility(View.GONE);
            }
        });

        rdNo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value10.setVisibility(View.GONE);
                step3value10.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value10.setVisibility(View.GONE);
                step4value10.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue10.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value10.setVisibility(View.VISIBLE);
                step4value10.setVisibility(View.GONE);
            }
        });

        rdAdvantage10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue10.setVisibility(View.GONE);
            }
        });
    }

    private void value9() {
        step2value9.setVisibility(View.GONE);
        step3value9.setVisibility(View.GONE);
        step4value9.setVisibility(View.GONE);
        step5value9.setVisibility(View.GONE);

        rdIdentified9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value9.setVisibility(View.GONE);
                step2value9.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue9.setVisibility(View.GONE);
            }
        });


        rdYes9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue9.setVisibility(View.GONE);
            }
        });

        rdNo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value9.setVisibility(View.GONE);
                step3value9.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value9.setVisibility(View.GONE);
                step4value9.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue9.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value9.setVisibility(View.VISIBLE);
                step4value9.setVisibility(View.GONE);
            }
        });

        rdAdvantage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue9.setVisibility(View.GONE);
            }
        });
    }

    private void value8() {
        step2value8.setVisibility(View.GONE);
        step3value8.setVisibility(View.GONE);
        step4value8.setVisibility(View.GONE);
        step5value8.setVisibility(View.GONE);

        rdIdentified8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value8.setVisibility(View.GONE);
                step2value8.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue8.setVisibility(View.GONE);
            }
        });


        rdYes8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue8.setVisibility(View.GONE);
            }
        });

        rdNo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value8.setVisibility(View.GONE);
                step3value8.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value8.setVisibility(View.GONE);
                step4value8.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue8.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value8.setVisibility(View.VISIBLE);
                step4value8.setVisibility(View.GONE);
            }
        });

        rdAdvantage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue8.setVisibility(View.GONE);
            }
        });
    }

    private void value7() {
        step2value7.setVisibility(View.GONE);
        step3value7.setVisibility(View.GONE);
        step4value7.setVisibility(View.GONE);
        step5value7.setVisibility(View.GONE);

        rdIdentified7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value7.setVisibility(View.GONE);
                step2value7.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue7.setVisibility(View.GONE);
            }
        });


        rdYes7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue7.setVisibility(View.GONE);
            }
        });

        rdNo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value7.setVisibility(View.GONE);
                step3value7.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value7.setVisibility(View.GONE);
                step4value7.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue7.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value7.setVisibility(View.VISIBLE);
                step4value7.setVisibility(View.GONE);
            }
        });

        rdAdvantage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue7.setVisibility(View.GONE);
            }
        });
    }

    private void value6() {
        step2value6.setVisibility(View.GONE);
        step3value6.setVisibility(View.GONE);
        step4value6.setVisibility(View.GONE);
        step5value6.setVisibility(View.GONE);

        rdIdentified6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value6.setVisibility(View.GONE);
                step2value6.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue6.setVisibility(View.GONE);
            }
        });


        rdYes6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue6.setVisibility(View.GONE);
            }
        });

        rdNo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value6.setVisibility(View.GONE);
                step3value6.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value6.setVisibility(View.GONE);
                step4value6.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue6.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value6.setVisibility(View.VISIBLE);
                step4value6.setVisibility(View.GONE);
            }
        });

        rdAdvantage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue6.setVisibility(View.GONE);
            }
        });
    }

    private void value5() {
        step2value5.setVisibility(View.GONE);
        step3value5.setVisibility(View.GONE);
        step4value5.setVisibility(View.GONE);
        step5value5.setVisibility(View.GONE);

        rdIdentified5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value5.setVisibility(View.GONE);
                step2value5.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue5.setVisibility(View.GONE);
            }
        });


        rdYes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue5.setVisibility(View.GONE);
            }
        });

        rdNo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value5.setVisibility(View.GONE);
                step3value5.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value5.setVisibility(View.GONE);
                step4value5.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue5.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value5.setVisibility(View.VISIBLE);
                step4value5.setVisibility(View.GONE);
            }
        });

        rdAdvantage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue5.setVisibility(View.GONE);
            }
        });
    }

    private void value4() {
        step2value4.setVisibility(View.GONE);
        step3value4.setVisibility(View.GONE);
        step4value4.setVisibility(View.GONE);
        step5value4.setVisibility(View.GONE);

        rdIdentified4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value4.setVisibility(View.GONE);
                step2value4.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue4.setVisibility(View.GONE);
            }
        });


        rdYes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue4.setVisibility(View.GONE);
            }
        });

        rdNo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value4.setVisibility(View.GONE);
                step3value4.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value4.setVisibility(View.GONE);
                step4value4.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue4.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value4.setVisibility(View.VISIBLE);
                step4value4.setVisibility(View.GONE);
            }
        });

        rdAdvantage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue4.setVisibility(View.GONE);
            }
        });
    }

    private void value3() {
        step2value3.setVisibility(View.GONE);
        step3value3.setVisibility(View.GONE);
        step4value3.setVisibility(View.GONE);
        step5value3.setVisibility(View.GONE);

        rdIdentified3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value3.setVisibility(View.GONE);
                step2value3.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue3.setVisibility(View.GONE);
            }
        });


        rdYes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue3.setVisibility(View.GONE);
            }
        });

        rdNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value3.setVisibility(View.GONE);
                step3value3.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value3.setVisibility(View.GONE);
                step4value3.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue3.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value3.setVisibility(View.VISIBLE);
                step4value3.setVisibility(View.GONE);
            }
        });

        rdAdvantage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue3.setVisibility(View.GONE);
            }
        });
    }

    private void value2() {
        step2value2.setVisibility(View.GONE);
        step3value2.setVisibility(View.GONE);
        step4value2.setVisibility(View.GONE);
        step5value2.setVisibility(View.GONE);

        rdIdentified2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value2.setVisibility(View.GONE);
                step2value2.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue2.setVisibility(View.GONE);
            }
        });


        rdYes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue2.setVisibility(View.GONE);
            }
        });

        rdNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value2.setVisibility(View.GONE);
                step3value2.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value2.setVisibility(View.GONE);
                step4value2.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue2.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value2.setVisibility(View.VISIBLE);
                step4value2.setVisibility(View.GONE);
            }
        });

        rdAdvantage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue2.setVisibility(View.GONE);
            }
        });
    }

    private void value1() {
        step2value1.setVisibility(View.GONE);
        step3value1.setVisibility(View.GONE);
        step4value1.setVisibility(View.GONE);
        step5value1.setVisibility(View.GONE);

        rdIdentified1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1value1.setVisibility(View.GONE);
                step2value1.setVisibility(View.VISIBLE);
            }
        });
        rdNonIdentified1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue1.setVisibility(View.GONE);
            }
        });


        rdYes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue1.setVisibility(View.GONE);
            }
        });

        rdNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step2value1.setVisibility(View.GONE);
                step3value1.setVisibility(View.VISIBLE);
            }
        });

        rdYesHabits1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3value1.setVisibility(View.GONE);
                step4value1.setVisibility(View.VISIBLE);
            }
        });

        rdNoHabits1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue1.setVisibility(View.GONE);
            }
        });

        rdYesAdvantage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step5value1.setVisibility(View.VISIBLE);
                step4value1.setVisibility(View.GONE);
            }
        });

        rdAdvantage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(OrganizationalValuesActivity.this, "Valor Descartado", Toast.LENGTH_LONG).show();
                cardValue1.setVisibility(View.GONE);
            }
        });
    }
}
