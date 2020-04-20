package com.pusulait.grpc;

import com.pusulait.grpc.lesson.*;
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

    public RecordResultEnum record2(Student student, Classroom classroom) {

        log.info("client sending {}", student);

        RecordRequest recordRequest = RecordRequest.newBuilder()
                .setStudent(student)
                .setClassroom(classroom)
                .build();

        RecordResult recordResult = recordServiceBlockingStub.record2(recordRequest);
        log.info("client received {}", recordResult.getRecordResultEnum());

        return recordResult.getRecordResultEnum();
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
