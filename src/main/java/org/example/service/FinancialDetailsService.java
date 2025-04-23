package org.example.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.customer.details.FinancialDetailsGrpc;
import org.customer.details.Financialdetails;
import org.example.model.FinancialData;
import org.example.repository.FinancialDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.Date;

//@GrpcService
@Service
public class FinancialDetailsService extends FinancialDetailsGrpc.FinancialDetailsImplBase {

    @Autowired
    FinancialDetailsRepo financialDetailsRepo;

    public Financialdetails.FinancialDetailsResponse getFinancialDetails(Financialdetails.FinancialDetailsRequest request) {

        FinancialData response = financialDetailsRepo.findById(request.getCustomerId()).orElseThrow();

        Financialdetails.FinancialDetailsResponse financialDetailsResponse = Financialdetails.FinancialDetailsResponse.newBuilder()
                .setAccountId(response.getAccountId())
                .setCustomerId(response.getCustomerId())
                .setAccountType(response.getAccountType())
                .setAccountNumber(response.getAccountNumber())
                .setBalance(response.getBalance())
                .setStatus(response.getStatus())
                .setOpenDate(dateToTimestamp(response.getOpenDate()))
                .build();

        return financialDetailsResponse;
    }

    public Date timestampToDate(Timestamp timestamp) {
        return new Date(timestamp.getSeconds() * 1000L + timestamp.getNanos() / 1_000_000);
    }

    public Timestamp dateToTimestamp(Date date) {
        long millis = date.getTime();
        return Timestamp.newBuilder()
                .setSeconds(millis / 1000)
                .setNanos((int) ((millis % 1000) * 1_000_000))
                .build();
    }
}
