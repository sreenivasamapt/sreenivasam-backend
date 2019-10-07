package com.sreenivasam.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sreenivasam.beans.FlatBean;
import com.sreenivasam.modal.Flat;
import com.sreenivasam.repository.FlatRespository;
import com.sreenivasam.service.FlatService;
import com.sreenivasam.util.ApiResponse;
import com.sreenivasam.util.Utility;

@Service
public class FlatServiceImpl implements FlatService {

	@Autowired
	FlatRespository flatRepository;

	private String message = "";

	@Override
	public ApiResponse getFlats() {

		Map<String, Object> data = new LinkedHashMap<>();

		List<Flat> flats = flatRepository.findAll();

		List<FlatBean> flatBeans = new ArrayList<>();

		if (Utility.isEmpty(flats)) {
			return new ApiResponse(HttpStatus.NOT_FOUND, "No data found", null);
		}

		for (Flat flat : flats) {
			FlatBean flatBean = new FlatBean();

			flatBean.setId(flat.getId());
			flatBean.setFloor(flat.getFloor());
			flatBean.setFlatNo(flat.getFlatNo());
			flatBean.setVacancy(flat.getVacancy());

			flatBeans.add(flatBean);
		}

		data.put("flats", flatBeans);

		return new ApiResponse(HttpStatus.OK, null, data);
	}

	@Override
	public ApiResponse saveFlat(FlatBean flatBean) {

		if (!isValidFlat(flatBean)) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
		}

		Flat flat = new Flat();
		if (flatBean.getId() != null) {
			flat.setId(flatBean.getId());
		}
		flat.setFloor(flatBean.getFloor());
		flat.setFlatNo(flatBean.getFlatNo());
		flat.setVacancy(flatBean.getVacancy());

		if (flat.getId() == null || flat.getId() == 0) {
			message = "Flat saved successfully";
		} else {
			message = "Flat updated successfully";
		}
		flatRepository.save(flat);

		return new ApiResponse(HttpStatus.OK, message, null);
	}

	public boolean isValidFlat(FlatBean expenseBean) {

		if (Utility.isEmpty(expenseBean.getFloor())) {
			message = "Please Enter Floor";
			return false;
		}

		if (Utility.isEmpty(expenseBean.getFlatNo())) {
			message = "Please Enter Flat No";
			return false;
		}

		if (Utility.isEmpty(expenseBean.getVacancy())) {
			message = "Please Select Vacancy";
			return false;
		}

		return true;
	}

	@Override
	public ApiResponse getFlat(Long id) {
		if (id == null || id == 0) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {

			Optional<Flat> flatOp = flatRepository.findById(id);
			if (flatOp.isPresent()) {
				Flat flat = flatOp.get();

				FlatBean flatBean = new FlatBean();

				flatBean.setId(flat.getId());
				flatBean.setFloor(flat.getFloor());
				flatBean.setFlatNo(flat.getFlatNo());
				flatBean.setVacancy(flat.getVacancy());

				return new ApiResponse(HttpStatus.OK, null, flatBean);
			} else {
				return new ApiResponse(HttpStatus.NO_CONTENT, "No data found", null);
			}
		}
	}

	@Override
	public ApiResponse deleteFlat(Long id) {
		if (getFlat(id).getData() == null) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No data found", null);
		} else {
			flatRepository.delete((Flat) getFlat(id).getData());
			message = "Flat deleted successfully";
			return new ApiResponse(HttpStatus.OK, message, null);
		}
	}

	@Override
	public ApiResponse deleteFlats() {
		flatRepository.deleteAll();
		message = "Flats deleted successfully";
		return new ApiResponse(HttpStatus.OK, message, null);
	}

}
