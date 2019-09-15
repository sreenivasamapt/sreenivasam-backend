package com.sreenivasam.service;

import org.springframework.stereotype.Service;

import com.sreenivasam.beans.ApiResponse;
import com.sreenivasam.modal.Chit;

@Service
public interface ChitService {

	public ApiResponse saveChit(Chit chit);

	public ApiResponse getChits();

	public ApiResponse getChit(Long id);

	public ApiResponse deleteChit(Long id);

	public ApiResponse deleteChits();

}
