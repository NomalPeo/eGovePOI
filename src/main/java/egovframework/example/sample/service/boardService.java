package egovframework.example.sample.service;

import java.io.File;
import java.util.List;

import egovframework.excel.vo.ExcelVO;

public interface boardService {
	
	void excelUpload(File destFile);

	List<ExcelVO> getList(ExcelVO vo);

	void insertExcel(ExcelVO vo);
}
