package com.example.root.com.example.root.ContactDetail;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.com.example.root.DatabaseHelperClasses.Class_For_A_Positive;
import com.example.root.giveblood.FragmentMainActivity;
import com.example.root.giveblood.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class User_Contact_Detail extends AppCompatActivity {

    public static String final_name,final_blood_Group,final_city,final_Address,final_number;
    Class_For_A_Positive get_User_Data=new Class_For_A_Positive();

    TextView name,city,address,blood,number,testing;
    Button contact_Person,Go_Back;

    private ImageView imageEdit;

    Point p;

    public static String PashaObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__contact__detail);

        Parse.initialize(getApplication(), "Iiav8rG2tGa1ExUQZiuYEC0c3I54v1d29RSS1tZS", "W5GaHHTdR18tEK2SGmDgFWcdPOD1savHG4zwS4e1");

        name=(TextView)findViewById(R.id.tv_User_Final_name);
        city=(TextView)findViewById(R.id.tv_User_Final_City);
        address=(TextView)findViewById(R.id.tv_User_Final_Address);
        blood=(TextView)findViewById(R.id.tv_User_Final_Blood_Group);
        number=(TextView)findViewById(R.id.tv_User_Final_Phone_Number);

        testing=(TextView)findViewById(R.id.test1);


        contact_Person=(Button)findViewById(R.id.btn_Make_Contact);
        Go_Back=(Button)findViewById(R.id.btn_go_back);

        imageEdit=(ImageView) findViewById(R.id.imageEdit);
        imageEdit.setVisibility(View.INVISIBLE);

        contact_Person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();
            }
        });

        Go_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User_Contact_Detail.this,FragmentMainActivity.class);
                startActivity(i);
                finish();
            }
        });
        refreshPostList();
//        final_name=get_User_Data.get_name();
//        final_blood_Group=get_User_Data.get_blood_group();
//        final_city=get_User_Data.get_User_city();
//        final_Address=get_User_Data.get_address();
//        final_number=get_User_Data.getphone_Number();

    }
    private void refreshPostList() {
       final ProgressDialog progressDialog = new ProgressDialog(User_Contact_Detail.this,R.style.AppTheme_Dark_Dialog);
       // final ProgressDialog progressDialog  = new ProgressDialog(getApplication());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait while Activity Is being Refreshed");
        progressDialog.show();
        // Class Name = Post
        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("BloodBank");

        // attribute Author
        query.whereEqualTo("Blood_Group", get_User_Data.get_blood_group());
        query.whereEqualTo("Donor_Name", get_User_Data.get_name());


        // setProgressBarIndeterminateVisibility(true);

        query.findInBackground(new FindCallback<ParseObject>() {

            @SuppressWarnings("unchecked")
            @Override
            public void done(List<ParseObject> postList, ParseException e) {

                //setProgressBarIndeterminateVisibility(false);
                if (e == null) {
                    Toast.makeText(getApplication(), "Data Found!", Toast.LENGTH_LONG).show();
                    // If there are results, update the list of posts
                    // and notify the adapter

                    for (ParseObject post : postList) {
//                        Note note = new Note(post.getString("Donor_Name"), post.getString("Blood_Group"), post.getString("Phone_Number"),
//                                post.getString("City"), post.getString("Address"), post.getObjectId());
                      final_name=post.getString("Donor_Name");
                        final_blood_Group=post.getString("Blood_Group");
                        final_number=post.getString("Phone_Number");
                        final_Address=post.getString("Address");
                        final_city=post.getString("City");
                        ParseFile fileObject = (ParseFile) post.get("ImageFile");
                        /////////////////////////////////////////
                        try{
                            fileObject.getDataInBackground(new GetDataCallback() {

                                public void done(byte[] data,ParseException e) {
                                    if (e == null) {
                                        Log.d("test", "We've got data in data.");
                                        Toast.makeText(getApplicationContext(), "We've got data in data.", Toast.LENGTH_SHORT).show();

                                        // Decode the Byte[] into
                                        // Bitmap
                                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        // Get the ImageView from
                                        // main.xml

                                        // Set the Bitmap into the
                                        // ImageView
                                        imageEdit.setVisibility(View.VISIBLE);
                                        imageEdit.setImageBitmap(bmp);
                                        // Close progress dialog
                                        progressDialog.dismiss();

                                    } else {
                                        Log.d("test", "There was a problem downloading the data.");
                                        Toast.makeText(getApplicationContext(), "There was a problem downloading the data.", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }

                                }
                            });
                        }
                        catch(Exception ex){
                            progressDialog.dismiss();
                        }
                        ///////////////////////////////////
                        name.setText(final_name);
                        city.setText(final_city);
                        address.setText(final_Address);
                        blood.setText(final_blood_Group);
                        number.setText(final_number);
//                        Toast.makeText(getActivity(), "Test Name =  "+TestNames,
//                                Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();



                } else {

                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    // Toast.makeText(getBaseContext(),  "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplication(), "Error Retrieving Data! Please Check your internet Connection ",
                            Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
              //  gettingImage();
            }

        });

    }
    private void notification() {
        //  mp.setLooping(true);
        //mp.start();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Options"); // Set Alert dialog title
        // here
        alert.setMessage("Do you Want to send Message or Make a Call to this Person?");

        alert.setPositiveButton("Message", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
//                if (p != null)
//                    showPopup(User_Contact_Detail.this, p);
                MessageBox();
            }
        });
        alert.setNeutralButton("Call", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+final_number));
                startActivity(callIntent);
            }
        });
//        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                Intent i = new Intent(User_Contact_Detail.this, User_Contact_Detail.class);
//                startActivity(i);
//                finish();
//            }
//        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
    public void MessageBox(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
       // new AlertDialog.Builder(mContext, R.style.MyCustomDialogTheme);

        final EditText edittext = new EditText(this);
        alert.setMessage("Enter Your Message");
        alert.setTitle("Contact Information");

        alert.setView(edittext);

        alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                // Editable YouEditTextValue = edittext.getText();
                //OR
                String YouEditTextValue = edittext.getText().toString();
                sendMessage(final_number,YouEditTextValue);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();

    }
    protected void sendMessage(String theNum, String myMsg) {
        final ProgressDialog progressDialog = new ProgressDialog(User_Contact_Detail.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        String sent= "Message Send";
        String Delivered= "Message Delivered";
        PendingIntent sentPI= PendingIntent.getBroadcast(this, 0, new Intent(sent), 0);
        PendingIntent deliverPI=PendingIntent.getBroadcast(this, 0, new Intent(Delivered), 0);
        registerReceiver(new BroadcastReceiver() {

            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(User_Contact_Detail.this, "SMS Sent", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        notification();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic Failure", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        break;


                }
            }
        }, new IntentFilter(sent));

        registerReceiver(new BroadcastReceiver() {

            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(User_Contact_Detail.this, "SMS Delivered", Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS Not Delivered", Toast.LENGTH_LONG).show();
                        break;


                }
            }
        }, new IntentFilter(Delivered));

        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage(theNum, null, myMsg, sentPI, deliverPI);
        // Toast.makeText(getBaseContext(), "Message sended ", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
      //  Button button = (Button) findViewById(R.id.show_popup);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.

      //  button.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    // The method that displays the popup.
    private void showPopup(final Activity context, Point p) {
        int popupWidth = 800;
        int popupHeight = 600;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 200;
        int OFFSET_Y = 600;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    ///////////////Download Image ////////////////////////
    public void gettingImage(){
        testing.setText("Please Wait Bro");
        final ProgressDialog progressDialog = new ProgressDialog(User_Contact_Detail.this,R.style.AppTheme_Dark_Dialog);
        // final ProgressDialog progressDialog  = new ProgressDialog(getApplication());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait while Image is Being Downloaded");
        progressDialog.show();
        // Locate the class table named "ImageUpload" in Parse.com
        ParseQuery<ParseObject> query = ParseQuery.getQuery("BloodBank");

        // attribute Author
        query.whereEqualTo("Blood_Group", get_User_Data.get_blood_group());
        query.whereEqualTo("Donor_Name", get_User_Data.get_name());

        // Locate the objectId from the class
        query.getInBackground(final_name,new GetCallback<ParseObject>() {

            public void done(ParseObject object,ParseException e) {
                // Locate the column named "ImageName" and set
                // the string
                try{
                    ParseFile fileObject = (ParseFile) object.get("ImageFile");
                    fileObject.getDataInBackground(new GetDataCallback() {

                        public void done(byte[] data,ParseException e) {
                            if (e == null) {
                                Log.d("test", "We've got data in data.");
                                Toast.makeText(getApplicationContext(), "We've got data in data.", Toast.LENGTH_SHORT).show();

                                // Decode the Byte[] into
                                // Bitmap
                                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                // Get the ImageView from
                                // main.xml

                                // Set the Bitmap into the
                                // ImageView
                                imageEdit.setVisibility(View.VISIBLE);
                                imageEdit.setImageBitmap(bmp);
                                // Close progress dialog
                                progressDialog.dismiss();
                                testing.setText("Done Yaar");

                            } else {
                                Log.d("test", "There was a problem downloading the data.");
                                Toast.makeText(getApplicationContext(), "There was a problem downloading the data.", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    });
                }
                catch(Exception ex){
                    progressDialog.dismiss();
                }
            }
        });
    }
    ////////////////// Download Image  ////////////////////////

}
