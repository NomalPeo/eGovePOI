package egovframework.excel.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.sample.service.boardService;
import egovframework.excel.vo.ExcelVO;
@Controller
public class boardController {


	@Resource(name="boardService")
	private boardService boardService;


	@RequestMapping(value="/main.do")
	public String main(ExcelVO vo,Model model) {
		List<ExcelVO> list = boardService.getList(vo);
		model.addAttribute("list",list);
		
		
		return "main";
	}

	// 업로드
	@RequestMapping(value = "/excelUploadAjax.do", method = RequestMethod.POST)
	public String excelUploadAjax(MultipartFile testFile, MultipartHttpServletRequest request) throws  Exception{

		System.out.println("업로드 진행");

		MultipartFile excelFile = request.getFile("excelFile");

		if(excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택해 주세요");
		}

		File destFile = new File("C:\\cR\\excel\\"+excelFile.getOriginalFilename());

		try {
			//내가 설정한 위치에 내가 올린 파일을 만들고 
			excelFile.transferTo(destFile);
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}

		//업로드를 진행하고 다시 지우기
		boardService.excelUpload(destFile);
		destFile.delete();

		
		return "redirect:/main.do";
	}
	
	@RequestMapping(value="excelSave.do")
	public String excelSave(ExcelVO vo) {
		boardService.insertExcel(vo);
		return "redirect:/main.do";
	}
	
	
	
	
	@RequestMapping(value="excelListSave.do")
	public void excelListSave(ExcelVO vo,HttpServletResponse response) throws IOException {
		List<ExcelVO> list = boardService.getList(vo);
		Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("게시판글들");
        int rowNo = 0;
        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("번호");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("아이디");
        headerRow.createCell(3).setCellValue("비밀번호");
        for (ExcelVO board : list) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(board.getNo());
            row.createCell(1).setCellValue(board.getName());
            row.createCell(2).setCellValue(board.getId());
            row.createCell(3).setCellValue(board.getPwd());
        }
 
        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        //response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=TEST.xlsx");

        // Excel File Output
        workbook.write(response.getOutputStream());
        
        
	}
	

}




















