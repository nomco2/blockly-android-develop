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

import com.google.blockly.android.codegen.LoggingCodeGeneratorCallback;
import com.google.blockly.android.demo.Coding_app_activity.demo.ServerPref;
import com.google.blockly.android.demo.Coding_app_activity.demo.TelnetServer;
import com.google.blockly.android.demo.Coding_app_activity.demo.Utils;
import com.google.blockly.android.demo.R;
import com.google.blockly.model.DefaultBlocks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        connect();
//        try{
//            connect();
//        }catch (Exception e){
//            Toast.makeText(this, "not connecnt",Toast.LENGTH_SHORT).show();
//        }
        String Json = "[{\"Product\" : {\"nested_object1\":\"test1\"}, \"Maker\":\"Samsung\", \"Price\":23000},"
                + "{\"Product\" : [{\"nested_array1\":\"test2\"}], \"Maker\":\"LG\", \"Price\":12000},"
                + "{\"Product\":\"HDD\", \"Maker\":\"Western Digital\", \"Price\":156000}]";


        try{
            String result = "";
            JSONArray ja = new JSONArray(Json);
            for (int i = 0; i < ja.length(); i++){
                JSONObject order = ja.getJSONObject(i);
                result += "product: " + order.getString("Product") + ", maker: " + order.getString("Maker") +
                        ", price: " + order.getInt("Price") + "\n";
                Log.i("result:", result);
            }
        }
        catch (JSONException e){ ;}




    }//oncreate 끝




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
            new LoggingCodeGeneratorCallback(this, TAG) {
                @Override
                public void onFinishCodeGeneration(String generatedCode) {
                    One_circle_code_bolcks mOne_circle_code_bolcks = new One_circle_code_bolcks(generatedCode);
                    Toast.makeText(getApplicationContext(), generatedCode, Toast.LENGTH_SHORT).show();

//                    NetworkThread thread = new NetworkThread(generatedCode.toString());
//                    thread.start();
//                    thread.interrupt();
                }


            };

//            new CodeGenerationRequest.CodeGeneratorCallback() {
//                @Override
//                public void onFinishCodeGeneration(String generatedCode) {
//                    // Sample callback.
//                    if(generatedCode != "") {
//                        Log.i(TAG, "generatedCode:\n" + generatedCode);
//                        Toast.makeText(getApplicationContext(), generatedCode, Toast.LENGTH_SHORT).show();
//
//                        One_circle_code_bolcks mOne_circle_code_bolcks = new One_circle_code_bolcks(generatedCode);
//
//                    }
//
//
//
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
////                            String encoded = "Turtle.execute("
////                                    + JavascriptUtil.makeJsString(generatedCode) + ")";
////                            mTurtleWebview.loadUrl("javascript:" + encoded);
////                            Toast.makeText(getApplicationContext(),generatedCode.toString(), Toast.LENGTH_LONG).show();
//
//                            //텔넷 데이터 보내기
////                            Toast.makeText(getApplicationContext(), generatedCode.toString(), Toast.LENGTH_LONG).show();
//
////                            NetworkThread thread = new NetworkThread(generatedCode.toString());
////                            thread.start();
////                            thread.interrupt();
//                        }
//                    });
//                }
//            };



    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {

//        BLOCK_DEFINITIONS.add(set_locale_language());
        return BLOCK_DEFINITIONS;
    }

    //언어별 설정을 위하여 DefaultBlocks.java에 있던 VARIABLE_BLOCKS_PATH String을 빼고 여기서 따로 추가해줌 -> 나중에 unmodified 방식으로 넣어야 될듯
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
        private char[] send_byte;

        //재귀함수 호출을 위한 [{}] json 덩어리 String 나누기
        private int block_count = 0;
        private String temp_block_string[];

        //variable 처리를 위한 class arraylist
        public ArrayList<Variable_save> variables;
        int int_variable_count =96; // 0x60 hex = 96
        int string_variable_count =112; //0x70 hex = 112



        /*
        change variable => [variable_change_number,\titem:\titem]
         */

        private One_circle_code_bolcks(String input_string){
            variables = new ArrayList<Variable_save>();

            try {
                send_byte = convert_string_to_byte(input_string);
//                telnet.out.println("ABS");
                telnet.out.println(send_byte);
                telnet.out.flush();
//            convert_char_to_byte_mode_select('A','1');
            }catch (Exception e){
                Log.e("error@@1", e.toString());
            }
        }


        private char[] convert_string_to_byte(String input_string) {
            StringBuilder temp_save_bytes = new StringBuilder(); //변환된 바이트 모으는 저장소
            char[] c_arr = input_string.toCharArray();


            Log.i("c_arr.length", c_arr.length +"");

            for (int i = 0; i < c_arr.length; i++) {
                Log.i("i is", i +"");


                    /* 시작과 끝 처리하는 부분 */
                    if (c_arr[i] == '[') {
                        temp_save_bytes.append(0x00); //괄호 열기 바이트 추가
                        byte type_mode = convert_char_to_byte_mode_select(c_arr[++i], c_arr[++i]); //모드 바이트로 변환하기
                        temp_save_bytes.append(type_mode); //모드 바이트 추가



                }else if(c_arr[i] == ']'){
                    temp_save_bytes.append(0x01); //괄호 닫기 바이트 추가



                }else if(c_arr[i] == ':') {
                    temp_save_bytes.append(0x02); // 중간 구분자


                }else if(c_arr[i] == ',' && c_arr[i+1] != '@'){ //디지털 신호 등 기능 들 이면
                    byte type_mode = convert_char_to_byte_mode_select(c_arr[++i],c_arr[++i]); //모드 바이트로 변환하기
                    temp_save_bytes.append(type_mode); //모드 바이트 추가





                    /*변수 관련 처리 부분  */
                }else if(c_arr[i] == '@' && c_arr[i+1] == '@' && c_arr[i+2] == '!') {
                    i += 3; // var@@!item,item2; 이런식으로 선언 됨

                    StringBuilder variable_name_is = new StringBuilder("");
                    while (true) {
                        if (c_arr[i] == ',') {
                            //변수 추가
                            Variable_save mVariable_save = new Variable_save(variable_name_is.toString(), "");
                            variables.add(mVariable_save);
                            variable_name_is = new StringBuilder(""); // 저장해놓고 비우기
                            i += 1; // var@@!item,item2; 이런식이니까 , 다음에 한칸 건너뛰기

                        } else if (c_arr[i] == ';') {
                            //변수 추가하고 끝내기
                            Variable_save mVariable_save = new Variable_save(variable_name_is.toString(), "");
                            variables.add(mVariable_save);
                            break;


                        } else {
                            variable_name_is.append(c_arr[i++]);

                        }
                    }
//                    Log.i("variable list@@", variables.toString());



                    Log.i("i is", i +"");


                }else if(c_arr[i] == '\t'){//변수 중간에 찾아서 변수 순서대로 바이트로 바꾸기
                    StringBuilder variable_name_is = new StringBuilder("");
                    while (true) {

                        if (c_arr[i] == ':' || c_arr[i] == ']' ) { //변수가 끝나는 지점을 찾음
                            //변수 찾아서 바이트 가져오기
        /*
        change variable => [variable_change_number,\titem:\titem]
         */


        //@@ 테스트 필요함 값을 제대로 찾는지
                            for(int j=0;j<variables.size(); j++){
                                try {
                                    Variable_save mVariable_load = variables.get(j);

                                if(mVariable_load.variable_name == variable_name_is.toString()){
                                    temp_save_bytes.append(mVariable_load.variable_address);
                                    break;
                                }
                                }catch (Exception e){
                                    Log.e("error@@2", e.toString());
                                }
                            }
//                            break;

                        } else {
                            variable_name_is.append(c_arr[i++]);
                        }
                    }
                    }

            }



            String convert_strbuilder_to_string = temp_save_bytes.toString();
            char[] return_char = convert_strbuilder_to_string.toCharArray();
            return return_char;
        }

        private byte convert_char_to_byte_mode_select(char input_char1, char input_char2){
            byte return_byte;

            int temp1 = char_to_byte(input_char1)&0xff;
            temp1 = temp1<<4;
            int temp2 = char_to_byte(input_char2)&0xff;
            int sum_temp = temp1|temp2;
            return_byte = (byte) sum_temp;


            return return_byte;
        }

        private byte char_to_byte(char input_char){
            byte return_byte = 0x00;
            switch (input_char){
                case '0': return_byte = 0x00; break;
                case '1': return_byte = 0x01; break;
                case '2': return_byte = 0x02; break;
                case '3': return_byte = 0x03; break;
                case '4': return_byte = 0x04; break;
                case '5': return_byte = 0x05; break;
                case '6': return_byte = 0x06; break;
                case '7': return_byte = 0x07; break;
                case '8': return_byte = 0x08; break;
                case '9': return_byte = 0x09; break;
                case 'A': return_byte = 0x0a; break;
                case 'B': return_byte = 0x0b; break;
                case 'C': return_byte = 0x0c; break;
                case 'D': return_byte = 0x0d; break;
                case 'E': return_byte = 0x0e; break;
                case 'F': return_byte = 0x0f; break;
            }
            return return_byte;

        }







//변수 저장 형식 클래스 -> 인트 저장과 스트링 저장을 구분할 필요가 있음
        class Variable_save{
            public String variable_name;
            public String variable_value;
            public int variable_int = 0;
            private boolean int_or_string;
            public byte variable_address;


            private Variable_save(String name, String value){
                variable_name = name;
                variable_value = value;

                try{ // 숫자로 변환되면 인트형식이라 인식 아니면 그냥 스트링 형식
                    variable_int = Integer.parseInt(value);
                    int_or_string = true;
                }catch (Exception e){
                    int_or_string = false;
                    //@@ 스트링형 저장소의 순서에 맞춰서 바이트로 변환
                    Log.e("error@@3", e.toString());

                }
                address_determiner();//주소 정해 주기


            }

            private void address_determiner(){
                //@@ 바이트가 제대로 들어가는지 확인해봐야 될듯
                if(int_or_string){
                    variable_address = (byte) int_variable_count++;
                }else{
                    variable_address = (byte) string_variable_count++;
                }

            }

        }

    }

}
