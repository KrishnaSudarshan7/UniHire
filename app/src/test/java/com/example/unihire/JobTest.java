package com.example.unihire;

import junit.framework.TestCase;

public class JobTest extends TestCase {
/*
    public void testSetUnivId() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setUnivId(newVal);
        assertEquals(newVal, job.getUnivId());
        System.out.println("Test Passed");
    }

    public void testSetJobTitle() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setJobTitle(newVal);
        assertEquals(newVal, job.getJobTitle());
        System.out.println("Test Passed");
    }

    public void testSetDepartment() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setDepartment(newVal);
        assertEquals(newVal, job.getDepartment());
        System.out.println("Test Passed");
    }

    public void testSetSpecialization() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setSpecialization(newVal);
        assertEquals(newVal, job.getSpecialization());
        System.out.println("Test Passed");
    }

    public void testSetJobDescription() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setJobDescription(newVal);
        assertEquals(newVal, job.getJobDescription());
        System.out.println("Test Passed");
    }

    public void testSetPriority1() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setPriority1(newVal);
        assertEquals(newVal, job.getPriority1());
        System.out.println("Test Passed");
    }

    public void testSetPriority2() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setPriority2(newVal);
        assertEquals(newVal, job.getPriority2());
        System.out.println("Test Passed");
    }

    public void testSetPriority3() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setPriority3(newVal);
        assertEquals(newVal, job.getPriority3());
        System.out.println("Test Passed");
    }

    public void testSetJobID() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setJobID(newVal);
        assertEquals(newVal, job.getJobID());
        System.out.println("Test Passed");
    }

    public void testSetJobStatus() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setJobStatus(newVal);
        assertEquals(newVal, job.getJobStatus());
        System.out.println("Test Passed");
    }

    public void testSetPostedDateTime() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setPostedDateTime(newVal);
        assertEquals(newVal, job.getPostedDateTime());
        System.out.println("Test Passed");
    }

    public void testSetClosedDateTime() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        String newVal="new";
        job.setClosedDateTime(newVal);
        assertEquals(newVal, job.getClosedDateTime());
        System.out.println("Test Passed");
    }

    public void testSetWeightage1() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        int newVal=100;
        job.setWeightage1(newVal);
        assertEquals(newVal, job.getWeightage1());
        System.out.println("Test Passed");
    }

    public void testSetWeightage2() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        int newVal=100;
        job.setWeightage2(newVal);
        assertEquals(newVal, job.getWeightage2());
        System.out.println("Test Passed");
    }

    public void testSetWeightage3() {
        Job job=new Job(
                "uid","1-1-22","1-1-22","active","job_title","department","specialization","jd","p1","p2","p3","jobId",10,
                20,70,true,true,true,true,true,true
                ,false
        );
        int newVal=100;
        job.setWeightage3(newVal);
        assertEquals(newVal, job.getWeightage3());
        System.out.println("Test Passed");
    }
*/
}