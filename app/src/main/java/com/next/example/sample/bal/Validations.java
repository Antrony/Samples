package com.next.example.sample.bal;

import android.text.TextUtils;

/**
 * Created by next on 29/7/16.
 */

public class Validations {

    public boolean checkEditTextIsEmptyOrNot(String check) {

        if (TextUtils.isEmpty(check))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean namecheck(String name) {

        String namepattern = "[a-zA-Z.? ]{3,12}";

        if (name.matches(namepattern)) {
            return true;
        }
     else {
            return false;
        }
    }



    public boolean emailcheck(String email) {

        String emailpattern = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|ara|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";

        if (email.matches(emailpattern)) {
            return true;

        } else {
            return false;
        }
    }

    public boolean phonecheck(String phone) {

        String mobilepattern = "[0-9]{10}";

        if (phone.matches(mobilepattern))
        {
            return true;

        } else {
            return false;
        }
    }
    public boolean unamecheck(String uname) {

        String unamepattern = "^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9].{4,}$";

        if (uname.matches(unamepattern))
        {
            return true;

        } else {
            return false;
        }
    }
    public boolean pwordcheck(String pword) {

        String passpattern ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,}$";

        if (pword.matches(passpattern))
        {
            return true;

        } else {
            return false;
        }
    }
    public boolean confirmpword(String conirmpword,String pword) {

        if (conirmpword.matches(pword))
        {
            return true;

        } else {
            return false;
        }
    }
}
