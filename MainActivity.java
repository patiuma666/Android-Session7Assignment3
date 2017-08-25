package com.example.iis5.imageviewfromgallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity { //main activity
    ImageView imageView;
    Button button;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView); //invoked the properties of xml
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() { //implemented onclicklistner

            @Override
            public void onClick(View view) { //by implementing onclick method and declaring the methods in it
                openGallery();

            }
        });
    }

    private void openGallery() {           //by using intents here it starts the activity
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data); //after choosing an image from gallery to view the image in imageview
        if (resultCode == RESULT_OK)
            switch (requestCode){
                case PICK_IMAGE:
                    Uri selectedImage = data.getData(); //we use the get data method
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        imageView.setImageBitmap(bitmap); //if any exceptions catch handle those by using bitmap
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
        }


}