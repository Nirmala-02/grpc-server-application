package org.example.service;

import io.grpc.stub.StreamObserver;
import org.customer.details.Customerdetails;
import org.example.repository.CustomerProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class CustomerDetailsServiceImpl extends org.customer.details.CustomerDetailsGrpc.CustomerDetailsImplBase {

    @Autowired
    CustomerProfileRepo customerRepo;
    @Override
    public void getCustomerDetails(Customerdetails.CustomerRequest request, StreamObserver<Customerdetails.CustomerResponse> responseObserver) {





    }
}
