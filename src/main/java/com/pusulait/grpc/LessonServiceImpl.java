package com.pusulait.grpc;

import com.pusulait.grpc.lesson.RecordResult;
import com.pusulait.grpc.lesson.RecordResultEnum;
import com.pusulait.grpc.lesson.RecordServiceGrpc;
import com.pusulait.grpc.lesson.Student;
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
}
