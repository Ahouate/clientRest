package com.example.abdel.restandroid;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import Resume.ListeCv;

/**
 * Created by Abdel on 26/04/2015.
 */
public class ListViewDActivity extends Activity  {
    final String url = "http://projetweb.herokuapp.com/rest/resume";
    final String ID = "ID";
    public static ListeCv rs;
    private DefaultHttpClient client = new DefaultHttpClient();


    /** Called when the activity is first created. */
    ListView listView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdynamique);
        try {
            rs = new GetCvs().execute().get();
        } catch (Exception e) {
            Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println( "Erreur : "+e.getMessage());
        }

        listView = (ListView) findViewById(R.id.lv_country);
        listView.setAdapter(new EfficientAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in=new Intent(getBaseContext(),OngletActivity.class);
                in.putExtra("idcv",position);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    private static class EfficientAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public EfficientAdapter(Context context) {
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

            holder.text1.setText(rs.getResume().get(position).getTitre());
            holder.text2.setText(rs.getResume().get(position).getInfo_perso().getNom()+" "+rs.getResume().get(position).getInfo_perso().getPrenom());

            return convertView;
        }

        static class ViewHolder {
            TextView text1;
            TextView text2;
        }
    }
}
