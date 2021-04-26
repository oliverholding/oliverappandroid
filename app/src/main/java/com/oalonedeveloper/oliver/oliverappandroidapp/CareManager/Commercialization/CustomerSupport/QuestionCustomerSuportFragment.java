package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CustomerSupport;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.HashMap;


public class QuestionCustomerSuportFragment extends Fragment {

    RadioButton rd001A,rd001B,rd002A,rd002B,rd003A,rd003B,rd004A,rd004B,rd005A,rd005B,rd005C,rd006A,rd006B,rd006C,rd006D,rd006E,rd006F,rd007A,rd007B,rd007C,rd007D,rd008A,rd008B,rd009A,rd009B,rd010A,rd010B,rd011A,rd011B,
            rd012A,rd012B,rd013A,rd013B,rd014A,rd014B,rd014C,rd016A,rd016B,rd016C,rd017A,rd017B,rd017C,rd017D,rd018A,rd018B,rd018C,rd018D,rd018E,rd018F,rd020A,rd020B;
    CheckBox rd015A,rd015B,rd015C,rd015D,rd015E,rd015F,rd019A,rd019B,rd019C,rd019D,rd019E,rd019F,rd019G,rd019H;
    String post_key;
    DatabaseReference companyRef;
    Button btnFinish;
    RelativeLayout rootLayout;
    String answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8,answer9,answer10,answer11,answer12,answer13,answer14,answer15A,answer15B,answer15C,answer15D,answer15E,answer15F,answer16,answer17,answer18,
            answer19A,answer19B,answer19C,answer19D,answer19E,answer19F,answer19G,answer19H,answer20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_question_customer_suport, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnFinish = view.findViewById(R.id.btnFinish);
        rootLayout = view.findViewById(R.id.rootLayout);
        rd001A = view.findViewById(R.id.rd001A);
        rd001B = view.findViewById(R.id.rd001B);
        rd002A = view.findViewById(R.id.rd002A);
        rd002B = view.findViewById(R.id.rd002B);
        rd003A = view.findViewById(R.id.rd003A);
        rd003B = view.findViewById(R.id.rd003B);
        rd004A = view.findViewById(R.id.rd004A);
        rd004B = view.findViewById(R.id.rd004B);
        rd005A = view.findViewById(R.id.rd005A);
        rd005B = view.findViewById(R.id.rd005B);
        rd005C = view.findViewById(R.id.rd005C);
        rd006A = view.findViewById(R.id.rd006A);
        rd006B = view.findViewById(R.id.rd006B);
        rd006C = view.findViewById(R.id.rd006C);
        rd006D = view.findViewById(R.id.rd006D);
        rd006E = view.findViewById(R.id.rd006E);
        rd006F = view.findViewById(R.id.rd006F);
        rd007A = view.findViewById(R.id.rd007A);
        rd007B = view.findViewById(R.id.rd007B);
        rd007C = view.findViewById(R.id.rd007C);
        rd007D = view.findViewById(R.id.rd007D);
        rd008A = view.findViewById(R.id.rd008A);
        rd008B = view.findViewById(R.id.rd008B);
        rd009A = view.findViewById(R.id.rd009A);
        rd009B = view.findViewById(R.id.rd009B);
        rd010A = view.findViewById(R.id.rd010A);
        rd010B = view.findViewById(R.id.rd010B);
        rd011A = view.findViewById(R.id.rd011A);
        rd011B = view.findViewById(R.id.rd011B);
        rd012A = view.findViewById(R.id.rd012A);
        rd012B = view.findViewById(R.id.rd012B);
        rd013A = view.findViewById(R.id.rd013A);
        rd013B = view.findViewById(R.id.rd013B);
        rd014A = view.findViewById(R.id.rd014A);
        rd014B = view.findViewById(R.id.rd014B);
        rd014C = view.findViewById(R.id.rd014C);
        rd015A = view.findViewById(R.id.rd015A);
        rd015B = view.findViewById(R.id.rd015B);
        rd015C = view.findViewById(R.id.rd015C);
        rd015D = view.findViewById(R.id.rd015D);
        rd015E = view.findViewById(R.id.rd015E);
        rd015F = view.findViewById(R.id.rd015F);
        rd016A = view.findViewById(R.id.rd016A);
        rd016B = view.findViewById(R.id.rd016B);
        rd016C = view.findViewById(R.id.rd016C);
        rd017A = view.findViewById(R.id.rd017A);
        rd017B = view.findViewById(R.id.rd017B);
        rd017C = view.findViewById(R.id.rd017C);
        rd017D = view.findViewById(R.id.rd017D);
        rd018A = view.findViewById(R.id.rd018A);
        rd018B = view.findViewById(R.id.rd018B);
        rd018C = view.findViewById(R.id.rd018C);
        rd018D = view.findViewById(R.id.rd018D);
        rd018E = view.findViewById(R.id.rd018E);
        rd018F = view.findViewById(R.id.rd018F);
        rd019A = view.findViewById(R.id.rd019A);
        rd019B = view.findViewById(R.id.rd019B);
        rd019C = view.findViewById(R.id.rd019C);
        rd019D = view.findViewById(R.id.rd019D);
        rd019E = view.findViewById(R.id.rd019E);
        rd019F = view.findViewById(R.id.rd019F);
        rd019G = view.findViewById(R.id.rd019G);
        rd019H = view.findViewById(R.id.rd019H);
        rd020A = view.findViewById(R.id.rd020A);
        rd020B = view.findViewById(R.id.rd020B);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rd001A.isChecked() && !rd001B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd002A.isChecked() && !rd002B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd003A.isChecked() && !rd003B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd004A.isChecked() && !rd004B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd005A.isChecked() && !rd005B.isChecked()&& !rd005C.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd006A.isChecked() && !rd006B.isChecked()&& !rd006C.isChecked() && !rd006D.isChecked() && !rd006E.isChecked() && !rd006F.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd007A.isChecked() && !rd007B.isChecked()&& !rd007C.isChecked() && !rd007D.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd008A.isChecked() && !rd008B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd009A.isChecked() && !rd009B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd010A.isChecked() && !rd010B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd011A.isChecked() && !rd011B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd012A.isChecked() && !rd012B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd013A.isChecked() && !rd013B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd014A.isChecked() && !rd014B.isChecked() && !rd014C.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd015A.isChecked() && !rd015B.isChecked() && !rd015C.isChecked() && !rd015D.isChecked() && !rd015E.isChecked() && !rd015F.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd016A.isChecked() && !rd016B.isChecked() && !rd016C.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd017A.isChecked() && !rd017B.isChecked() && !rd017C.isChecked() && !rd017D.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd018A.isChecked() && !rd018B.isChecked() && !rd018C.isChecked() && !rd018D.isChecked() && !rd018E.isChecked() && !rd018F.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd019A.isChecked() && !rd019B.isChecked() && !rd019C.isChecked() && !rd019D.isChecked() && !rd019E.isChecked() && !rd019F.isChecked() && !rd019G.isChecked() && !rd019H.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!rd020A.isChecked() && !rd020B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_LONG).show();
                    return;
                }else {
                    registerData();
                }
            }
        });


        return view;
    }

    private void registerData() {
        if (rd001A.isChecked()) {
            answer1 = rd001A.getText().toString();
        }
        else if (rd001B.isChecked()) {
            answer1 = rd001B.getText().toString();
        }

        if (rd002A.isChecked()) {
            answer2 = rd002A.getText().toString();
        }
        else if (rd002B.isChecked()) {
            answer2 = rd002B.getText().toString();
        }

        if (rd003A.isChecked()) {
            answer3 = rd003A.getText().toString();
        }
        else if (rd002B.isChecked()) {
            answer3 = rd003B.getText().toString();
        }

        if (rd004A.isChecked()) {
            answer4 = rd004A.getText().toString();
        }
        else if (rd004B.isChecked()) {
            answer4 = rd004B.getText().toString();
        }

        if (rd005A.isChecked()) {
            answer5 = rd005A.getText().toString();
        }
        else if (rd005B.isChecked()) {
            answer5 = rd005B.getText().toString();
        }
        else if (rd005C.isChecked()) {
            answer5 = rd005C.getText().toString();
        }

        if (rd006A.isChecked()) {
            answer6 = rd006A.getText().toString();
        }
        else if (rd006B.isChecked()) {
            answer6 = rd006B.getText().toString();
        }
        else if (rd006C.isChecked()) {
            answer6 = rd006C.getText().toString();
        }
        else if (rd006D.isChecked()) {
            answer6 = rd006D.getText().toString();
        }
        else if (rd006E.isChecked()) {
            answer6 = rd006E.getText().toString();
        }
        else if (rd006F.isChecked()) {
            answer6 = rd006F.getText().toString();
        }

        if (rd007A.isChecked()) {
            answer7 = rd007A.getText().toString();
        }
        else if (rd007B.isChecked()) {
            answer7 = rd007B.getText().toString();
        }
        else if (rd007C.isChecked()) {
            answer7 = rd007C.getText().toString();
        }
        else if (rd007D.isChecked()) {
            answer7 = rd007D.getText().toString();
        }

        if (rd008A.isChecked()) {
            answer8 = rd008A.getText().toString();
        }
        else if (rd008B.isChecked()) {
            answer8 = rd008B.getText().toString();
        }

        if (rd009A.isChecked()) {
            answer9 = rd009A.getText().toString();
        }
        else if (rd009B.isChecked()) {
            answer9 = rd009B.getText().toString();
        }

        if (rd010A.isChecked()) {
            answer10 = rd010A.getText().toString();
        }
        else if (rd010B.isChecked()) {
            answer10 = rd010B.getText().toString();
        }

        if (rd011A.isChecked()) {
            answer11 = rd011A.getText().toString();
        }
        else if (rd011B.isChecked()) {
            answer11 = rd011B.getText().toString();
        }

        if (rd012A.isChecked()) {
            answer12 = rd012A.getText().toString();
        }
        else if (rd012B.isChecked()) {
            answer12 = rd012B.getText().toString();
        }

        if (rd013A.isChecked()) {
            answer13 = rd013A.getText().toString();
        }
        else if (rd013B.isChecked()) {
            answer13 = rd013B.getText().toString();
        }

        if (rd014A.isChecked()) {
            answer14 = rd014A.getText().toString();
        }
        else if (rd014B.isChecked()) {
            answer14 = rd014B.getText().toString();
        }

        if (rd015A.isChecked()) {
            answer15A = rd015A.getText().toString();
        }
        else if (rd015B.isChecked()) {
            answer15B = rd015B.getText().toString();
        }
        else if (rd015C.isChecked()) {
            answer15C = rd015C.getText().toString();
        }
        else if (rd015D.isChecked()) {
            answer15D = rd015D.getText().toString();
        }
        else if (rd015E.isChecked()) {
            answer15E = rd015E.getText().toString();
        }
        else if (rd015F.isChecked()) {
            answer15F = rd015F.getText().toString();
        }

        if (rd016A.isChecked()) {
            answer16 = rd016A.getText().toString();
        }
        else if (rd016B.isChecked()) {
            answer16 = rd016B.getText().toString();
        }
        else if (rd016C.isChecked()) {
            answer16 = rd016C.getText().toString();
        }

        if (rd017A.isChecked()) {
            answer17 = rd017A.getText().toString();
        }
        else if (rd017B.isChecked()) {
            answer17 = rd017B.getText().toString();
        }
        else if (rd017C.isChecked()) {
            answer17 = rd017C.getText().toString();
        } else if (rd017D.isChecked()) {
            answer17 = rd017D.getText().toString();
        }

        if (rd018A.isChecked()) {
            answer18 = rd018A.getText().toString();
        }
        else if (rd018B.isChecked()) {
            answer18 = rd018B.getText().toString();
        }
        else if (rd018C.isChecked()) {
            answer18 = rd018C.getText().toString();
        } else if (rd018D.isChecked()) {
            answer18 = rd018D.getText().toString();
        } else if (rd018E.isChecked()) {
            answer18 = rd018E.getText().toString();
        } else if (rd018F.isChecked()) {
            answer18 = rd018F.getText().toString();
        }

        if (rd019A.isChecked()) {
            answer19A = rd019A.getText().toString();
        }
        else if (rd019B.isChecked()) {
            answer19B = rd019B.getText().toString();
        }
        else if (rd019C.isChecked()) {
            answer19C = rd019C.getText().toString();
        }
        else if (rd019D.isChecked()) {
            answer19D = rd019D.getText().toString();
        }
        else if (rd019E.isChecked()) {
            answer19E = rd019E.getText().toString();
        }
        else if (rd019F.isChecked()) {
            answer19F = rd019F.getText().toString();
        }
        else if (rd019G.isChecked()) {
            answer19G = rd019G.getText().toString();
        }
        else if (rd019H.isChecked()) {
            answer19H = rd019H.getText().toString();
        }

        if (rd020A.isChecked()) {
            answer20 = rd020A.getText().toString();
        }
        else if (rd020B.isChecked()) {
            answer20 = rd020B.getText().toString();
        }

        HashMap hashMap = new HashMap();
        hashMap.put("answer1",answer1);
        hashMap.put("answer2",answer2);
        hashMap.put("answer3",answer3);
        hashMap.put("answer4",answer4);
        hashMap.put("answer5",answer5);
        hashMap.put("answer6",answer6);
        hashMap.put("answer7",answer7);
        hashMap.put("answer8",answer8);
        hashMap.put("answer9",answer9);
        hashMap.put("answer10",answer10);
        hashMap.put("answer11",answer11);
        hashMap.put("answer12",answer12);
        hashMap.put("answer13",answer13);
        hashMap.put("answer14",answer14);
        hashMap.put("answer15A",answer15A);
        hashMap.put("answer15B",answer15B);
        hashMap.put("answer15C",answer15C);
        hashMap.put("answer15D",answer15D);
        hashMap.put("answer15E",answer15E);
        hashMap.put("answer15F",answer15F);
        hashMap.put("answer16",answer16);
        hashMap.put("answer17",answer17);
        hashMap.put("answer18",answer18);
        hashMap.put("answer19A",answer19A);
        hashMap.put("answer19B",answer19B);
        hashMap.put("answer19C",answer19C);
        hashMap.put("answer19D",answer19D);
        hashMap.put("answer19E",answer19E);
        hashMap.put("answer19F",answer19F);
        hashMap.put("answer19G",answer19G);
        hashMap.put("answer19H",answer19H);
        hashMap.put("answer20",answer20);
        companyRef.child(post_key).child("Module 6").child("Questions Customer Support").updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                Intent intent = new Intent(getActivity(), CustomerSuportActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });
    }
}
