/**
 * Copyright Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.codelab.smartlock;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * This class represents changing of credentials outside of Smart Lock. If a saved credential is changed here then
 * used to Sign In it should be deleted.
 */
public class ChangeCredActivity extends AppCompatActivity {

    private TextInputLayout mUsername1TextInputLayout;
    private TextInputLayout mPassword1TextInputLayout;
    private TextInputLayout mUsername2TextInputLayout;
    private TextInputLayout mPassword2TextInputLayout;
    private TextInputLayout mUsername3TextInputLayout;
    private TextInputLayout mPassword3TextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_cred);

        mUsername1TextInputLayout = (TextInputLayout) findViewById(R.id.username1TextInputLayout);
        mPassword1TextInputLayout = (TextInputLayout) findViewById(R.id.password1TextInputLayout);
        mUsername2TextInputLayout = (TextInputLayout) findViewById(R.id.username2TextInputLayout);
        mPassword2TextInputLayout = (TextInputLayout) findViewById(R.id.password2TextInputLayout);
        mUsername3TextInputLayout = (TextInputLayout) findViewById(R.id.username3TextInputLayout);
        mPassword3TextInputLayout = (TextInputLayout) findViewById(R.id.password3TextInputLayout);

        mUsername1TextInputLayout.getEditText().setText(UsernamesAndPasswords.username1);
        mPassword1TextInputLayout.getEditText().setText(UsernamesAndPasswords.password1);
        mUsername2TextInputLayout.getEditText().setText(UsernamesAndPasswords.username2);
        mPassword2TextInputLayout.getEditText().setText(UsernamesAndPasswords.password2);
        mUsername3TextInputLayout.getEditText().setText(UsernamesAndPasswords.username3);
        mPassword3TextInputLayout.getEditText().setText(UsernamesAndPasswords.password3);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsernamesAndPasswords.username1 = mUsername1TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.password1 = mPassword1TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.username2 = mUsername2TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.password2 = mPassword2TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.username3 = mUsername3TextInputLayout.getEditText().getText().toString();
                UsernamesAndPasswords.password3 = mPassword3TextInputLayout.getEditText().getText().toString();
                Toast.makeText(v.getContext(), R.string.creds_updated_msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
