package egovframework.example.sample.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.sample.service.boardService;
import egovframework.excel.util.ExcelRead;
import egovframework.excel.util.ExcelReadOption;
@Service("boardService")
public class boardServiceImpl implements boardService {
	
	@Resource(name="BoardMapper")
	private boardMapper boardDAO;
	
	
	@Override
	public void excelUpload(File destFile) {
		 ExcelReadOption excelReadOption = new ExcelReadOption();
	        
	        //파일경로 추가
	        excelReadOption.setFilePath(destFile.getAbsolutePath());
	        
	        //추출할 컬럼명 추가
	        excelReadOption.setOutputColumns("A", "B", "C","D");
	        
	        //시작행
	        excelReadOption.setStartRow(2);
	        
	        List<Map<String, String>>excelContent  = ExcelRead.read(excelReadOption);
	        
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("excelContent", excelContent);
	        
	        try {
	        	boardDAO.insertExcel(paramMap);
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
		
	}

}
