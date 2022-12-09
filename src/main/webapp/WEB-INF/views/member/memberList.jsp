<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체직원리스트</title>
</head>
<body>
	<table border="1" id="tableData">
	<th>직원번호</th>
	<th>이름</th>
	<th>직급</th>
	<th>연락처</th>
	<th>이메일</th>
	<c:if test="${!empty mList }">
			<c:forEach items="${mList}" var="member" varStatus="i">
				<tr><!-- 첫번째 줄 시작 -->
				    <td><a href="/member/memberDetail?memberNo=${member.memberNo }">${member.memberNo }</a></td>
				    <td>${member.memberName }</td>
				    <td>${member.memberPo }</td>
				    <td>${member.memberTel }</td>
				    <td>${member.memberEmail }</td>
				</tr><!-- 첫번째 줄 끝 -->
			</c:forEach>
			<tr>
					<td >
						<form action="/member/search" method="get">
							<input type="text" name="searchValue" value="${searchValue }">
							<input type="submit" value="검색">
						</form>
					</td>
					<td>
						<button  id="csvDownloadButton">CSV다운로드</button>
						 <button id="excelDownloadButton">엑셀파일 다운로드</button>
					</td>
			</tr>
			
	</c:if>
    </table>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
<script type="text/javascript">

//csv다운로드
class ToCSV {
    constructor() {
    // CSV 버튼에 이벤트 등록
    document.querySelector('#csvDownloadButton').addEventListener('click', e => {
        e.preventDefault()
        this.getCSV('memberList.csv')
    })
}

downloadCSV(csv, filename) {
    let csvFile;
    let downloadLink;
    csvFile = new Blob([csv], {type: "text/csv"})
    downloadLink = document.createElement("a")
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(csvFile)
    downloadLink.style.display = "none"
    document.body.appendChild(downloadLink)
    downloadLink.click()
}

getCSV(filename) {
    const csv = []
    const rows = document.querySelectorAll("#tableData tr")

    for (let i = 0; i < rows.length; i++) {
        const row = [], cols = rows[i].querySelectorAll("td, th")
        for (let j = 0; j < cols.length; j++)
            row.push(cols[j].innerText)
        csv.push(row.join(","))
    }

    this.downloadCSV(csv.join("\n"), filename)
}
}

document.addEventListener('DOMContentLoaded', e => {
new ToCSV()
})


//엑셀파일 다운로드
const excelDownload = document.querySelector('#excelDownloadButton');

document.addEventListener('DOMContentLoaded', ()=>{
    excelDownload.addEventListener('click', exportExcel);
});

function exportExcel(){ 
  // step 1. workbook 생성
  var wb = XLSX.utils.book_new();

  // step 2. 시트 만들기 
  var newWorksheet = excelHandler.getWorksheet();

  // step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.  
  XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());

  // step 4. 엑셀 파일 만들기 
  var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});

  // step 5. 엑셀 파일 내보내기 
  saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), excelHandler.getExcelFileName());
}

var excelHandler = {
    getExcelFileName : function(){
        return 'table-test.xlsx';	//파일명
    },
    getSheetName : function(){
        return 'Table Test Sheet';	//시트명
    },
    getExcelData : function(){
        return document.getElementById('tableData'); 	//TABLE id
    },
    getWorksheet : function(){
        return XLSX.utils.table_to_sheet(this.getExcelData());
    }
}

function s2ab(s) { 
  var buf = new ArrayBuffer(s.length); //convert s to arrayBuffer
  var view = new Uint8Array(buf);  //create uint8array as viewer
  for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; //convert to octet
  return buf;    
}
</script>
</body>
</html>