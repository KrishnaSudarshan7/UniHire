package com.example.unihire;

import junit.framework.TestCase;

public class AddressClassTest extends TestCase {

    public void testSetAddressL1() {
        AddressClass ua=new AddressClass("Line 1",
                "Line 2", "India", "Tamil Nadu",
                "Chennai",600061
        );
        String newVal="Line One";
        ua.setAddressL1(newVal);
        assertEquals(newVal,ua.getAddressL1());
        System.out.println("Test Passed");
    }

    public void testSetAddressL2() {
        AddressClass ua=new AddressClass("Line 1",
                "Line 2", "India", "Tamil Nadu",
                "Chennai",600061
        );
        String newVal="Line Two";
        ua.setAddressL2(newVal);
        assertEquals(newVal,ua.getAddressL2());
        System.out.println("Test Passed");
    }

    public void testSetCountry() {
        AddressClass ua=new AddressClass("Line 1",
                "Line 2", "India", "Tamil Nadu",
                "Chennai",600061
        );
        String newVal="Kailasa";
        ua.setCountry(newVal);
        assertEquals(newVal,ua.getCountry());
        System.out.println("Test Passed");
    }

    public void testSetState() {
        AddressClass ua=new AddressClass("Line 1",
                "Line 2", "India", "Tamil Nadu",
                "Chennai",600061
        );
        String state="goa";
        ua.setState(state);
        assertEquals(state,ua.getState());
        System.out.println("Test Passed");
    }

    public void testSetCity() {
        AddressClass ua=new AddressClass("Line 1",
                "Line 2", "India", "Tamil Nadu",
                "Chennai",600061
        );
        String newVal="Quohawg";
        ua.setCity(newVal);
        assertEquals(newVal,ua.getCity());
        System.out.println("Test Passed");
    }

    public void testSetPincode() {
        AddressClass ua=new AddressClass("Line 1",
                "Line 2", "India", "Tamil Nadu",
                "Chennai",600061
        );
        int newVal=641112;
        ua.setPincode(newVal);
        assertEquals(newVal,ua.getPincode());
        System.out.println("Test Passed");
    }
}