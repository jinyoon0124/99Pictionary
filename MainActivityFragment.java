package com.example.jinyoon.pictionary;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private List<People> mPeopleList, mAnswerList;
    private int answerIndex, answerChoiceIndex, incorrectAnswerIndex;
    private Button option0, option1, option2, option3;
    private ImageView answerPicView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        readExcel();

        answerPicView= (ImageView) rootView.findViewById(R.id.answerPic);
        option0 = (Button) rootView.findViewById(R.id.option0);
        option1 = (Button) rootView.findViewById(R.id.option1);
        option2 = (Button) rootView.findViewById(R.id.option2);
        option3 = (Button) rootView.findViewById(R.id.option3);

        return rootView;
    }

    public void readExcel(){
        try {
            AssetManager am = getActivity().getAssets();

            InputStream is = am.open("data.xls");

            Workbook wb = Workbook.getWorkbook(is);
            Sheet sh = wb.getSheet(0);


            int row = sh.getRows();
            int col = sh.getColumns();

            mPeopleList = new ArrayList<>();

            for(int i=1; i<row; i++){
                People mPeople= new People();
                for (int j=0; j<col; j++){
                    Cell c = sh.getCell(j,i);

                    String colName =sh.getCell(j,0).getContents();

                    switch (colName){
                        case "id":
                            mPeople.setId(c.getContents());
                            break;
                        case "Name":
                            mPeople.setName(c.getContents());
                            break;
                        case "Image route":
                            mPeople.setImageRoute(c.getContents());
                            break;
                        case "Field":
                            mPeople.setField(c.getContents());
                            break;
                        case "Citizenship":
                            mPeople.setCitizenship(c.getContents());
                            break;
                        case "Salary":
                            mPeople.setSalary(c.getContents());
                            break;
                        case "Endorsement":
                            mPeople.setEndorsement(c.getContents());
                            break;
                    }
                }
                mPeopleList.add(mPeople);
            }
//
//            Cell c = sh.getCell(1,1);
//            String s = c.getContents();


//            Log.v("String", s);
//
//            for(int k=0; k<mPeopleList.size(); k++){
//                Log.v("ID", mPeopleList.get(k).getId());
//                Log.v("Name",mPeopleList.get(k).getName());
//                Log.v("Image Route", mPeopleList.get(k).getImageRoute());
//            }
//            Log.v("List Size", String.valueOf(mPeopleList.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void questionGenerator(){
        Random rand = new Random();
        answerIndex = rand.nextInt(100);
        answerChoiceIndex = rand.nextInt(4);

        mAnswerList = new ArrayList<>();

        for (int i =0;i<4;i++){
            if(i==answerChoiceIndex){
                mAnswerList.add(mPeopleList.get(answerIndex));
            }else{
                incorrectAnswerIndex = rand.nextInt(100);
                while(incorrectAnswerIndex==answerIndex){
                    incorrectAnswerIndex = rand.nextInt(100);
                }
                mAnswerList.add(mPeopleList.get(incorrectAnswerIndex));
            }
        }

        option0.setText(mAnswerList.get(0).getName());
        option1.setText(mAnswerList.get(1).getName());
        option2.setText(mAnswerList.get(2).getName());
        option3.setText(mAnswerList.get(3).getName());

        Picasso.with(getContext())
                .load(mPeopleList.get(answerIndex).getImageRoute())
                .into(answerPicView);

    }

    public void optionSelected(View view)  {

        if(Integer.parseInt((String)view.getTag())==answerChoiceIndex){
            Toast.makeText(getContext(), "Correct!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Wrong! the answer was "+mPeopleList.get(answerIndex).getName(), Toast.LENGTH_SHORT).show();
        }
        questionGenerator();
    }

}
