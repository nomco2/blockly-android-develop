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

                    int find_var = generatedCode.indexOf("var");
//                    Log.i(TAG, "find var:\n" + find_var);
                    if(find_var == 0){
                        int find_semiconlon = generatedCode.indexOf(";");
//                        Log.i(TAG, "find semicolon:\n" + find_semiconlon);



                        for(int i=0; i<find_semiconlon + 1; i++){
                            //var 개수 찾기

                        }
                    }
                    int find_brace = generatedCode.indexOf("{");
                    Log.i(TAG, "find_brace:\n" + find_brace);
                    String spilt_string = generatedCode.substring(find_brace);
                    Log.i(TAG, "spilt_string:\n" + spilt_string);
                    char[] c_arr = spilt_string.toCharArray();
                    Log.i(TAG, "c_arr:\n" + c_arr[0]);

                    int open_brace_counter = 0;
                    int close_brace_counter = 0;
                    int[] end_brace_number;

                    for(int i : c_arr){
                        if(c_arr[i] == '{'){
                            open_brace_counter++;
                        }else if(c_arr[i] == '}'){
                            close_brace_counter++;
                        }

                        if(open_brace_counter == close_brace_counter){

                        }
                    }




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

    class One_circle_code_bolcks{
        public String input_string;
        public int type_of_block; // if 1~3, for 4~6 등등 정해야 될 듯
        private byte[] add_byte;

        //{'if':[{({'equal':[{'item':'(1)'}]};)},{'control_type':1},{  '{for':[{'infinity':1},{'control_type':1},{}]}}]};
        private void One_circle_code_bolcks(String input_string){
            String spilt_string = input_string.substring(3);
            int find_dot = spilt_string.indexOf("'");  //{'if' 까지 길이를 찾음
            spilt_string = input_string.substring(0,find_dot); // {'if' 까지 자름
            if(spilt_string.indexOf("control_if_start") != -1){ //control_if_start를 찾으면
                type_of_block = 1; //이런식으로 정해야 될듯
            }
//            Log.i(TAG, "find semicolon:\n" + find_dot);
        }

        //안에 다른 type의 시작 블록(if나 for같은)게 있을 때
        private One_circle_code_bolcks if_other_type_of_blocks_inner_class(String input_string){
            One_circle_code_bolcks return_class = new One_circle_code_bolcks();

            return return_class;
        }

    }

}
