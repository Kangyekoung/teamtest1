package board.spring.mybatis;

import java.util.ArrayList;
import java.util.List;


public interface BoardService {
	public int inserBoard(BoardDTO dto);
	public int getTotalBoard();
	public List<BoardDTO>boardList(int[] limit);
	public BoardDTO updateViewcountAndGetDetail(int seq);
	public int deleteboard(int seq);
	public int updateBoard(BoardDTO dto);
}
