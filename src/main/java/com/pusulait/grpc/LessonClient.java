package com.pusulait.grpc;

import com.pusulait.grpc.lesson.RecordResult;
import com.pusulait.grpc.lesson.RecordResultEnum;
import com.pusulait.grpc.lesson.RecordServiceGrpc;
import com.pusulait.grpc.lesson.Student;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class LessonClient {

    private RecordServiceGrpc.RecordServiceBlockingStub recordServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                                        .forAddress("localhost", 6565)
                                        .usePlaintext().build();

        recordServiceBlockingStub = RecordServiceGrpc.newBlockingStub(managedChannel);
    }

    public RecordResultEnum record(String firstName, String lastName, Integer age) {

        Student student = Student.newBuilder()
                                .setFirstName(firstName)
                                .setLastName(lastName)
                                .setAge(15)
                                .build();

        log.info("client sending {}", student);

        RecordResult recordResult = recordServiceBlockingStub.record(student);
        log.info("client received {}", recordResult.getRecordResultEnum());

        return recordResult.getRecordResultEnum();
    }

}
