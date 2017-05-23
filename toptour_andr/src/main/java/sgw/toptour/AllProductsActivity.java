package sgw.toptour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
/**
 * Created by Катя on 20.04.2017.
 */
public class AllProductsActivity extends ListActivity implements View.OnClickListener{
    private ProgressDialog pDialog;
    DrawerLayout mDrawerLayout;
    RelativeLayout content,frame;
    public static final String APP_PREFERENCES = "mysettings";
    SharedPreferences mSettings;
    // Создаем JSON парсер
    JSONParser jParser = new JSONParser();
    TextView icon,icon1,icon2,icon3;
    ArrayList<HashMap<String, String>> productsList;

    // url получения списка всех продуктов
    private static String url_all_products = "http://toptour.ho.ua/php/query/pricelist.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PUTIVKA = "putivka";
    private static final String TAG_KODPUTIV = "kod_putiv";
  //  private static final String TAG_CITY = "city";
  String[] cites;
    Intent in;
    private static final String TAG_PRICE="price";
    // тут будет хранится список продуктов
    JSONArray products = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtour);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        content = (RelativeLayout) findViewById(R.id.content);
        frame = (RelativeLayout) findViewById(R.id.frame);

        icon=(TextView) findViewById(R.id.icon);
        icon1=(TextView) findViewById(R.id.icon1);
        icon2=(TextView) findViewById(R.id.icon2);
        icon3=(TextView) findViewById(R.id.icon3);
        icon.setOnClickListener(this);
        icon1.setOnClickListener(this);
        icon2.setOnClickListener(this);
        icon3.setOnClickListener(this);
        Resources res = getResources();
        cites = res.getStringArray(R.array.city);






        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();

        // Загружаем продукты в фоновом потоке
        new LoadAllProducts().execute();
         in = new Intent(this, Order.class);
        // получаем ListView
        ListView lv = getListView();
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        // на выбор одного продукта
        // запускается заказ продукта с автоматическим вносом города, который выбран
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                        .toString();
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES, pid);
                editor.apply();
                startActivity(in);
            }
        });

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon:
                Intent intent=new Intent(this,Main.class);
                startActivity(intent);
                break;
            case R.id.icon1:
                Intent intent1=new Intent(this,AllProductsActivity.class);
                startActivity(intent1);
                break;
            case R.id.icon2:
                Intent intent2=new Intent(this,PersonalPage.class);
                startActivity(intent2);
                break;
            case R.id.icon3:
                Intent intent3=new Intent(this,Contact.class);
                startActivity(intent3);
                break;
        }
    }

    public class LoadAllProducts extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllProductsActivity.this);
            pDialog.setMessage("Загрузка продуктов. Подождите...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            // Будет хранить параметры
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // получаем JSON строк с URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

         Log.d("All Products: ", json.toString());

            if (json==null){
                Log.e("json", "Не удалось получить никаких данных из адреса null");
            }
            try {
                // Получаем SUCCESS тег для проверки статуса ответа сервера
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // продукт найден
                    // Получаем масив из Продуктов
                    products = json.getJSONArray(TAG_PUTIVKA);

                    // перебор всех продуктов
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Сохраняем каждый json елемент в переменную
                        String id = c.getString(TAG_KODPUTIV);

                        String price = c.getString(TAG_PRICE);

                        // Создаем новый HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        for (int k=0;k<cites.length;k++){

                            map.put(TAG_KODPUTIV, cites[Integer.parseInt(id)-1]);
                        }

                        // добавляем каждый елемент в HashMap ключ => значение


                        map.put(TAG_PRICE,price);

                        // добавляем HashList в ArrayList
                        productsList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * После завершения фоновой задачи закрываем прогрес диалог
         * **/
        protected void onPostExecute(String file_url) {
            // закрываем прогресс диалог после получение все продуктов
            pDialog.dismiss();
            // обновляем UI форму в фоновом потоке
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Обновляем распарсенные JSON данные в ListView
                     * */


                    ListAdapter adapter = new SimpleAdapter(
                            AllProductsActivity.this, productsList,
                            R.layout.list_item, new String[] {TAG_KODPUTIV,
                            TAG_PRICE},
                            new int[] { R.id.pid, R.id.price });
                    // обновляем listview
                    setListAdapter(adapter);
                }
            });

        }

    }

}
