package edu.iastate.tlavan.bonappetit;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * Created by Tracy La Van
 */

public class PasswordAsterisks extends PasswordTransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private class PasswordCharSequence implements CharSequence {
        private CharSequence charsStored;

        // Store the char sequence
        public PasswordCharSequence(CharSequence source) {
            charsStored = source;
        }

        // Show asterisks as user types
        public char charAt(int index) {
            return '*';
        }

        public int length() {
            return charsStored.length();
        }

        public CharSequence subSequence(int start, int end) {
            return charsStored.subSequence(start, end);
        }
    }
};