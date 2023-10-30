package board.spring.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	@RequestMapping("/")
	public String start() {
		return "board/start";
	}
	
	@GetMapping("/boardwrite")
	public String  writeform() {
		return "board/writingform";
	}
		
	@PostMapping("/boardwrite")
	public ModelAndView wirteprocess(BoardDTO dto) {
		dto.setWriter(dto.getWriter().trim());
		int insertcount = service.inserBoard(dto);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("insertcount", insertcount);
		//mv.setViewName("board/writeresult");
		mv.setViewName("board/start");
		return mv;
		//mv.setViewName("board/list");
		//redirect:/boardlist
	}
	
	@RequestMapping("/boardlist")
	public ModelAndView boardlist(@RequestParam(value= "page", required=false, defaultValue="1") int page) { //파라미터 page가 없을 경우 기본값 1로 지정
		
		//전체 게시물 갯수 (9) 가져와서 몇페이지까지 (1페이지 당 3개 게시물)
		int totalBoard= service.getTotalBoard();
		
		//page번호 해당 게시물 4개 리스트 조회
		int limitcount = 4;
		int limitindex = (page-1)*limitcount;
		int limit[] = new int[2];
		limit[0] = limitindex;
		limit[1] = limitcount;
		
		/* 1. List<BoardDTO> -- 서비스 메소드(limitindex, limitcount,);
		 * 2. board-mapping.xml
		 * select * from board order by writingtime desc limit 배열[0],배열[1]
		 *                                                         
		 * 3. 1번 결과를 모델로 추가 저장
		 * 4. 뷰 3번에서 모델 저장 테이블 태그 출력
		 * */
		List<BoardDTO> list = service.boardList(limit);
		

		ModelAndView mv = new ModelAndView();
		mv.addObject("totalBoard",totalBoard );
		mv.addObject("boardList" , list );
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@RequestMapping("/boarddetail")
	public ModelAndView boarddetail(int seq) {
		ModelAndView mv = new ModelAndView();
		BoardDTO dto =  service.updateViewcountAndGetDetail(seq);
		System.out.println(dto);
		mv.addObject("detaildto", dto);
		mv.setViewName("board/detail");
		
		return mv;
		
	}
	
	@RequestMapping("/boarddelete")
	public String boarddelete(int seq){ //modelx, view만 있다 return String
		//return "board/list"; //list.jsp 이동(모델 전달 없다 - nullpoint.. 500)
		service.deleteboard(seq);
		return "redirect:/boardlist"; //controller의 boardlist 매핑 메소드 실행
	}
	
	@RequestMapping("/boardupdate")
	public String boardupdate(BoardDTO dto) {
		service.updateBoard(dto);
		return "redirect:/boardlist"; 
	}
}
