package board.spring.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	@Autowired
	SqlSession session;
	
	
	public int insertBoard(BoardDTO dto) {
		return session.insert("insertBoard", dto);
	}
	
	
	public int getTotalBoard() {
		return session.selectOne("getTotalBoard");
	}
	
	
	public List<BoardDTO> boardList(int[] limit){
		return session.selectList("boardlist", limit);
	}
	
	public int updateViewcount(int seq) {
		return session.update("updateViewcount", seq);
	}
	
	public BoardDTO getDetail(int seq) {
		return session.selectOne("getDetail", seq);
	}
	
	public int deleteBoard(int seq) {
		return session.delete("deleteBoard",seq);
	}
	
	public int updateBoard(BoardDTO dto) {
		return session.update("updateBoard", dto);
	}
}
