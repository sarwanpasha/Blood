package com.example.root.giveblood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.root.com.example.root.ContactDetail.User_Contact_Detail;
import com.example.root.com.example.root.DatabaseHelperClasses.Class_For_A_Positive;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewFragment_for_B_negative extends Fragment {

    private AutoLabelUI mAutoLabel;
    private List<Person> mPersonList;
    private MyRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    private List<Note> posts;

    public static List <String> TestNames=new ArrayList<String>();
    public static List <String> TestBloodGroup=new ArrayList<String>();
    public static List <String> TestCity=new ArrayList<String>();

    Class_For_A_Positive set_A_Positive_Values = new Class_For_A_Positive();

    public static RecyclerViewFragment_for_B_negative newInstance() {
        return new RecyclerViewFragment_for_B_negative();
    }

    public RecyclerViewFragment_for_B_negative() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycler_view_fragment_for__b_negative, container, false);

        Parse.initialize(getActivity(), "Iiav8rG2tGa1ExUQZiuYEC0c3I54v1d29RSS1tZS", "W5GaHHTdR18tEK2SGmDgFWcdPOD1savHG4zwS4e1");
        posts = new ArrayList<Note>();
        refreshPostList();
        findViews(view);
        setListeners();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null){
            List<Person> people = savedInstanceState.getParcelableArrayList("statePeople");
            if(people != null){
                mPersonList = people;
                adapter.setPeople(people);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    private void itemListClicked(int position) {
        Person person = mPersonList.get(position);
        boolean isSelected = person.isSelected();
        boolean success;
        if (isSelected) {
            success = mAutoLabel.removeLabel(position);
        } else {
           // success = mAutoLabel.addLabel(person.getName(), position);

            set_A_Positive_Values.set_Person_Name(person.getName());
            set_A_Positive_Values.set_blood_group("B-");

            Intent i = new Intent(getActivity(),User_Contact_Detail.class);
            startActivity(i);
        }
//        if (success) {
//            adapter.setItemSelected(position, !isSelected);
//        }
    }

    private void setListeners() {
        mAutoLabel.setOnLabelsCompletedListener(new AutoLabelUI.OnLabelsCompletedListener() {
            @Override
            public void onLabelsCompleted() {
                Toast.makeText(getActivity(), "Completed!", Toast.LENGTH_SHORT).show();
            }
        });

        mAutoLabel.setOnRemoveLabelListener(new AutoLabelUI.OnRemoveLabelListener() {
            @Override
            public void onRemoveLabel(View view, int position) {
                adapter.setItemSelected(position, false);
            }
        });

        mAutoLabel.setOnLabelsEmptyListener(new AutoLabelUI.OnLabelsEmptyListener() {
            @Override
            public void onLabelsEmpty() {
                Toast.makeText(getActivity(), "EMPTY!", Toast.LENGTH_SHORT).show();
            }
        });

        mAutoLabel.setOnLabelClickListener(new AutoLabelUI.OnLabelClickListener() {
            @Override
            public void onClickLabel(View v) {
                Toast.makeText(getActivity(), ((Label) v).getText() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findViews(View view) {
        mAutoLabel = (AutoLabelUI) view.findViewById(R.id.label_view);
        mAutoLabel.setBackgroundResource(R.drawable.round_corner_background);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        mPersonList = new ArrayList<>();

        //Populate list
        // List<String> names = Arrays.asList(getResources().getStringArray(R.array.names));
        List <String> names=new ArrayList<String>();
        //  List<String> ages = Arrays.asList(getResources().getStringArray(R.array.ages));
        List <String> ages=new ArrayList<String>();
        TypedArray photos = getResources().obtainTypedArray(R.array.photos);

//        names.add("Sarwan");
//        names.add("Pasha");
//        names.add("Eduardo Centeno");
//        names.add("Tylos");
//        names.add("Elisa Pastor");
//        names.add("Lurecas");
//        names.add("Carlos Romero");
//        names.add("JAMM");
//        names.add("Juan Carlos Domínguez");
//        names.add("Pablo Domínguez");

//        ages.add("28 years old");
//        ages.add("28 years old");
//        ages.add("28 years old");
//        ages.add("28 years old");
//        ages.add("28 years old");
//        ages.add("28 years old");
//        ages.add("28 years old");
//        ages.add("28 years old");
//        ages.add("28 years old");
//        ages.add("28 years old");

//        for(int i = 0; i<names.size(); i++){
//            mPersonList.add(new Person(names.get(i), ages.get(i), photos.getResourceId(i, -1), false));
//        }

//        for(int i = 0; i<TestNames.size(); i++){
//            mPersonList.add(new Person(TestNames.get(i), ages.get(i), photos.getResourceId(i, -1), false));
//            //  Toast.makeText(getActivity(), "Test Name =  "+TestNames,Toast.LENGTH_LONG).show();
//        }
//        TestNames.clear();
        for(int i = 0; i<TestNames.size(); i++){
            mPersonList.add(new Person(TestNames.get(i), "Blood Group : "+TestBloodGroup.get(i),
                    photos.getResourceId(i, -1),"City :"+TestCity.get(i), false));
            //  Toast.makeText(getActivity(), "Test Name =  "+TestNames,Toast.LENGTH_LONG).show();
        }
        TestNames.clear();
        TestBloodGroup.clear();
        TestCity.clear();

        photos.recycle();

        adapter = new MyRecyclerAdapter(mPersonList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                itemListClicked(position);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
      //  outState.putParcelableArrayList("statePeople",(ArrayList<? extends Parcelable>) adapter.getPeople());
        try {
            outState.putParcelableArrayList("statePeople", (ArrayList<? extends Parcelable>) adapter.getPeople());
        }
        catch(Exception ex){

        }
    }
    private void refreshPostList() {
        // final ProgressDialog progressDialog = new ProgressDialog(RecyclerViewFragment.this,R.style.AppTheme_Dark_Dialog);
        final ProgressDialog progressDialog  = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait while Activity Is being Refreshed");
        progressDialog.show();
        // Class Name = Post
        //  ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("BloodBank");

        // attribute Author
        query.whereEqualTo("Blood_Group", "B-");

        // setProgressBarIndeterminateVisibility(true);

        query.findInBackground(new FindCallback<ParseObject>() {

            @SuppressWarnings("unchecked")
            @Override
            public void done(List<ParseObject> postList, ParseException e) {
                //setProgressBarIndeterminateVisibility(false);
                if (e == null) {
                 //   Toast.makeText(getActivity(), "Data Found!", Toast.LENGTH_LONG).show();
                    // If there are results, update the list of posts
                    // and notify the adapter
                    posts.clear();
                    for (ParseObject post : postList) {
                        Note note = new Note(post.getString("Donor_Name"), post.getString("Blood_Group"), post.getString("Phone_Number"),
                                post.getString("City"), post.getString("Address"), post.getObjectId());
                        posts.add(note);

                        TestNames.add(post.getString("Donor_Name"));
                        TestBloodGroup.add(post.getString("Blood_Group"));
                        TestCity.add(post.getString("City"));

//                        Toast.makeText(getActivity(), "Test Name =  "+TestNames,
//                                Toast.LENGTH_LONG).show();
                    }
                    setRecyclerView();
                    progressDialog.dismiss();
                } else {

                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    // Toast.makeText(getBaseContext(),  "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(), "Error Retrieving Data! Please Check your internet Connection ",
                            Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }

            }

        });

    }
}
