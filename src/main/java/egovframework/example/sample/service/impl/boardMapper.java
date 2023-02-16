package egovframework.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.excel.vo.ExcelVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BoardMapper")
public interface boardMapper {

	void insertExcel(Map<String, Object> paramMap) throws Exception;

	List<ExcelVO> getList(ExcelVO vo);

	void insertBoard(ExcelVO vo);
}
