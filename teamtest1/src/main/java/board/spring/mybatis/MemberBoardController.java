package board.spring.mybatis;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberBoardController {

		@Autowired
		MemberService service;
		@GetMapping("/boardlogin")
		public String loginform() {
			return "board/loginform";
		}
		@PostMapping("/boardlogin")
		public String loginprocess(String memberid, int pw, HttpSession session) { //Controller는 내장 HttpSession 있어서 매개변수로 추가해서 씀  request.getSession();
			//1 c_member id, pw 확인
			MemberDTO dto = service.oneMember(memberid);
			if(dto != null) {
				if(dto.getPw() == pw) {
					//2 HttpSession 객체 "sessionid" :
					session.setAttribute("sessionid", memberid);
				}
				else {
					//암호가 다르면 암호 다시 입력해야 한다.
					//session.setAttribute("sessionid", "암호 다시 입력해야 한다.");
				}
			}
			else {
				//id 회원가입부터 해야한다.
				//session.setAttribute("sessionid", "회원가입부터 해야한다.");
			}
			
			
			
			return "board/start";
		}
		
		@RequestMapping("/boardlogout")
		public String logout(HttpSession session) {
			if(session.getAttribute("sessionid") != null)
			{			
				session.removeAttribute("sessionid");
			}
			
			return "board/start";
		}
}
