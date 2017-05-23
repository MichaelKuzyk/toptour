package sgw.toptour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Катя on 16.04.2017.
 */
public class PersonalPage  extends Activity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    RelativeLayout content,frame;
    TextView icon,icon1,icon2,icon3;
    TextView chp,chph,chm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalpage);
        chp=(TextView)findViewById(R.id.chp);
        chm=(TextView)findViewById(R.id.chm);
        chph=(TextView)findViewById(R.id.chph);
        chm.setOnClickListener(this);
        chp.setOnClickListener(this);
        chph.setOnClickListener(this);
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
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chph:
                Intent intent=new Intent(this,ChangePhone.class);
                startActivity(intent);
                break;
            case R.id.chp:
                Intent intent1=new Intent(this,ChangePass.class);
                startActivity(intent1);
                break;
            case R.id.chm:
                Intent intent2=new Intent(this,ChangeMail.class);
                startActivity(intent2);
                break;
            case R.id.icon:
                Intent intent3=new Intent(this,Main.class);
                startActivity(intent3);
                break;
            case R.id.icon1:
                Intent intent4=new Intent(this,AllProductsActivity.class);
                startActivity(intent4);
                break;
            case R.id.icon2:
                Intent intent5=new Intent(this,PersonalPage.class);
                startActivity(intent5);
                break;
            case R.id.icon3:
                Intent intent6=new Intent(this,Contact.class);
                startActivity(intent6);
                break;
        }

    }
}
