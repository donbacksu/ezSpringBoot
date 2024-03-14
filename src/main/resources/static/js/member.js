/**
 * 약관 동의 여부
 */
function go_next() {
	if ($(".agree")[0].checked == true) {	// 동의함이 체크됨
		$("#join").attr("action", "join_form").submit();	// 서버로 URL 전송
	} else if($(".agree")[1].checked == true){
		alert("약관에 동의하셔야 가입 할 수 있습니다.")
	}
}

/*
 * 회원 가입시, 필수 입력 항목 확인
 */
function go_save() {
	if ($("#id").val() == "") {
		alert("아이디를 입력해 주세요!");
		$("#id").focus();
		return false;
	} else if($("#id").val() != $("#reid").val()) {
		alert("아이디 중복 체크를 해주세요!");
		$("#id").focus();
		return false;
	} else if ($("#pwd").val() == "") {
		alert("비밀번호를 입력해 주세요!");
		$("#pwd").focus();
		return false;
	} else if($("#pwd").val() != $("#pwdCheck").val()) {
		alert("비밀번호가 일치하지 않습니다!");
		$("#pwd").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("이름을 입력해 주세요!");
		return false;
	} else if ($("#email").val() == "") {
		alert("이메일을 입력해 주세요!");
		return false;
	} else {
		// 회원 가입 요청
		$("#join").attr("action", "join").submit();
	}
}

/* id 중복확인 화면 출력 요청*/
function idcheck() {
	if ($("#id").val() == "") {
		alert("아이디를 입력해 주세요!");
        $("#id").focus();
        return false;;
	}
	
	/*id 중복확인 창 오픈*/
	var url = "id_check_form?id="+$("#id").val();
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
			"resizable=yes, width=350, height=200");
}

function post_zip() {
	var url = "find_zip_num";
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
	"resizable=yes, width=500, height=350");
}