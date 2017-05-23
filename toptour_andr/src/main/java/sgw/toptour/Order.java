package sgw.toptour;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Order extends Activity {
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_SETPASP = "setpassport";
    Button oform;
    TextView cityofo,numpofo;
    private ProgressDialog pDialog;
    SharedPreferences mSettings, setpasp;
    JSONParser jsonParser = new JSONParser();
    EditText inputtermin;
    EditText inputdate;
    EditText inputzirka;
    EditText inputkilk;
    String pasp;
    String[] cites;
    int i;
    String termin, zirka, date, kod_putivky,kilk_osib;

    private static String url_create_product = "http://toptour.ho.ua/php/query/order.php";

    private static final String TAG_SUCCESS = "success";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        oform=(Button) findViewById(R.id.oform);
        cityofo=(TextView)findViewById(R.id.cityofo);

        numpofo=(TextView)findViewById(R.id.numpofo);
        inputdate=(EditText) findViewById(R.id.date);
        inputtermin=(EditText) findViewById(R.id.termin);
        inputzirka=(EditText) findViewById(R.id.zirka);
        inputkilk=(EditText)findViewById(R.id.kilk_osib);


        setpasp=getSharedPreferences(APP_SETPASP,Context.MODE_PRIVATE);
        if(setpasp.contains(APP_SETPASP)) {
            numpofo.setText(setpasp.getString(APP_SETPASP, ""));
        }
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(mSettings.contains(APP_PREFERENCES)) {
            cityofo.setText(mSettings.getString(APP_PREFERENCES, ""));
        }
        oform.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                termin=inputtermin.getText().toString();
                date=inputdate.getText().toString();
                zirka=inputzirka.getText().toString();
                kilk_osib=inputkilk.getText().toString();
                 pasp = String.valueOf(numpofo);

                new CreateNewProduct().execute();
            }
        });
        Resources res = getResources();
        cites = res.getStringArray(R.array.city);

        for(int k=0;k<cites.length;k++){
            if (cites[k].equals(mSettings.getString(APP_PREFERENCES,"")))
                i=k+1;
        }

        kod_putivky= String.valueOf(i);

    }
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Перед согданием в фоновом потоке показываем прогресс диалог
         **/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Order.this);
            pDialog.setMessage("Создание заказа...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Создание продукта
         **/
        public String doInBackground(String[] args) {





            // Заполняем параметры
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("passport_nomer", pasp));
            params.add(new BasicNameValuePair("kod_putivky", kod_putivky));
            params.add(new BasicNameValuePair("zirk_hotel", zirka));
            params.add(new BasicNameValuePair("kilkist_osib", kilk_osib));
            params.add(new BasicNameValuePair("date_putivka",date));
            params.add(new BasicNameValuePair("termin",termin));

            // получаем JSON объект
            JSONObject json = jsonParser.makeHttpRequest(url_create_product, "POST", params);

            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // продукт удачно создан
                    Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                    startActivity(i);

                    // закрываем это окно
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * После оконачния скрываем прогресс диалог
         **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }

    }



}
