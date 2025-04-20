package org.example.service;

import io.grpc.stub.StreamObserver;
import org.customer.details.Customerdetails;
import org.example.model.CustomerDetailsResponse;
import org.example.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class CustomerDetailsServiceImpl extends org.customer.details.CustomerDetailsGrpc.CustomerDetailsImplBase {

    @Autowired
    CustomerRepo customerRepo;
    @Override
    public void getCustomerDetails(Customerdetails.CustomerRequest request, StreamObserver<Customerdetails.CustomerResponse> responseObserver) {

        Optional<CustomerDetailsResponse> optionalResponse = customerRepo.findById(request.getCustomerId());
        CustomerDetailsResponse response = optionalResponse.get();

        Customerdetails.CustomerResponse streamResponse = Customerdetails.CustomerResponse.newBuilder()
                                                         .setCustomerId(response.getCustomer_id())
                                                         .setCustomerName(response.getCustomer_name())
                                                         .setAccType(response.getAcc_type())
                                                         .setBalance(response.getBalance())
                                                         .build();
        responseObserver.onNext(streamResponse);
        responseObserver.onCompleted();

    }
}
