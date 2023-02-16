<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data"
        method="post" action= "excelUploadAjax.do">
        <div class="contents">
        <div>첨부파일은 한개만 등록 가능합니다.</div>
    
        <dl class="vm_name">
          <dt class="down w90">첨부 파일</dt>
            <dd><input id="excelFile" type="file" name="excelFile" /></dd>
          </dl>
        </div>
        <div class="bottom">
          <input type="submit" value="전송">
        </div>
      </form>
</body>
</html>