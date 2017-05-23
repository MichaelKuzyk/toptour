package sgw.toptour;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Катя on 16.04.2017.
 */
public class ChangeMail  extends Activity  {

    private ProgressDialog pDialog;

    // Создаем JSON парсер
    JSONParser jParser = new JSONParser();


    // url получения списка всех продуктов
    private static String url_changemail= "http://toptour.ho.ua/php/query/change_email.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_LOGIN = "login";
    private static final String TAG_PHONE = "phone";

    private static final String TAG_PASSWORD = "password";
    // тут будет хранится список продуктов
    JSONArray logi = null;

    Button button, button2;
    EditText editText, editText2;
    String login, password;
    int i=0;






    EditText inputemail,inputnewemail;
    String email,newemail;
    Button change;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changemail);
        inputemail=(EditText) findViewById(R.id.email);
        inputnewemail=(EditText) findViewById(R.id.newemail);
        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                newemail = inputnewemail.getText().toString();
                email=inputemail.getText().toString();

                new ChangingMail().execute();
            }
        });
    }




    public class ChangingMail extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            // Будет хранить параметры
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // получаем JSON строк с URL
            JSONObject json = jParser.makeHttpRequest(url_changemail, "GET", params);

            Log.d("LOGIN: ", json.toString());

            if (json == null) {
                Log.e("json", "Не удалось получить никаких данных из адреса null");
            }
            try {
                // Получаем SUCCESS тег для проверки статуса ответа сервера
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // продукт найден
                    // Получаем масив из Продуктов
                    logi = json.getJSONArray(TAG_LOGIN);
                    for (int i = 0; i < logi.length(); i++) {
                        if (logi.getJSONObject(i).getString(TAG_PHONE).equals(login)&&logi.getJSONObject(i).getString(TAG_PASSWORD).equals(password))
                            i=1;


                    }
                    // перебор всех продуктов

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * После завершения фоновой задачи закрываем прогрес диалог
         **/
        protected void onPostExecute(String file_url) {
            // закрываем прогресс диалог после получение все продуктов
            pDialog.dismiss();
            // обновляем UI форму в фоновом потоке


        }

    }

    }
