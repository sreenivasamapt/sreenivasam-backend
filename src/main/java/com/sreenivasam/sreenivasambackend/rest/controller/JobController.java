package com.sreenivasam.rest.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.beans.JobBean;
import com.sreenivasam.modal.Job;
import com.sreenivasam.service.JobService;
import com.sreenivasam.util.Utility;

@RestController
@RequestMapping(path = "/api/jobs")
@CrossOrigin("*")
public class JobController {

	@Autowired
	private JobService jobService;

	@PostMapping
	public ApiResponse saveJob(@RequestBody(required = true) JobBean jobBean) {

		Job job = new Job();
		if (jobBean.getId() != null) {
			job.setId(jobBean.getId());
		}
		job.setCompany(jobBean.getCompany());

		try {
			if (!Utility.isEmpty(jobBean.getDojStr())) {

				jobBean.setDoj(Utility.onlyDateSdf.parse(jobBean.getDojStr()));

			}

			if (!Utility.isEmpty(jobBean.getDolStr())) {
				jobBean.setDoj(Utility.onlyDateSdf.parse(jobBean.getDolStr()));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		job.setDoj(jobBean.getDoj());
		job.setDol(jobBean.getDol());

		return jobService.saveJob(job);

	}

	@GetMapping
	public ApiResponse jobs() {
		return jobService.getJobs();
	}

	@GetMapping("/{id}")
	public ApiResponse getJob(@PathVariable(value = "id") Long id) {
		return jobService.getJob(id);
	}

	@DeleteMapping("/{id}")
	public ApiResponse deleteJob(@PathVariable(value = "id") Long id) {
		return jobService.deleteJob(id);
	}

	@DeleteMapping()
	public ApiResponse deleteJobs() {
		return jobService.deleteJobs();
	}
}
