package org.example.service;

import io.grpc.stub.StreamObserver;
import org.customer.details.Transactiondetails;
import org.customer.details.TransctionDetailsGrpc;
import org.example.model.TransactionData;
import org.example.repository.TransactionDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

//@GrpcService
@Service
public class TransactionDetailsService extends TransctionDetailsGrpc.TransctionDetailsImplBase {

    @Autowired
    TransactionDetailsRepo transactionDetailsRepo;

    public Transactiondetails.TransactionDetailsResponse getTransactionDetails(Transactiondetails.TransactionDetailsRequest request) {
        TransactionData response = transactionDetailsRepo.findById(request.getCustomerId()).orElseThrow();
        Transactiondetails.TransactionDetailsResponse transactionResponse = Transactiondetails.TransactionDetailsResponse.newBuilder()
                .setTransactionId(response.getTransactionId())
                .setCustomerId(response.getCustomerId())
                .setAccountId(response.getAccountId())
                .setType(response.getType())
                .setAmount(response.getAmount())
                .setDescription(response.getDescription())
                .build();
        return transactionResponse;
    }
}
