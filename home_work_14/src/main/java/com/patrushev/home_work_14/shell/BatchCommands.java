//package com.patrushev.home_work_14.shell;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//
//@ShellComponent
//@RequiredArgsConstructor
//public class BatchCommands {
//    private final JobLauncher jobLauncher;
//    private final Job importAuthorJob;
//
//    @ShellMethod(value = "Start batch.", key = "startBatch")
//    public void startBatch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        jobLauncher.run(importAuthorJob, new JobParameters());
//    }
//}
//
