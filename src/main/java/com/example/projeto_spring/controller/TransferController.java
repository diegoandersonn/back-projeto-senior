package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Transfer;
import com.example.projeto_spring.dto.transfer.UpdateTransferDto;
import com.example.projeto_spring.dto.transfer.DetailTransferDto;
import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.dto.transfer.ListTransferDto;
import com.example.projeto_spring.repository.TransferRepository;
import com.example.projeto_spring.service.transfer.TransferService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("transfers")
public class TransferController {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    TransferService transferService;

    @PostMapping
    @Transactional
    public ResponseEntity registerTransfer(@RequestBody @Valid RegisterTransferDto dto, UriComponentsBuilder uriBuilder) {
        Transfer transfer = transferService.register(dto);
        var uri = uriBuilder.path("/transfer/{id}").buildAndExpand(transfer.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailTransferDto(transfer));
    }

    @GetMapping
    public ResponseEntity listTransfers() {
        List<Transfer> transfers = transferService.list();
        return ResponseEntity.ok(transfers.stream().map(ListTransferDto::new).toList());
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity listTransferByTeamId(@PathVariable UUID teamId) {
        List<Transfer> transfers = transferService.listByTeamId(teamId);
        return ResponseEntity.ok(transfers.stream().map(ListTransferDto::new).toList());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateTransfer(@PathVariable UUID id, @RequestBody @Valid UpdateTransferDto dto) {
        Transfer transfer = transferService.update(id, dto);
        return ResponseEntity.ok(new DetailTransferDto(transfer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTransfer(@PathVariable UUID id) {
        transferService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
