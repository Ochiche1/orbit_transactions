package com.orbit.transaction.controller;

import com.orbit.transaction.inward.dto.request.FundTransferRequest;
import com.orbit.transaction.inward.model.TransactionProperties;
import com.orbit.transaction.inward.services.BalanceEnquiryService;
import com.orbit.transaction.inward.services.TransactionServices;
import com.orbit.transaction.outward.repository.TSQOutwardOps;
import com.orbit.transaction.outward.services.FTDirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ServiceController {
private final TransactionServices transactionServices;
private final TSQOutwardOps tsqOutwardOps;
private final BalanceEnquiryService balanceEnquiryService; ;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer( @RequestBody TransactionProperties properties)  {
        return ResponseEntity.ok(tsqOutwardOps.postTransactions(properties));
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<?> getBalance(@PathVariable String accountNumber)  {
        return ResponseEntity.ok(balanceEnquiryService.getBalance(accountNumber));
    }
}
