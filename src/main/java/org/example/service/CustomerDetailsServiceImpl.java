package org.example.service;

import io.grpc.stub.StreamObserver;
import org.customer.details.*;
import org.example.model.CustomerProfileData;
import org.example.model.FinancialData;
import org.example.model.InteractionData;
import org.example.model.PreferenceData;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class CustomerDetailsServiceImpl extends CustomerOrchestrarionDetailsGrpc.CustomerOrchestrarionDetailsImplBase {

    @Autowired
    CustomerProfileService customerProfileService;
    @Autowired
    FinancialDetailsService financialDetailsService;
    @Autowired
    InteractionDetailsService interactionDetailsService;
    @Autowired
    PreferenceDetailsService preferenceDetailsService;
    @Autowired
    TransactionDetailsService transactionDetailsService;

    @Override
    public void getCustomerOrchestration(Customerorchestration.CustomerRequest request, StreamObserver<Customerorchestration.CustomerOrchestrationResponse> responseObserver) {

        Customerorchestration.CustomerOrchestrationResponse customerOrchestrationResponse = Customerorchestration.CustomerOrchestrationResponse.newBuilder()
                .setCustomerProfile(getCustomerProfile(request))
                .setFinancialDetails(getFinancialDetails(request))
                .setInteractionDetails(getInteractionDetails(request))
                .setPreferenceDetails(getPreferenceDetails(request))
                .setTransactionDetails(getTransactionDetails(request))
                .build();
        responseObserver.onNext(customerOrchestrationResponse);
        responseObserver.onCompleted();

//        Customerdetails.CustomerProfileResponse customerProfileResponse = customerProfileService.getCustomerProfile(getCustomerProfileRequest(request));
//        Financialdetails.FinancialDetailsResponse financialDetailsResponse = financialDetailsService.getFinancialDetails(getFinancialDetailsRequest(request));
//        Interactiondetails.InteractionDetailsResponse interactionDetailsResponse = interactionDetailsService.getInteractionDetails(getInteractionDetailsRequest(request));
//        Preferencedetails.PreferenceDetailsResponse preferenceDetailsResponse = preferenceDetailsService.getPreferenceDetails(getPreferenceDetailsRequest(request));
//        Transactiondetails.TransactionDetailsResponse transactionDetailsResponse = transactionDetailsService.getTransactionDetails(getTransactionDetailsRequest(request));

    }

    public Customerdetails.CustomerProfileResponse getCustomerProfile(Customerorchestration.CustomerRequest request){
        Customerdetails.CustomerProfileRequest customerProfileRequest = Customerdetails.CustomerProfileRequest.newBuilder()
                .setCustomerId(request.getCustomerId())
                .build();
        return customerProfileService.getCustomerProfile(customerProfileRequest);
    }

    public Financialdetails.FinancialDetailsResponse getFinancialDetails(Customerorchestration.CustomerRequest request){
        Financialdetails.FinancialDetailsRequest financialDetailsRequest = Financialdetails.FinancialDetailsRequest.newBuilder()
                .setCustomerId(request.getCustomerId())
                .build();
        return financialDetailsService.getFinancialDetails(financialDetailsRequest);

    }

    public Interactiondetails.InteractionDetailsResponse getInteractionDetails(Customerorchestration.CustomerRequest request){
        Interactiondetails.InteractionDetailsRequest interactionDetailsRequest = Interactiondetails.InteractionDetailsRequest.newBuilder()
                .setCustomerId(request.getCustomerId())
                .build();
        return interactionDetailsService.getInteractionDetails(interactionDetailsRequest);

    }

    public Preferencedetails.PreferenceDetailsResponse getPreferenceDetails(Customerorchestration.CustomerRequest request){
        Preferencedetails.PreferenceDetailsRequest preferenceDetailsRequest = Preferencedetails.PreferenceDetailsRequest.newBuilder()
                .setCustomerId(request.getCustomerId())
                .build();
        return preferenceDetailsService.getPreferenceDetails(preferenceDetailsRequest);

    }

    public Transactiondetails.TransactionDetailsResponse getTransactionDetails(Customerorchestration.CustomerRequest request){
        Transactiondetails.TransactionDetailsRequest transactionDetailsRequest = Transactiondetails.TransactionDetailsRequest.newBuilder()
                .setCustomerId(request.getCustomerId())
                .build();
        return transactionDetailsService.getTransactionDetails(transactionDetailsRequest);
    }

}

