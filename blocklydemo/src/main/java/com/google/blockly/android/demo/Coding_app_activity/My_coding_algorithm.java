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
    );

    private final Handler mHandler = new Handler();
    private final CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
            new CodeGenerationRequest.CodeGeneratorCallback() {
                @Override
                public void onFinishCodeGeneration(final String generatedCode) {
                    // Sample callback.
                    Log.i(TAG, "generatedCode:\n" + generatedCode);
//                    Toast.makeText(getApplicationContext(), generatedCode, Toast.LENGTH_LONG).show();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
//                            String encoded = "Turtle.execute("
//                                    + JavascriptUtil.makeJsString(generatedCode) + ")";
//                            mTurtleWebview.loadUrl("javascript:" + encoded);
//                            Toast.makeText(getApplicationContext(),generatedCode.toString(), Toast.LENGTH_LONG).show();

                            //텔넷 데이터 보내기
                            NetworkThread thread = new NetworkThread(generatedCode.toString());
                            thread.start();
                            thread.interrupt();
                        }
                    });
                }
            };



    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {
        return BLOCK_DEFINITIONS;
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
}
