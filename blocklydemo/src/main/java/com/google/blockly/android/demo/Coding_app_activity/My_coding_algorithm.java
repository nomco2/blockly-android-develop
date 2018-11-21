/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.google.blockly.android.demo.Coding_app_activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.blockly.android.AbstractBlocklyActivity;
import com.google.blockly.android.codegen.CodeGenerationRequest;

import com.google.blockly.android.demo.Coding_app_activity.demo.ServerPref;
import com.google.blockly.android.demo.Coding_app_activity.demo.TelnetServer;
import com.google.blockly.android.demo.Coding_app_activity.demo.Utils;
import com.google.blockly.model.DefaultBlocks;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Simplest implementation of AbstractBlocklyActivity.
 */
public class My_coding_algorithm extends AbstractBlocklyActivity {
    private static final String TAG = "SimpleActivity";

    private static final String SAVE_FILENAME = "simple_workspace.xml";
    private static final String AUTOSAVE_FILENAME = "simple_workspace_temp.xml";

    private static final String My_coding_toolbox_1 = "default/My_coding_toolbox_1.xml";


    ServerPref serverPref;
    Utils utils;
    TelnetServer telnet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = new Utils(this);
        serverPref = new ServerPref(this);
        try{
            connect();
        }catch (Exception e){
            Toast.makeText(this, "not connecnt",Toast.LENGTH_SHORT).show();
        }

    }


    //telnet 관련 메소드
    public void connect() {
        telnet = new TelnetServer(My_coding_algorithm.this, this);
        telnet.start();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(telnet != null)
            telnet.disconnect();
    }

    private class NetworkThread extends Thread {
        String telnet_send_data;

        public NetworkThread(String send_data){
            telnet_send_data = send_data;
        }

        public void run() {
            telnet.out.println(telnet_send_data);
            telnet.out.flush();
        }
    }



    // Add custom blocks to this list.
    private static final List<String> BLOCK_DEFINITIONS = DefaultBlocks.getAllBlockDefinitions();
    private static final List<String> JAVASCRIPT_GENERATORS = Arrays.asList(
        // Custom block generators go here. Default blocks are already included.
        // TODO(#99): Include Javascript defaults when other languages are supported.
            "my_coding_algorithm/my_coding_algorithm.js"
    );

    private final Handler mHandler = new Handler();
    private final CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
            new CodeGenerationRequest.CodeGeneratorCallback() {
                @Override
                public void onFinishCodeGeneration(final String generatedCode) {
                    // Sample callback.
                    Log.i(TAG, "generatedCode:\n" + generatedCode);
                    Toast.makeText(getApplicationContext(), generatedCode, Toast.LENGTH_SHORT).show();

                    One_circle_code_bolcks mOne_circle_code_bolcks = new One_circle_code_bolcks(generatedCode);




                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
//                            String encoded = "Turtle.execute("
//                                    + JavascriptUtil.makeJsString(generatedCode) + ")";
//                            mTurtleWebview.loadUrl("javascript:" + encoded);
//                            Toast.makeText(getApplicationContext(),generatedCode.toString(), Toast.LENGTH_LONG).show();

                            //텔넷 데이터 보내기
//                            Toast.makeText(getApplicationContext(), generatedCode.toString(), Toast.LENGTH_LONG).show();

//                            NetworkThread thread = new NetworkThread(generatedCode.toString());
//                            thread.start();
//                            thread.interrupt();
                        }
                    });
                }
            };



    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {

//        BLOCK_DEFINITIONS.add(set_locale_language());
        return BLOCK_DEFINITIONS;
    }

    //언어별 설정을 위하여 DefaultBlocks.java에 있던 VARIABLE_BLOCKS_PATH String을 빼고 여기서 따로 추가해줌
    private String set_locale_language(){

        Locale systemLocale = getApplicationContext().getResources().getConfiguration().locale;
        String strDisplayCountry = systemLocale.getDisplayCountry(); // 대한민국
        String strCountry = systemLocale.getCountry(); // KR
        String strLanguage = systemLocale.getLanguage(); // ko

        String VARIABLE_BLOCKS_PATH;
        if(strLanguage == "ko"){
            VARIABLE_BLOCKS_PATH = "default/variable_blocks_kr.json";

        }else{
            VARIABLE_BLOCKS_PATH = "default/variable_blocks.json";
        }
        return VARIABLE_BLOCKS_PATH;


    }



    @NonNull
    @Override
    protected String getToolboxContentsXmlPath() {
        // Replace with a toolbox that includes application specific blocks.
        return My_coding_toolbox_1;
    }

    @NonNull
    @Override
    protected List<String> getGeneratorsJsPaths() {
        return JAVASCRIPT_GENERATORS;
    }

    @NonNull
    @Override
    protected CodeGenerationRequest.CodeGeneratorCallback getCodeGenerationCallback() {
        // Uses the same callback for every generation call.
        return mCodeGeneratorCallback;
    }

    /**
     * Optional override of the save path, since this demo Activity has multiple Blockly
     * configurations.
     * @return Workspace save path used by SimpleActivity and SimpleFragment.
     */
    @Override
    @NonNull
    protected String getWorkspaceSavePath() {
        return SAVE_FILENAME;

    }

    /**
     * Optional override of the auto-save path, since this demo Activity has multiple Blockly
     * configurations.
     * @return Workspace auto-save path used by SimpleActivity and SimpleFragment.
     */
    @Override
    @NonNull
    protected String getWorkspaceAutosavePath() {
        return AUTOSAVE_FILENAME;
    }



    //generate code 처리되는 클래스
    class One_circle_code_bolcks{
        public String input_string;
        public int type_of_block; // if 1~3, for 4~6 등등 정해야 될 듯
        private byte[] send_byte;

        //재귀함수 호출을 위한 [{}] json 덩어리 String 나누기
        private int block_count = 0;
        private String temp_block_string[];


//        var item;
//
//
//    [@2:#7,([3:5,item:item]):  item = (typeof item == 'number' ? item : 0) + 0;
//    ]

        private One_circle_code_bolcks(String input_string){

            find_end_of_brace(input_string);


        }


        private byte[] convert_string_to_byte(String input_string) {
            List<Object> return_byte_list = new ArrayList<Object>();
            char[] c_arr = input_string.toCharArray();
            int open_brace = 0;
            int close_brace = 0;

            //var 변수 처리
            //

            for (int i = 0; i < c_arr.length; i++) {


                if (c_arr[i] == '[') {
                    convert_char_to_byte_mode_select(c_arr[++i],c_arr[++i]);
                }


            }
            return return_byte;
        }

        private byte convert_char_to_byte_mode_select(char input_char1, char input_char2){
            byte[] return_byte = new byte[2];
            switch (input_char1){
                case '0': return_byte[0] = 0x5B; break;
                case '1': return_byte[0] = 0x5B; break;
                case '2': return_byte[0] = 0x5B; break;
                case '3': return_byte[0] = 0x5B; break;
                case '4': return_byte[0] = 0x5B; break;
                case '5': return_byte[0] = 0x5B; break;
                case '6': return_byte[0] = 0x5B; break;
            }

            switch (input_char2){
                case '0': return_byte[0] = 0x5B; break;
                case '1': return_byte[0] = 0x5B; break;
                case '2': return_byte[0] = 0x5B; break;
                case '3': return_byte[0] = 0x5B; break;
                case '4': return_byte[0] = 0x5B; break;
                case '5': return_byte[0] = 0x5B; break;
                case '6': return_byte[0] = 0x5B; break;
            }
        }


        //if,for, digital write 등  판별
        private void find_type_of_block(String input_string){
            int first_colon = input_string.indexOf("'");
            Log.i("first colon : ",first_colon +"");

            String sub_string = input_string.substring(first_colon+1);
            int second_colon = sub_string.indexOf("'");
            String block_type = input_string.substring(first_colon+1,first_colon+1+second_colon);
            Log.i("type string : ",block_type);


            int find_brace = input_string.indexOf("{");
            sub_string = input_string.substring(find_brace);
            find_end_of_brace(sub_string);
        }

        //[ 시작되고 ] 끝날때 까지 찾기
        private void find_end_of_brace(String input_string){
            char[] c_arr = input_string.toCharArray();
            int open_brace = 0;
            int close_brace = 0;
            int one_circle_block = 0;
            int start_sub_string = 0;
            int[] start_brace;
            int[] end_brace;


            for(int i =0; i<c_arr.length; i++){
                if(c_arr[i] == '['){
                    if(open_brace == 0){
                        start_sub_string = i;
                    }
                    if(open_brace != 0){ //블록안에 또 블록

                    }

                    open_brace++;
                }
                if(c_arr[i] == ']'){
                    close_brace++;
                }

                if(open_brace == close_brace && open_brace != 0){
                    one_circle_block = i;
                    break;
                }
            }
            String sub_string = input_string.substring(start_sub_string,one_circle_block+1);
            sub_string = sub_string.replace("(","");
            sub_string = sub_string.replace(")","");
            sub_string = sub_string.replace("{","");
            sub_string = sub_string.replace("}","");
            sub_string = sub_string.replace("[","");
            sub_string = sub_string.replace("]","");

            block_contents_json_parsing(sub_string);

        }

        private void block_contents_json_parsing(String input_string){
            try {
                String json_string = "[{" + input_string + "}]";
                JSONArray jarray = new JSONArray(json_string);   // JSONArray 생성
                for(int i=0; i < jarray.length(); i++) {
                    JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                    String block_type = jObject.getString("block_type");
                    Log.i("find type : ", block_type);

                }


            }catch (Exception e){
                Log.e("json err", e.toString());
            }


        }



    }

}
