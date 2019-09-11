package com.sreenivasam.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.Job;
import com.sreenivasam.repository.JobRespository;
import com.sreenivasam.service.JobService;
import com.sreenivasam.util.Utility;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobRespository jobsRepository;

	private String message = "";

	@Override
	public ApiResponse saveJob(Job job) {

		if (job.getId() == null || job.getId() == 0) {
			message = "Job saved successfully";
		} else {
			message = "Job updated successfully";
		}
		jobsRepository.save(job);

		return new ApiResponse(HttpStatus.OK, message, null);
	}

	@Override
	public ApiResponse getJobs() {
		
		Map<String,Object> data=new LinkedHashMap<>();
		
		List<Job> jobs=jobsRepository.findAll();
		
		if (Utility.isEmpty(jobs)) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		}
		
		Float totalExperience=0f;

		data.put("jobs",jobs);
		data.put("totalExperience",totalExperience);
		
		return new ApiResponse(HttpStatus.OK, null,data);
	}

	@Override
	public ApiResponse getJob(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {

			Optional<Job> jobOp = jobsRepository.findById(id);
			if (jobOp.isPresent()) {
				Job fd = jobOp.get();
				return new ApiResponse(HttpStatus.OK, null, fd);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteJob(Long id) {
		if (getJob(id).getData() == null) {
			return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
		} else {
			jobsRepository.delete((Job) getJob(id).getData());
			message = "Job deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteJobs() {
		jobsRepository.deleteAll();
		message = "Jobs deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
