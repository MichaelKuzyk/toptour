package sgw.toptour;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements OnClickListener {

    private ProgressDialog pDialog;

    // Создаем JSON парсер
    JSONParser jParser = new JSONParser();
    SharedPreferences setpasp;

    // url получения списка всех продуктов
    private static String url_all_login = "http://toptour.ho.ua/php/query/login.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_LOGIN = "login";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PASP="passport_nom";
    public static final String APP_SETPASP = "setpassport";
    private static final String TAG_PASSWORD = "password";
    // тут будет хранится список продуктов
    JSONArray logi = null;

    Button button, button2;
    EditText editText, editText2;
    String login, password,passport_nom;
    int i=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);




        button.setOnClickListener(this);
        button2.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, Registration.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button2:
                login = editText2.getText().toString();
                password = editText.getText().toString();
                new LoadInfo().execute();
                if (i==1)
                {
                Intent intent1 = new Intent(this, Main.class);
                startActivity(intent1);
                    finish();

        }
        else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Невірний логін або пароль", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }

    }


    public class LoadInfo extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Завантаження. Зачекайте...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            // Будет хранить параметры
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // получаем JSON строк с URL
            JSONObject json = jParser.makeHttpRequest(url_all_login, "GET", params);

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
                        if (logi.getJSONObject(i).getString(TAG_PHONE).equals(login)/*&&logi.getJSONObject(i).getString(TAG_PASSWORD).equals(password)*/)
                        {
                            i=1;
                      //     passport_nom= logi.getJSONObject(i).getString(TAG_PASP);
                         //   SharedPreferences.Editor editor = setpasp.edit();
                         //   editor.putString(APP_SETPASP, passport_nom);
                         //   editor.apply();
                    }
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