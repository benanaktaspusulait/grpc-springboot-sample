package com.pusulait;

import static org.assertj.core.api.Assertions.assertThat;
import com.pusulait.grpc.LessonClient;
import com.pusulait.grpc.lesson.RecordResultEnum;
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
  public void testSayHello() {
    assertThat(lessonClient.record("Eren", "Toprak", 15)).isEqualTo(RecordResultEnum.SUCCESS);
  }



}
