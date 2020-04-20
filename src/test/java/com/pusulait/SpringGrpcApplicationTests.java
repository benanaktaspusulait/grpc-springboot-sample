package com.pusulait;

import static org.assertj.core.api.Assertions.assertThat;
import com.pusulait.grpc.LessonClient;
import com.pusulait.grpc.lesson.Classroom;
import com.pusulait.grpc.lesson.RecordResultEnum;
import com.pusulait.grpc.lesson.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringGrpcApplicationTests {

  @Autowired
  private LessonClient lessonClient;

  @Test
  public void testRecord() {

    assertThat(lessonClient.record("Eren", "Toprak", 15)).isEqualTo(RecordResultEnum.SUCCESS);
  }


  @Test
  public void testRecord2() {

    Student student = Student
            .newBuilder()
            .setFirstName("Eren")
            .setLastName("Aktas")
            .setAge(15)
            .build();

    Classroom classroom = Classroom.newBuilder()
            .setName("1-D")
            .setLessonCode("301")
            .build();

    assertThat(lessonClient.record2(student, classroom)).isEqualTo(RecordResultEnum.SUCCESS);
  }


}
