package board.spring.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	SqlSession session;
		

	public MemberDTO oneMember(String id) {
		return session.selectOne("oneMember", id);
	}
	
}




