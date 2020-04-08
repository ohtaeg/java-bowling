package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AnswersTest {
    private Answer answer;
    private Answer otherAnswer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        otherAnswer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
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
        Answers answers = new Answers(Arrays.asList(answer, answer));

        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(
                () -> answers.checkDeletable(User.GUEST_USER)
        );
    }
}