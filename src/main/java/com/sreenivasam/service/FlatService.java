package com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.FlatBean;
import com.sreenivasam.util.ApiResponse;

@Service
public interface FlatService {

	public ApiResponse saveFlat(FlatBean flatBean);

	public ApiResponse getFlats();

	public ApiResponse getFlat(Long id);

	public ApiResponse deleteFlat(Long id);

	public ApiResponse deleteFlats();

}
