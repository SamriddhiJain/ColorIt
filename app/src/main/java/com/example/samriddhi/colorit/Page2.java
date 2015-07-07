package com.example.samriddhi.colorit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;


public class Page2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String name = extras.getString("name");
        String msg = name;
        Button but_submit;
        TextView TextView_msg = (TextView) findViewById(R.id.Welcomemsg);
        TextView_msg.setText(msg);

        but_submit = (Button) findViewById(R.id.button3);
        but_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    //Bundle b=new Bundle();
                    //	b.putString("name",name);
                    String pack = getPackageName() + ".Test1";
                    Class cls = Class.forName(pack);
                    Intent i = new Intent(Page2.this, cls);
                    //	i.putExtras(b);
                    startActivity(i);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }

        });

        Button startCamera = (Button) findViewById(R.id.camera);
        startCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    Bundle b=new Bundle();
                    b.putString("result5","");
                    String pack = getPackageName() + ".Final";
                    Class cls = Class.forName(pack);
                    Intent i = new Intent(Page2.this, cls);
                    i.putExtras(b);
                    startActivity(i);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }

        });

        Button instructions = (Button) findViewById(R.id.instruct);
        instructions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    String pack = getPackageName() + ".Instructions";
                    Class cls = Class.forName(pack);
                    Intent i = new Intent(Page2.this, cls);
                    startActivity(i);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                setResult(requestCode);
                this.finish();
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page2, menu);
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
}
