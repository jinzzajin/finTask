<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/member/modify" method="post">
	<input type="hidden" value="${ member.memberNo}" name="befMemberNo" name ="befMemberNo">
		<table>
			<tr>
				<td>직원번호</td>
				<td>
					<input type="text" id="memberNo" name="memberNo" oninput="numChek(this)" value="${member.memberNo }"  maxlength="3" >
					<div id="NoChk"></div>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="memberName"  id="memberName" value="${member.memberName }" ></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td> <input type="text" name="memberTel" id="memberTel" oninput="telAuto(this)" maxlength="13"  value="${member.memberTel }" ></td>
			</tr>
			<tr>
				<td>직급</td>
				<td>
					<select name="memberPo" id="memberPo">
						 <option value="">직급선택</option>
					    <option value="사원">사원</option>
					    <option value="대리">대리</option>
					    <option value="과장">과장</option>
					    <option value="팀장">팀장</option>
					    <option value="상무">상무</option>
					    <option value="임원">임원</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>이메일주소</td>
				<td><input type="email" name="memberEmail" id="memberEmail"   value="${member.memberEmail }" ></td>
			</tr>
			<tr>
				<td><input type="submit" value="수정" ></td>
				<td><input type="button" onclick="deleteMember(${member.memberNo})" value="삭제" ></td>
			</tr>
		</table>	
	</form>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">	

/*직원정보 삭제*/
function deleteMember(memberNo){
	if(confirm("직원정보를 삭제하시겠습니까?")){
		location.href="/member/delete?memberNo="+memberNo;
	}
	
	
}

/*셀렉트박스 미리선택*/
	$("#memberPo").val("${member.memberPo}").prop("selected", true);
	
/* 직원번호 중복체크 ajax, 유효성체크 : 중복아니고 조건충족시 통과 */
$('#memberNo').keyup(function(){
	let memberNo = $('#memberNo').val();
	let check1=0;
	$.ajax({
		url : "/member/memberNoCheck",
		type : "post",
		data : {memberNo: memberNo},
		dataType : 'json',
		success : function(result){
			if(result == 1){
				$("#NoChk").html('이미 사용중인 직원번호 입니다.');
				$("#NoChk").css('color','red');
				
			} else if(memberNo==""){
				$("#NoChk").html('직원번호를 입력해 주세요');
				$("#NoChk").css('color','black');
			}else{
				$("#NoChk").html('사용할 수 있는 직원번호 입니다.');
				$("#NoChk").css('color','blue');
				let check1=1;
			} 
		},
		error : function(){
			
		}
	})
		 
})
const numChek = (target) => {
	target.value = target.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
}
/* 전화번호 하이픈 자동입력 */
const telAuto = (target) => {
 target.value = target.value
   .replace(/[^0-9]/g, '')
  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}

/* 전화번호 유효성체크 : 13자리 모두 입력시 통과 */
let check2=0;
$('#memberTel').keyup(function(){
	if($('#memberTel').val().length==13){
		check2=1;
	}else{
		check2=0;
	}
	console.log(check2);
});

//내용기입 안된경우, 유효성검사에 통과하지 못했을 경우 제출안됨. 포커스 자동이동
document.querySelector('form').addEventListener('submit', function(e){
    if(check1 == 0){
      	e.preventDefault()     
    	$('#memberNo').focus();   
    }
    if($('#memberName').val() == ''){
      	e.preventDefault() 
   		$('#memberName').focus(); 	
    } 	
    if(check2 == 0) {
     	e.preventDefault()     
      	$('#memberTel').focus();
	}
    if($('#memberPo').val() == ''){
      	e.preventDefault()      
   		$('#memberPo').focus(); 	
    }
});
</script>
</body>
</html>