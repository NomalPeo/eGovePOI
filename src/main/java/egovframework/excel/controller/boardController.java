package egovframework.excel.controller;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.sample.service.boardService;
@Controller
public class boardController {
	
	
	@Resource(name="boardService")
	private boardService boardService;
	
	
	@RequestMapping(value="/main.do")
	public String main() {
		return "main";
	}
	
	
	@RequestMapping(value = "/excelUploadAjax.do", method = RequestMethod.POST)
    public ModelAndView excelUploadAjax(MultipartFile testFile, MultipartHttpServletRequest request) throws  Exception{
    
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
    
    ModelAndView view = new ModelAndView();
    view.setViewName("/egovSampleList.do");
    
    return view;
}

}
