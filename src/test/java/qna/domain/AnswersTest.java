package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AnswersTest {
    private User javajigi;
    private User sanjigi;
    private Answer answer;
    private Answer otherAnswer;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        javajigi = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        sanjigi = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        answer = new Answer(javajigi, QuestionTest.Q1, "Answers Contents1");
        otherAnswer = new Answer(sanjigi, QuestionTest.Q1, "Answers Contents2");
        now = LocalDateTime.of(2019, 4, 11, 1, 52);
    }

    @DisplayName("답변자 객체를 생성할 수 있다.")
    @Test
    void create() {
        Answers answers = new Answers(Arrays.asList(answer, otherAnswer));

        assertThat(answers.getAnswers()).containsExactly(answer, otherAnswer);
    }

    @DisplayName("질문자와 답변글의 모든 답변자가 같지 않으면 예외를 발생한다.")
    @Test
    void throwCannotDeleteException() {
        Answers answers = new Answers(Arrays.asList(answer, otherAnswer));

        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(
                () -> answers.delete(javajigi, now)
        );
    }

    @DisplayName("답변글을 삭제할 수 있다.")
    @Test
    void delete() {
        Answers answers = new Answers(Arrays.asList(answer, answer));

        DeleteHistories deleteHistories = answers.delete(javajigi, now);
        List<DeleteHistory> actual = deleteHistories.getDeleteHistories();

        assertThat(actual).hasSize(2);
    }
}