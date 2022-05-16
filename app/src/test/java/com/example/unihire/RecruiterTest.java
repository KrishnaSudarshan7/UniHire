package com.example.unihire;

import junit.framework.TestCase;

public class RecruiterTest extends TestCase {

    public void testSetUnivName() {
        Recruiter rec=new Recruiter(
                "Amrita University","www.amrita.edu",
                "AD0BC8","696969696969","amrita@amrita.edu");
        String newVal="new";
        rec.setUnivName(newVal);
        assertEquals(newVal, rec.getUnivName());
        System.out.println("Test Passed");
    }

    public void testSetUnivURL() {
        Recruiter rec=new Recruiter(
                "Amrita University","www.amrita.edu",
                "AD0BC8","696969696969","amrita@amrita.edu");
        String newVal="new";
        rec.setUnivURL(newVal);
        assertEquals(newVal, rec.getUnivURL());
        System.out.println("Test Passed");
    }

    public void testSetUnivUGCID() {
        Recruiter rec=new Recruiter(
                "Amrita University","www.amrita.edu",
                "AD0BC8","696969696969","amrita@amrita.edu");
        String newVal="new";
        rec.setUnivUGCID(newVal);
        assertEquals(newVal, rec.getUnivUGCID());
        System.out.println("Test Passed");
    }

    public void testSetUnivNum() {
        Recruiter rec=new Recruiter(
                "Amrita University","www.amrita.edu",
                "AD0BC8","696969696969","amrita@amrita.edu");
        String newVal="new";
        rec.setUnivNum(newVal);
        assertEquals(newVal, rec.getUnivNum());
        System.out.println("Test Passed");
    }

    public void testSetEmail() {
        Recruiter rec=new Recruiter(
                "Amrita University","www.amrita.edu",
                "AD0BC8","696969696969","amrita@amrita.edu");
        String newVal="new";
        rec.setEmail(newVal);
        assertEquals(newVal, rec.getEmail());
        System.out.println("Test Passed");
    }

    public void testSetAbout() {
        Recruiter rec=new Recruiter(
                "Amrita University","www.amrita.edu",
                "AD0BC8","696969696969","amrita@amrita.edu");
        String newVal="new";
        rec.setAbout(newVal);
        assertEquals(newVal, rec.getAbout());
        System.out.println("Test Passed");
    }

    public void testSetgMapsLink() {
        Recruiter rec=new Recruiter(
                "Amrita University","www.amrita.edu",
                "AD0BC8","696969696969","amrita@amrita.edu");
        String newVal="new";
        rec.setgMapsLink(newVal);
        assertEquals(newVal, rec.getgMapsLink());
        System.out.println("Test Passed");
    }

    public void testSetDP_Link() {
        Recruiter rec=new Recruiter(
                "Amrita University","www.amrita.edu",
                "AD0BC8","696969696969","amrita@amrita.edu");
        String newVal="new";
        rec.setDP_Link(newVal);
        assertEquals(newVal, rec.getDP_Link());
        System.out.println("Test Passed");
    }
}