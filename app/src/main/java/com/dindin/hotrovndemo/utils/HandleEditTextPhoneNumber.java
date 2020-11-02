package com.dindin.hotrovndemo.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class HandleEditTextPhoneNumber implements TextWatcher {
    EditText editText;
    String current = "";

    public HandleEditTextPhoneNumber(EditText editText) {
        this.editText = editText;
        this.editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String s1 = s.toString();
        if(s1.equals(current)){
            return;
        }
        if(!s1.isEmpty()){
            int length = s1.length();
            if(s1.charAt(0) != '0'){
                s1 = "(+84) " + s1.charAt(0);
            } else {
                if(length == 4) {

                }
            }
            current = s1;
            editText.setText(s1);
            editText.setSelection(s1.length());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
