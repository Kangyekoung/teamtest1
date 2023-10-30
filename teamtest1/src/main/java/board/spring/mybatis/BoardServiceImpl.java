package board.spring.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao;
	
	@Override
	public int inserBoard(BoardDTO dto) {
		return dao.insertBoard(dto);
	}

	@Override
	public int getTotalBoard() {
		return  dao.getTotalBoard();
	}
	
	@Override
	public List<BoardDTO> boardList(int[] limit) {
		return dao.boardList(limit);
	}
	
	@Override
	public BoardDTO updateViewcountAndGetDetail(int seq) {
		int updaterows = dao.updateViewcount(seq);
		return dao.getDetail(seq);
	}
	
	@Override
	public int deleteboard(int seq) {
		return dao.deleteBoard(seq);
	}
	
	@Override
	public int updateBoard(BoardDTO dto) {
		return dao.updateBoard(dto);
	}
}
