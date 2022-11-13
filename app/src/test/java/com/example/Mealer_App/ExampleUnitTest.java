package com.example.Mealer_App;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.EditText;

import java.text.BreakIterator;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ValidatorsTest {

    @Test
    public void validateNumber() {
        Validators number = new Validators();
        BreakIterator textCVV = null;
        String input = textCVV.getText().toString();
        number.validateCVV(new EditText(null));
        assertEquals(999, number.validateCVV(new EditText(null)));

    }

    @Test
    public void validateCardNumber() {
        Validators number = new Validators();
        String expected = "12345678912345";
        BreakIterator testCardNumber = null;
        String input = testCardNumber.getText().toString();
        number.validateCardNumber(new EditText(null));
        assertEquals(expected, number.validateCardNumber(new EditText(null)));
    }

    @Test
    public void validateName() {
        Validators number = new Validators();
        String expected = "Georgin";
        BreakIterator testCardNumber = null;
        String input = testCardNumber.getText().toString();
        number.validateName(new EditText(null));
        assertEquals(expected, number.validateName(new EditText(null)));

    }

    @Test
    public void validateEmail() {
        Validators number = new Validators();
        String expected = "Georgin2003@gmail.com";
        number.validateEmail(new EditText(null));
        assertEquals(expected, number.validateEmail(new EditText(null)));

    }
}
