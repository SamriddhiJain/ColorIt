package com.example.samriddhi.colorit;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Test4 extends ActionBarActivity {

        EditText et_name;
        Button but_submit2;
        @SuppressWarnings("rawtypes")
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_test4);
            Intent r1=getIntent();
            Bundle extra1=r1.getExtras();
            final String Result3=extra1.getString("result3");

            et_name = (EditText) findViewById(R.id.editText2);

            but_submit2 = (Button) findViewById(R.id.button2);

            but_submit2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    String name = et_name.getText().toString();
                    if (name == "" || name.trim().length() == 0 || name == null) {
                        Toast.makeText(getApplicationContext(), "Please Enter Number", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(), "Person with normal vision should see no special number", Toast.LENGTH_SHORT).show();

                        if(name.equals("0")){
                            name="3";
                        }else if(name.equals("2")){
                            name="3";
                            //5: p&d
                        }else{
                            name="4";
                        }
                       // Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();

                        try {
                            Bundle b = new Bundle();
                            b.putString("result4", Result3+name);
                            String pack = getPackageName() + ".Test5";
                            Class cls = Class.forName(pack);
                            Intent i = new Intent(Test4.this, cls);
                            i.putExtras(b);
                            startActivity(i);

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
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
        getMenuInflater().inflate(R.menu.menu_test4, menu);
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
