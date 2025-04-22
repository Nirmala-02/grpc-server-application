package org.example.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.customer.details.CustomerDetailsGrpc;
import org.customer.details.Customerdetails;
import org.example.model.CustomerProfileData;
import org.example.repository.CustomerProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.Date;

@GrpcService
public class CustomerProfileService extends CustomerDetailsGrpc.CustomerDetailsImplBase {

    @Autowired
    CustomerProfileRepo customerProfileRepo;
    @Override
    public void getCustomerProfile(Customerdetails.CustomerProfileRequest request, StreamObserver<Customerdetails.CustomerProfileResponse> responseObserver) {

        CustomerProfileData response = customerProfileRepo.findById(request.getCustomerId()).orElseThrow();

        Customerdetails.CustomerProfileResponse profileResponse = Customerdetails.CustomerProfileResponse.newBuilder()
                .setCustomerId(response.getCustomerId())
                .setFirstName(response.getFirstName())
                .setLastName(response.getLastName())
                .setEmail(response.getEmail())
                .setPhoneNumber(response.getPhoneNumber())
                .setAddress(response.getAddress())
                .setCustomerSegment(response.getCustomerSegment())
                .setCreatedDate(dateToTimestamp(response.getCreatedDate()))
                .build();

        responseObserver.onNext(profileResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void setCustomerProfile(Customerdetails.CustomerProfileAdd request, StreamObserver<Customerdetails.CustomerProfileAddedResponse> responseObserver) {
        CustomerProfileData response = customerProfileRepo.save(convertToCustomerProfileData(request));

        Customerdetails.CustomerProfileAddedResponse streamResponse = Customerdetails.CustomerProfileAddedResponse.newBuilder()
                                                                      .setResponse("Customer profile details added successfully.")
                                                                      .build();
        responseObserver.onNext(streamResponse);
        responseObserver.onCompleted();
    }

    public CustomerProfileData convertToCustomerProfileData(Customerdetails.CustomerProfileAdd request){
        CustomerProfileData profileDetails = new CustomerProfileData();
        profileDetails.setCustomerId(request.getCustomerId());
        profileDetails.setFirstName(request.getFirstName());
        profileDetails.setLastName(request.getLastName());
        profileDetails.setEmail(request.getEmail());
        profileDetails.setPhoneNumber(request.getPhoneNumber());
        profileDetails.setAddress(request.getAddress());
        profileDetails.setCustomerSegment(request.getAddress());
        profileDetails.setCreatedDate(timestampToDate(request.getCreatedDate()));
        return profileDetails;
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
