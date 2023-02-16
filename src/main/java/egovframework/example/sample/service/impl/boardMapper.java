package egovframework.example.sample.service.impl;

import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BoardMapper")
public interface boardMapper {

	void insertExcel(Map<String, Object> paramMap) throws Exception;
}
