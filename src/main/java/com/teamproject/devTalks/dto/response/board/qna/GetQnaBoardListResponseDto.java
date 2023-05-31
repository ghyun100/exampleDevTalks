package com.teamproject.devTalks.dto.response.board.qna;

import java.util.ArrayList;
import java.util.List;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.resultSet.QnaBoardListResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetQnaBoardListResponseDto extends ResponseDto {

	private List<BoardSummary> qnaBoardList; // B

	public GetQnaBoardListResponseDto(List<QnaBoardListResultSet> resultSets) { // A

		super("SU", "Success");

		this.qnaBoardList = new ArrayList<>(); // 빈 B리스트를 만든다

		// A를 반복하며 돈다
		for (QnaBoardListResultSet resultSet : resultSets) { // A에 있는 요소를 하나씩 꺼낸다

			BoardSummary boardSummary = new BoardSummary(resultSet); // A로 B를 만든다
			this.qnaBoardList.add(boardSummary); // 복사한 B를 B배열에 추가한다
		}
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class BoardSummary {
	private int quaBoardNumber;
	private String qnaTitle;
	private String qnaBoardImageUrl;
	private String writeDatetime;
	private int viewCount;
	private String writerEmail;
	private String writerNickname;
	private String writerProfileImageUrl;
	private int commentCount;
	private int heartCount;

	public BoardSummary(QnaBoardListResultSet resultSet) {

		this.quaBoardNumber = resultSet.getQnaBoardNumber();
		this.qnaTitle = resultSet.getQnaTitle();
		this.qnaBoardImageUrl = resultSet.getQnaBoardImageUrl();
		this.writeDatetime = resultSet.getWriteDatetime();
		this.viewCount = resultSet.getViewCount();
		this.writerEmail = resultSet.getWriterEmail();
		this.writerNickname = resultSet.getWriterNickname();
		this.writerProfileImageUrl = resultSet.getWriterProfileImageUrl();
		this.commentCount = resultSet.getQnaCommentCount();
		this.heartCount = resultSet.getQnaHeartCount();

	}

}
