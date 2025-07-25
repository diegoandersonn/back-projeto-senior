package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Transfer;
import com.example.projeto_spring.dto.transfer.TransferListDto;
import com.example.projeto_spring.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transfers")
public class TransferController {

    @Autowired
    TransferRepository transferRepository;

    @GetMapping
    public ResponseEntity list() {
        List<Transfer> transfers = transferRepository.findAll();
        return ResponseEntity.ok(transfers.stream().map(TransferListDto::new).toList());
    }
}
