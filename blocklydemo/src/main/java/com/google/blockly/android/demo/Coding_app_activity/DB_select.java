package com.google.blockly.android.demo.Coding_app_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.blockly.android.demo.R;
import com.google.blockly.android.demo.database.adapter.CustomAdapter;
import com.google.blockly.android.demo.database.conf.Constants;
import com.google.blockly.android.demo.database.data.InfoClass;
import com.google.blockly.android.demo.database.database.DbOpenHelper;

import java.util.ArrayList;



public class DB_select extends Activity {
    private static final String TAG = "TestDataBaseActivity";
    private DbOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private InfoClass mInfoClass;
    private ArrayList<InfoClass> mInfoArray;
    private CustomAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_select);

        setLayout();

        // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();




        mInfoArray = new ArrayList<InfoClass>();

        doWhileCursorToArray();


        mAdapter = new CustomAdapter(this, mInfoArray);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemLongClickListener(longClickListener);
        mListView.setOnItemClickListener(select_click_listener);

    }

    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }


    /**
     * 단순 선택할 때
     */
    private AdapterView.OnItemClickListener select_click_listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1,
                                int position, long arg3) {



            Intent intent = new Intent(getApplication(), Button_allocate.class);
            intent.putExtra("project_list_num", position + 1);
            startActivity(intent);


        }

    };


    /**
     * ListView의 Item을 롱클릭 할때 호출 ( 선택한 아이템의 DB 컬럼과 Data를 삭제 한다. )
     */
    private AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {

//            DLog.e(TAG, "position = " + position);
//
//            boolean result = mDbOpenHelper.deleteColumn(position + 1);
//            DLog.e(TAG, "result = " + result);
//
//            if(result){
//                mInfoArray.remove(position);
//                mAdapter.setArrayList(mInfoArray);
//                mAdapter.notifyDataSetChanged();
//            }else {
//                Toast.makeText(getApplicationContext(), "INDEX를 확인해 주세요.",
//                        Toast.LENGTH_LONG).show();
//            }



            final ArrayList<String> ListItems = new ArrayList<>();
            ListItems.add("프로젝트 이름 수정");
            ListItems.add("프로젝트 삭제");
            ListItems.add("프로젝트 복사");
            ListItems.add("취소");
            final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

            AlertDialog.Builder builder = new AlertDialog.Builder(DB_select.this);
            builder.setTitle("프로젝트 설정");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int pos) {

                    String selectedText = items[pos].toString();
                    if (pos == 0) {//버튼 이름 수정
                        final EditText edittext = new EditText(DB_select.this);

                        AlertDialog.Builder builder = new AlertDialog.Builder(DB_select.this);
                        builder.setTitle("버튼 이름 수정");
                        builder.setMessage("바꿀 버튼 이름을 넣어주세요. \n ※같은 이름은 안됨.");
                        builder.setView(edittext);
                        builder.setPositiveButton("입력",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {



                                    }
                                });
                        builder.setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        builder.show();

                    } else if (pos == 1) { // 코드 내용 수정


                    } else if (pos == 2) {//취소
                    }
                }
            });
            builder.show();



            return false;



        }
    };




    /**
     * DB에서 받아온 값을 ArrayList에 Add
     */
    private void doWhileCursorToArray(){

        mCursor = null;
        mCursor = mDbOpenHelper.getAllColumns();

        while (mCursor.moveToNext()) {

            mInfoClass = new InfoClass(
                    mCursor.getInt(mCursor.getColumnIndex("_id")),
                    mCursor.getString(mCursor.getColumnIndex("name")),
                    mCursor.getString(mCursor.getColumnIndex("contact")),
                    mCursor.getString(mCursor.getColumnIndex("email"))
            );

            mInfoArray.add(mInfoClass);
        }

        mCursor.close();
    }


    /**
     * OnClick Button
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_add:
                mDbOpenHelper.insertColumn
                        (
                                mEditTexts[Constants.NAME].getText().toString().trim(),
                                mEditTexts[Constants.CONTACT].getText().toString().trim(),
                                mEditTexts[Constants.EMAIL].getText().toString().trim()
                        );


                mInfoArray.clear();

                doWhileCursorToArray();

                mAdapter.setArrayList(mInfoArray);
                mAdapter.notifyDataSetChanged();

                mCursor.close();





                break;

            default:
                break;
        }
    }

    /*
     * Layout
     */
    private EditText[] mEditTexts;
    private ListView mListView;

    private void setLayout(){
        mEditTexts = new EditText[]{
                (EditText)findViewById(R.id.et_name),
                (EditText)findViewById(R.id.et_contact),
                (EditText)findViewById(R.id.et_email)
        };

        mListView = (ListView) findViewById(R.id.lv_list);
    }
}

