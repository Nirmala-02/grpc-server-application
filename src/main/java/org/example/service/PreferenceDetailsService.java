package org.example.service;

import io.grpc.stub.StreamObserver;
import org.customer.details.PreferenceDetailsGrpc;
import org.customer.details.Preferencedetails;
import org.example.model.PreferenceData;
import org.example.repository.PreferenceDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

//@GrpcService
@Service
public class PreferenceDetailsService extends PreferenceDetailsGrpc.PreferenceDetailsImplBase {

    @Autowired
    PreferenceDetailsRepo preferenceDetailsRepo;

    public Preferencedetails.PreferenceDetailsResponse getPreferenceDetails(Preferencedetails.PreferenceDetailsRequest request) {
        PreferenceData response = preferenceDetailsRepo.findById(request.getCustomerId()).orElseThrow();

        Preferencedetails.PreferenceDetailsResponse preferenceResponse = Preferencedetails.PreferenceDetailsResponse.newBuilder()
                .setPreferenceId(response.getPreferenceId())
                .setCustomerId(response.getCustomerId())
                .setPreferredLanguage(response.getPreferredLanguage())
                .setPreferredContactMethod(response.getPreferredContactMethod())
                .setMarketingConsent(response.isMarketingConsent())
                .setPaperlessStatements(response.isPaperlessStatements())
                .build();

        return preferenceResponse;
    }
}
