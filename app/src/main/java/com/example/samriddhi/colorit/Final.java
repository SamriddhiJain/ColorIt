package com.example.samriddhi.colorit;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.graphics.BitmapFactory;
import android.widget.Toast;


public class Final extends ActionBarActivity {

   ImageView  imgFavorite;
    Bitmap bp;
    Button button1,button2,button3,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent r1=getIntent();
        Bundle extra1=r1.getExtras();
        final String Result=extra1.getString("result5");

        button1 = (Button) findViewById(R.id.red);
        button2 = (Button) findViewById(R.id.green);
        button3 = (Button) findViewById(R.id.blue);

        final TextView Result_msg = (TextView) findViewById(R.id.resultText);
        //Toast.makeText(getApplicationContext(), Result, Toast.LENGTH_LONG).show();
        if(!Result.equals("")) {
            if (Result.equals("33333")) {
                Result_msg.setText("You have normal vision");
            } else if (Result.charAt(2) == '0') {
                Result_msg.setText("You have protanopia or red-blindness");
            } else if (Result.charAt(2) == '1') {
                Result_msg.setText("You have deuteranopia or green-blindness");
            } else {
                Result_msg.setText("Can't be resolved, most likely it is red-green blindness");
            }
        }

        bp = BitmapFactory.decodeResource(getResources(),R.drawable.t);


        imgFavorite = (ImageView) findViewById(R.id.finalimage1);
        imgFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Result_msg.setText("");
                final int REQUEST_IMAGE_CAPTURE = 1;

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }




        } );


        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ColorC redfilter=new ColorC(bp,0);
                Bitmap timage=redfilter.colorCover();

                imgFavorite.setImageBitmap(timage);

                Result_msg.setText("Red Filtered Image \n (Tap the image to retry)");

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ColorC greenfilter=new ColorC(bp,1);
                Bitmap timage=greenfilter.colorCover();

                imgFavorite.setImageBitmap(timage);

                Result_msg.setText("Green Filtered Image\n" +
                        " (Tap the image to retry)");

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ColorC bluefilter=new ColorC(bp,2);
                Bitmap timage=bluefilter.colorCover();

                imgFavorite.setImageBitmap(timage);

                Result_msg.setText("Blue Filtered Image\n" +
                        " (Tap the image to retry)");

            }
        });

       /* exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Final.this.finish();
                setResult(100);

            }
        });*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(resultCode==RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            bp = (Bitmap) data.getExtras().get("data");
            imgFavorite.setImageBitmap(bp);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final, menu);
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
