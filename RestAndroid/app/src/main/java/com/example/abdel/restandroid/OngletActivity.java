package com.example.abdel.restandroid;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ListView;
        import android.widget.TabHost;
        import android.widget.TabHost.*;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.HttpStatus;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.util.EntityUtils;
        import org.simpleframework.xml.Serializer;
        import org.simpleframework.xml.core.Persister;
        import org.springframework.web.client.RestTemplate;

        import java.io.IOException;
        import java.io.Reader;
        import java.io.StringReader;
        import java.util.LinkedList;

        import Resume.Cv;
        import Resume.ListeCv;

public class OngletActivity extends Activity implements OnTabChangeListener {
    final String url = "http://projetweb.herokuapp.com/rest/resume";
    final String ID = "ID";
    RestTemplate restTemplate;
    public static ListeCv rs;
    private DefaultHttpClient client = new DefaultHttpClient();
    TabHost view;
    ListView listView;
    public static Cv cv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.detail);
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

            Intent in=getIntent();
            int id=in.getIntExtra("idcv",0);
            try {
                rs = new GetCvs().execute().get();
                cv = rs.getResume().get(id);
                /**********************info***********************/
                listView = (ListView) findViewById(R.id.lv_infos);
                listView.setAdapter(new EfficientAdapterInfo(this));

                /**********************comeptence***********************/
                listView = (ListView) findViewById(R.id.lv_competence);
                listView.setAdapter(new EfficientAdapterCompetence(this));

                /**********************experience_pro***********************/
                listView = (ListView) findViewById(R.id.lv_experience);
                listView.setAdapter(new EfficientAdapterExperience(this));

                /**********************formation***********************/
                listView = (ListView) findViewById(R.id.lv_formation);
                listView.setAdapter(new EfficientAdapterFormation(this));

                /**********************langue***********************/
                listView = (ListView) findViewById(R.id.lv_langue);
                listView.setAdapter(new EfficientAdapterLangue(this));

                /**********************Loisirs***********************/
                listView = (ListView) findViewById(R.id.lv_loisir);
                listView.setAdapter(new EfficientAdapterLoisir(this));

                /**********************projet***********************/
                listView = (ListView) findViewById(R.id.lv_Projet);
                listView.setAdapter(new EfficientAdapterprojet(this));


            } catch (Exception e) {
                Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println( "Erreur : "+e.getMessage());
            }

        }

    private void addTab(String tag, String titre, int icone , int content){
        TabSpec spec = view.newTabSpec(tag);
        spec.setIndicator(titre, getResources().getDrawable(icone) );
        spec.setContent(content);
        view.addTab(spec);
    }

    @Override
    public void onTabChanged(String tabId) {
        Toast.makeText(this, tabId, Toast.LENGTH_SHORT);
    }

    private class GetCvs extends AsyncTask<String, Void, ListeCv> {
        @Override
        protected ListeCv doInBackground(String... params) {
            ListeCv cvs = new ListeCv();
            String xmlData = retrieve(url);
            Serializer serializer = new Persister();
            Reader reader = new StringReader(xmlData);
            try {
                cvs= serializer.read(ListeCv.class, reader, false);
            } catch (Exception e) {
                System.out.println( "Erreur : "+e.getMessage());
            }
            Log.d(MainActivity.class.getSimpleName(), cvs.toString());

            return cvs;
        }

    }

    public String retrieve(String url) {
        HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity getResponseEntity = getResponse.getEntity();
            if (getResponseEntity != null) {
                return EntityUtils.toString(getResponseEntity);
            }
        }
        catch (IOException e) {
            getRequest.abort();
            Log.w(getClass().getSimpleName(), "URL erroné " + url, e);
        }
        return null;
    }

    /****************************************************************************************/
    private static class EfficientAdapterInfo extends BaseAdapter {
        private LayoutInflater mInflater;

        public EfficientAdapterInfo(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return 4;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.two_col_row, null);
                holder = new ViewHolder();
                holder.text1 = (TextView) convertView
                        .findViewById(R.id.TextView01);
                holder.text2 = (TextView) convertView
                        .findViewById(R.id.TextView02);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            LinkedList<String> names = new LinkedList<String>();
            LinkedList<String> values = new LinkedList<String>();
            if(position==0){
                holder.text1.setText("Nom");
                holder.text2.setText(cv.getInfo_perso().getNom());
            }
            if(position==1){
                holder.text1.setText("Prenom");
                holder.text2.setText(cv.getInfo_perso().getPrenom());
            }
            if(position==2){
                holder.text1.setText("Adresse");
                holder.text2.setText(cv.getInfo_perso().getAdresse());
            }
            if(position==3){
                holder.text1.setText("Téléphone");
                holder.text2.setText(cv.getInfo_perso().getTele());
            }

            return convertView;
        }

        static class ViewHolder {
            TextView text1;
            TextView text2;
        }
    }

    private static class EfficientAdapterCompetence extends BaseAdapter {
        private LayoutInflater mInflater;

        public EfficientAdapterCompetence(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return cv.getCompetances().getCompetance().size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.two_col_row, null);
                holder = new ViewHolder();
                holder.text1 = (TextView) convertView
                        .findViewById(R.id.TextView01);
                holder.text2 = (TextView) convertView
                        .findViewById(R.id.TextView02);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text1.setText(cv.getCompetances().getCompetance().get(position).getTitre());
            holder.text2.setText(cv.getCompetances().getCompetance().get(position).getDescription());

            return convertView;
        }

        static class ViewHolder {
            TextView text1;
            TextView text2;
        }
    }


    private static class EfficientAdapterExperience extends BaseAdapter {
        private LayoutInflater mInflater;

        public EfficientAdapterExperience(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return rs.getResume().size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.two_col_row, null);
                holder = new ViewHolder();
                holder.text1 = (TextView) convertView
                        .findViewById(R.id.TextView01);
                holder.text2 = (TextView) convertView
                        .findViewById(R.id.TextView02);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text1.setText(cv.getExperiences_pro().getExperience_pro().get(position).getDate());
            holder.text2.setText(cv.getExperiences_pro().getExperience_pro().get(position).getExperience());

            return convertView;
        }

        static class ViewHolder {
            TextView text1;
            TextView text2;
        }
    }



    private static class EfficientAdapterFormation extends BaseAdapter {
        private LayoutInflater mInflater;

        public EfficientAdapterFormation(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return rs.getResume().size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.two_col_row, null);
                holder = new ViewHolder();
                holder.text1 = (TextView) convertView
                        .findViewById(R.id.TextView01);
                holder.text2 = (TextView) convertView
                        .findViewById(R.id.TextView02);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text1.setText(cv.getFormations().getFormation().get(position).getDate());
            holder.text2.setText(cv.getFormations().getFormation().get(position).getDiplome());

            return convertView;
        }

        static class ViewHolder {
            TextView text1;
            TextView text2;
        }
    }

    private static class EfficientAdapterLangue extends BaseAdapter {
        private LayoutInflater mInflater;

        public EfficientAdapterLangue(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return rs.getResume().size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.two_col_row, null);
                holder = new ViewHolder();
                holder.text1 = (TextView) convertView
                        .findViewById(R.id.TextView01);
                holder.text2 = (TextView) convertView
                        .findViewById(R.id.TextView02);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text1.setText(cv.getLangues().getLangue().get(position).getNom());
            holder.text2.setText(cv.getLangues().getLangue().get(position).getNiveau());
            return convertView;
        }

        static class ViewHolder {
            TextView text1;
            TextView text2;
        }
    }



    private static class EfficientAdapterLoisir extends BaseAdapter {
        private LayoutInflater mInflater;

        public EfficientAdapterLoisir(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return rs.getResume().size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.two_col_row, null);
                holder = new ViewHolder();
                holder.text1 = (TextView) convertView
                        .findViewById(R.id.TextView01);
                holder.text2 = (TextView) convertView
                        .findViewById(R.id.TextView02);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //holder.text1.setText(cv.);
            holder.text2.setText(cv.getLoisirs().getLoisir().get(position));

            return convertView;
        }

        static class ViewHolder {
            TextView text1;
            TextView text2;
        }
    }


    private static class EfficientAdapterprojet extends BaseAdapter {
        private LayoutInflater mInflater;

        public EfficientAdapterprojet(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return rs.getResume().size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.two_col_row, null);
                holder = new ViewHolder();
                holder.text1 = (TextView) convertView
                        .findViewById(R.id.TextView01);
                holder.text2 = (TextView) convertView
                        .findViewById(R.id.TextView02);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text1.setText(cv.getProjets().getProjet().get(position).getDate());
            holder.text2.setText(cv.getProjets().getProjet().get(position).getSujet());

            return convertView;
        }

        static class ViewHolder {
            TextView text1;
            TextView text2;
        }
    }

}
