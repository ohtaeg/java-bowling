package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {
    private User user;
    private Question question;
    private Answer answer;
    private Answers answers;

    @BeforeEach
    void setUp() {
        user = new User(1L, "javajigi", "aa", "aa", "aa");
        question = new Question("title1", "contents1").writeBy(user);
        answer = new Answer(user, QuestionTest.Q1, "Answers Contents1");
        answers = new Answers(Arrays.asList(answer));
    }

    @DisplayName("질문을 삭제하면 삭제 히스토리 일급 객체를 생성할 수 있다.")
    @Test
    void create() {
        LocalDateTime expect = LocalDateTime.of(2019, 4, 11, 1, 52);
        question.addAnswer(answer);
        DeleteHistories deleteHistories = question.delete(user, expect);

        final List<DeleteHistory> actual = deleteHistories.getDeleteHistories();

        assertThat(actual).hasSize(2);
    }
}