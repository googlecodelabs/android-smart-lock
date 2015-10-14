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

public class CodelabUtil {

    /**
     * Check whether or not given username and password pair exist in UsernamesAndPassword class.
     */
    public static boolean isValidCredential(String username, String password) {
        if ((username.equals(UsernamesAndPasswords.username1) && password.equals(UsernamesAndPasswords.password1)) ||
                (username.equals(UsernamesAndPasswords.username2) && password.equals(UsernamesAndPasswords.password2)) ||
                (username.equals(UsernamesAndPasswords.username3) && password.equals(UsernamesAndPasswords.password3))) {
            return true;
        }
        return false;
    }

    /**
     * Check if given username starts an existing username in the UsernamesAndPassword class.
     */
    public static boolean isValidUsernameSoFar(String username) {
        return UsernamesAndPasswords.username1.startsWith(username) ||
                UsernamesAndPasswords.username2.startsWith(username) ||
                UsernamesAndPasswords.username3.startsWith(username);
    }

    /**
     * Check if the password starts an existing password and matches an existing username in the
     * UsernamesAndPassword class.
     */
    public static boolean isValidPasswordSoFar(String username, String password) {
        if (username.equals(UsernamesAndPasswords.username1) &&
                UsernamesAndPasswords.password1.startsWith(password)) {
            return true;
        } else if (username.equals(UsernamesAndPasswords.username2) &&
                UsernamesAndPasswords.password2.startsWith(password)) {
            return true;
        } else if (username.equals(UsernamesAndPasswords.username3) &&
                UsernamesAndPasswords.password3.startsWith(password)) {
            return true;
        }
        return false;
    }

}
