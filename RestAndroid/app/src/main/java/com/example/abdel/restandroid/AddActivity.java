package com.example.abdel.restandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import android.widget.TabHost;
import android.widget.TabHost.*;

import Resume.*;
import Resume.ListeCv;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Abdel on 26/04/2015.
 */
public class AddActivity extends Activity implements OnTabChangeListener {
    final String url = "http://projetweb.herokuapp.com/rest/resume";
    TabHost view;
    Cv cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout);
        view = (TabHost) findViewById(android.R.id.tabhost);
        view.setOnTabChangedListener(this);
        view.setup();
        addTab("infoPerso", "Informations personnel", R.drawable.android, R.id.infoPerso);
        addTab("formation", "Formations", R.drawable.android, R.id.formation);
        addTab("experience_pro", "Experineces Pro", R.drawable.android, R.id.experience_pro);
        addTab("projet", "Projets", R.drawable.android, R.id.projet);
        addTab("competence", "Competence", R.drawable.android, R.id.competence);
        addTab("langue", "Langues", R.drawable.android, R.id.langue);
        addTab("loisir", "Loisirs", R.drawable.android, R.id.loisir);

        /****************** rassemblage des informations ************************/
        String titre = ((EditText)findViewById(R.id.titre)).getText().toString();

        String nom = ((EditText)findViewById(R.id.nom)).getText().toString();
        String prenom = ((EditText)findViewById(R.id.prenom)).getText().toString();
        String adresse = ((EditText)findViewById(R.id.adresse)).getText().toString();
        String tele = ((EditText)findViewById(R.id.tele)).getText().toString();
        Info_perso info_persos = new Info_perso(nom, prenom, adresse, tele);

        String titre1 = ((EditText)findViewById(R.id.titre1)).getText().toString();
        String description1 = ((EditText)findViewById(R.id.description1)).getText().toString();
        String titre2 = ((EditText)findViewById(R.id.titre2)).getText().toString();
        String description2 = ((EditText)findViewById(R.id.description2)).getText().toString();
        Competance c1 = new Competance(titre1, description1);
        Competance c2 = new Competance(titre2, description2);
        LinkedList<Competance> competancesListe = new LinkedList<Competance>();
        competancesListe.add(c1);
        competancesListe.add(c2);
        Competances competances = new Competances(competancesListe);

        String date1 = ((EditText)findViewById(R.id.date1)).getText().toString();
        String expr1 = ((EditText)findViewById(R.id.expr1)).getText().toString();
        String date2 = ((EditText)findViewById(R.id.date2)).getText().toString();
        String expr2 = ((EditText)findViewById(R.id.expr2)).getText().toString();
        Experience_pro exp1 = new Experience_pro(date1, expr1);
        Experience_pro exp2 = new Experience_pro(date2, expr2);
        LinkedList<Experience_pro> experiences_proListe = new LinkedList<Experience_pro>();
        experiences_proListe.add(exp1);
        experiences_proListe.add(exp2);
        Experiences_pro experiences_pro = new Experiences_pro(experiences_proListe);

        String datefr1 = ((EditText)findViewById(R.id.datefr1)).getText().toString();
        String diplome1 = ((EditText)findViewById(R.id.diplome1)).getText().toString();
        String datefr2 = ((EditText)findViewById(R.id.datefr2)).getText().toString();
        String diplome2 = ((EditText)findViewById(R.id.diplome2)).getText().toString();

        Formation formation1 = new Formation(datefr1, diplome1);
        Formation formation2 = new Formation(datefr2, diplome2);
        LinkedList<Formation> formationsListe = new LinkedList<Formation>();
        formationsListe.add(formation1);
        formationsListe.add(formation2);
        Formations formations= new Formations(formationsListe);

        String nom1 = ((EditText)findViewById(R.id.nom1)).getText().toString();
        String niveau1 = ((EditText)findViewById(R.id.niveau1)).getText().toString();
        String nom2 = ((EditText)findViewById(R.id.nom2)).getText().toString();
        String niveau2 = ((EditText)findViewById(R.id.niveau2)).getText().toString();
        Langue l1 = new Langue(nom1, niveau1);
        Langue l2 = new Langue(nom2, niveau2);
        LinkedList<Langue> languesListe = new LinkedList<Langue>();
        languesListe.add(l1);
        languesListe.add(l2);
        Langues langues = new Langues(languesListe);


        String lz1 = ((EditText)findViewById(R.id.lz1)).getText().toString();
        String lz2 = ((EditText)findViewById(R.id.lz2)).getText().toString();
        LinkedList<String> loisirsListe = new LinkedList<String>();
        loisirsListe.add(lz1);
        loisirsListe.add(lz2);
        Loisirs loisirs = new Loisirs(loisirsListe);

        String datep1 = ((EditText)findViewById(R.id.datep1)).getText().toString();
        String sujetp1 = ((EditText)findViewById(R.id.sujetp1)).getText().toString();
        String datep2 = ((EditText)findViewById(R.id.datep2)).getText().toString();
        String sujetp2 = ((EditText)findViewById(R.id.sujetp2)).getText().toString();

        Projet projet1 = new Projet(datep1, sujetp1);
        Projet projet2 = new Projet(sujetp2, datep2);
        LinkedList<Projet> projetsListe = new LinkedList<Projet>();
        projetsListe.add(projet1);
        projetsListe.add(projet2);
        Projets projets = new Projets(projetsListe);

        cv = new Cv(titre, info_persos, formations, experiences_pro, projets, competances, langues, loisirs);

        Button valide = (Button) findViewById(R.id.valide);
        /****************** fin rassemblage des informations ************************/


        valide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println();
                System.out.println("*****************************************************");
                System.out.println("information :: "+cv.getTitre() + " "+ cv.getInfo_perso().getNom() + " "+ cv.getInfo_perso().getPrenom());
                System.out.println("*****************************************************");

                Serializer deserialser=new Persister();

                try {
                    File sdCard = Environment.getDownloadCacheDirectory();
                    File dir = new File (sdCard.getAbsolutePath());
                    File file = new File(dir, "file.xml");

                    deserialser.write(cv, file);

                    new do_POST().execute(file);
                    file.delete();

                    Intent in=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(in);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        });

    }


    public static String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    class do_POST extends AsyncTask<File, Integer,Void> {

        private Exception exception;

        protected Void doInBackground(File... params) {
            postData(params[0]);
            return null ;
        }
    }

    public void postData(File file) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        StringEntity se;
        try {
            se = new StringEntity(getStringFromFile(file.getCanonicalPath()), HTTP.UTF_8);
            se.setContentType("text/xml");
            httppost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
            httppost.setEntity(se);

            BasicHttpResponse httpResponse = (BasicHttpResponse) httpclient .execute(httppost);

            System.out.println(httpResponse.getStatusLine().toString());
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void addTab(String tag, String titre, int icone , int content){
        TabHost.TabSpec spec = view.newTabSpec(tag);
        spec.setIndicator(titre, getResources().getDrawable(icone) );
        spec.setContent(content);
        view.addTab(spec);
    }

    @Override
    public void onTabChanged(String tabId) {

    }

}
