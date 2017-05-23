package sgw.toptour;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Registration extends Activity {

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputpib;
    EditText inputpass;
    EditText inputpass1;
    EditText inputbirthday;

    public static final String APP_SETPASP = "setpassport";
    SharedPreferences setpasp;
    EditText inputpassport;
    EditText inputmail;
    EditText inputphone;
    Button reg;
    String pib,pass,pass1,passport,phone,mail,birthday;

    private static String url_create_klient = "http://toptour.ho.ua/php/query/create_klient.php";

    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        inputmail=(EditText) findViewById(R.id.mail);
        inputpass=(EditText) findViewById(R.id.pass);
        inputpass1=(EditText) findViewById(R.id.pass1);
        inputpassport=(EditText) findViewById(R.id.passport);
        inputpib=(EditText) findViewById(R.id.pib);
        inputphone=(EditText) findViewById(R.id.phone);
        inputbirthday=(EditText) findViewById(R.id.birthday);



        reg = (Button) findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pib = inputpib.getText().toString();
                pass = inputpass.getText().toString();
                passport = inputpassport.getText().toString();
                phone = inputphone.getText().toString();
                mail = inputmail.getText().toString();
                birthday=inputbirthday.getText().toString();
                pass1=inputpass1.getText().toString();

                if (pass.equals(pass1)){
                new CreateNewKlient().execute();
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Невірний повторний пароль", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    /**
     * Фоновый Async Task создания нового продукта
     **/
    class CreateNewKlient extends AsyncTask<String, String, String> {

        /**
         * Перед согданием в фоновом потоке показываем прогресс диалог
         **/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Registration.this);
            pDialog.setMessage("Создание клиента...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Создание продукта
         **/
        protected String doInBackground(String[] args) {



            // Заполняем параметры
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("passport_nom", passport));
            params.add(new BasicNameValuePair("password", pass));
            params.add(new BasicNameValuePair("pib_kl", pib));
            params.add(new BasicNameValuePair("birthday",birthday));
            params.add(new BasicNameValuePair("phone",phone));
            params.add(new BasicNameValuePair("email",mail));

            // получаем JSON объект
            JSONObject json = jsonParser.makeHttpRequest(url_create_klient, "POST", params);

            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // продукт удачно создан
                    Intent i = new Intent(getApplicationContext(),Main.class);
                    startActivity(i);
                    SharedPreferences.Editor editor = setpasp.edit();
                    editor.putString(APP_SETPASP, passport);
                    editor.apply();
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