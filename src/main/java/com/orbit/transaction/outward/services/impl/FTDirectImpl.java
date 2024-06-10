package com.orbit.transaction.outward.services.impl;

import com.orbit.transaction.inward.dto.request.FundTransferRequest;
import com.orbit.transaction.inward.dto.response.FundTransferResponse;
import com.orbit.transaction.inward.model.NIPRequestType;
import com.orbit.transaction.outward.repository.FTServices;
import com.orbit.transaction.outward.services.FTDirectService;
import com.orbit.transaction.outward.services.TSQOutwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class FTDirectImpl implements FTDirectService {
    private final FTServices ftServices;
    private final TSQOutwardService tsqOutwardService;

    public static final int TIME_DELAY_IN_MILLI_SECONDS = 10_000;
    @Override
    public boolean savedFundTransfer(FundTransferResponse response, NIPRequestType inward) {
        return ftServices.hasSavedFundTransfer(response, inward);

    }

    @Override
    public FundTransferResponse performFundTransferDirectCredit(FundTransferRequest request) {
        FundTransferResponse response = new FundTransferResponse();
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Thread.sleep(TIME_DELAY_IN_MILLI_SECONDS);
                tsqOutwardService.transactionStatusQuery(response, NIPRequestType.OUTWARD);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return response;
    }
}
