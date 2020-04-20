package com.pusulait.grpc;

import com.pusulait.grpc.lesson.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
public class LessonServiceImpl extends RecordServiceGrpc.RecordServiceImplBase {

    @Override
    public void record(Student request, StreamObserver<RecordResult> responseObserver) {

        log.info("server received {}", request);
        RecordResult result;
        if (request.getAge() > 17) {
            result = RecordResult.newBuilder().setRecordResultEnum(RecordResultEnum.FAIL).build();
        } else {
            result = RecordResult.newBuilder().setRecordResultEnum(RecordResultEnum.SUCCESS).build();
        }
        log.info("server responded {}", result);
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void record2(RecordRequest request, StreamObserver<RecordResult> responseObserver) {

        log.info("server received {}", request);
        RecordResult result = null;
        Student student = request.getStudent();
        Classroom classroom = request.getClassroom();

        if (!classroom.getStudentsList().contains(student)){
            if (student.getAge() > 17) {
                result = RecordResult.newBuilder().setRecordResultEnum(RecordResultEnum.FAIL).build();
            } else {
                classroom.getStudentsList().add(student);
                result = RecordResult.newBuilder().setRecordResultEnum(RecordResultEnum.SUCCESS).build();
            }
        }

        log.info("server responded {}", result);
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
