package org.example.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.customer.details.InteractionDetailsGrpc;
import org.customer.details.Interactiondetails;
import org.example.model.InteractionData;
import org.example.repository.InteractionDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.util.Date;

//@GrpcService
@Service
public class InteractionDetailsService extends InteractionDetailsGrpc.InteractionDetailsImplBase {

    @Autowired
    InteractionDetailsRepo interactionDetailsRepo;

    public Interactiondetails.InteractionDetailsResponse getInteractionDetails(Interactiondetails.InteractionDetailsRequest request) {
        InteractionData response = interactionDetailsRepo.findById(request.getCustomerId()).orElseThrow();

        Interactiondetails.InteractionDetailsResponse interactionResponse = Interactiondetails.InteractionDetailsResponse.newBuilder()
                .setInteractionId(response.getInteractionId())
                .setCustomerId(response.getCustomerId())
                .setChannel(response.getChannel())
                .setType(response.getType())
                .setTimestamp(dateToTimestamp(response.getTimestamp()))
                .setDescription(response.getDescription())
                .setStatus(response.getStatus())
                .build();

        return interactionResponse;
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
