package com.mycom.app;

import com.mycom.app.question.entity.Question;
import com.mycom.app.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AppApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa(){
		//더미데이터입력
		for(int i=5;i<=120;i++){
			Question question = new Question();
			question.setSubject("제목"+i);
			question.setContent("내용"+i);
			question.setCreateDate(LocalDateTime.now());
			question.setModifyDate(LocalDateTime.now());
			questionRepository.save(question);

			System.out.println("전체레코드수="+questionRepository.count()); //콘솔출력
		}

		//1개의 질문 등록
	/*	Question question = new Question();
		question.setSubject("질문제목1");
		question.setContent("질문내용1");
		question.setCreateDate(LocalDateTime.now());
		questionRepository.save(question);

		//1개의 질문 등록
		Question question2 = new Question();
		question2.setSubject("질문제목2");
		question2.setContent("질문내용2");
		question2.setCreateDate(LocalDateTime.now());
		questionRepository.save(question2);*/

		//assertEquals(예측값,결과값);
		//예측값과 결과값이 일치하면 true반환=>test가 성공의미
		//예측값과 결과값이 불일치하면 true가 fail의미

		//질문 목록 조회 : findAll()
	/*	List<Question> questionList = questionRepository.findAll();
		assertEquals(15,questionList.size()); //success

		Question question = questionList.get(0); //전체목록에서 첫 번째 행을 가져옴.
		assertEquals("질문제목1",question.getSubject()); //success
		//assertEquals("~1",question.getSubject()); //fail*/

		//상세 조회 : findById()
		//findById(3)은 Question테이블에서 id가 3인 Question Entity 가져오기
		//Optional클래스 : java8에서 도입. import java.util.Optional클래스
		//null처리를 보다 확실하게 다룰 수 있는 여러 기능을 제공, 예외처리
		//NulllpointException발생 방지에 도움
		//isPresent() : 객체가 값이 존재하면 true반환, 그렇지 않으면 false반환
	/*	Optional<Question> optionalQuestion = questionRepository.findById(3);
		if(optionalQuestion.isPresent()){ //id가 3인 Question Entity클래스가 존재하면
			Question question = optionalQuestion.get(); //Question Entity클래스 가져오기
			assertEquals("질문제목1",question.getSubject()); //성공예측
			//assertEquals("질문제목11",question.getSubject()); //실패예측
		}*/

	/*	//QuestionRepository에 선언한 findbySubject()테스트
		//questionRepository.findBySubject("질문제목1") : 제목이 "질문제목1"인 Question Entity가져오기
		//question2.getId : 반환받은 Question Entity에서 id를 가져오기 => 글번호라는 의미
		Question question2 = questionRepository.findBySubject("질문제목1");
		//지금 현재의 db기준으로 id(글번호)는 3이다.
		//assertEquals(3,question2.getId()); //성공예측
		assertEquals(10,question2.getId()); //실패예측*/

	/*	//아래는 question테이블의 제목(subject)컬럼값의 어딘가에 제목1이 포함된 레코드를 조회
		//where x.firstname like %?%
		//List<Question> findBySubjectLike(String subject);
		List<Question> questionList2 = questionRepository.findBySubjectLike("%제목1%");
		//아래 그 결과의 레코드수가 3이라고 예측
		assertEquals(3,questionList2.size()); //성공예측
		
		//아래는 그 결과에서 첫번째 레코드의 id컬럼의 값이 3이라고 예측했으나, 실제로 1이 반환되어 실패
		assertEquals(3,questionList2.get(0).getId()); //실패예측*/

		//수정
	/*	Optional<Question> optionalQuestion2 = questionRepository.findById(3);
		if(optionalQuestion2.isPresent()) { //id가 3인 Question Entity클래스가 존재하면
			Question question2 = optionalQuestion2.get(); //Question Entity클래스 가져오기
			//assertEquals("질문제목1",question2.getSubject()); //성공예측
			question2.setSubject("변경된질문제목1");
			questionRepository.save(question2);
			assertEquals("변경된질문제목1",question2.getSubject()); //성공예측
			assertEquals("질문제목1",question2.getSubject()); //실패예측
		}*/

		//삭제
		//long count(); //레코드수 조회
		//void delete(T entity); //삭제
	/*	System.out.println("삭제전레코드수="+questionRepository.count()); //콘솔출력
		assertEquals(6,questionRepository.count()); //삭제전레코드수 성공예측
		Optional<Question> optionalQuestion3 = questionRepository.findById(2);
		assertTrue(optionalQuestion3.isPresent()); {
			//글번호가 2인 레코드가 존재하면 true => 리턴받은 값이 true이면 성공
			Question question3 = optionalQuestion3.get();
			questionRepository.delete(question3);
			assertEquals(5,questionRepository.count()); //삭제후레코드수 성공예측
			System.out.println("삭제후레코드수="+questionRepository.count()); //콘솔출력
		}*/
	}
}
