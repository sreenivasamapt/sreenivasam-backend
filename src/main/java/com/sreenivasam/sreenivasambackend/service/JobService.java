package com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.Job;

@Service
public interface JobService {

	public ApiResponse saveJob(Job job);

	public ApiResponse getJobs();

	public ApiResponse getJob(Long id);

	public ApiResponse deleteJob(Long id);

	public ApiResponse deleteJobs();

}
