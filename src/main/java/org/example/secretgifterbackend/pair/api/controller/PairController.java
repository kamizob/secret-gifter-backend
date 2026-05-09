package org.example.secretgifterbackend.pair.api.controller;

import jakarta.validation.Valid;
import org.example.secretgifterbackend.pair.api.request.GeneratePairsRequest;
import org.example.secretgifterbackend.pair.api.request.RevealPairRequest;
import org.example.secretgifterbackend.pair.api.response.PairResponse;
import org.example.secretgifterbackend.pair.api.response.RevealPairResponse;
import org.example.secretgifterbackend.pair.service.PairService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/pairs")
public class PairController {
    private final PairService pairService;

    public PairController(PairService pairService) {
        this.pairService = pairService;
    }
    @PostMapping("/generate")
    public List<PairResponse> generate(@Valid @RequestBody GeneratePairsRequest request) {
        return pairService.generatePairs(request.roomId());
    }
    @GetMapping("/{roomId}")
    public List<PairResponse> getPairs(@PathVariable("roomId") Integer roomId) {
        return pairService.getPairs(roomId);
    }
    @GetMapping("/reveal")
    public RevealPairResponse reveal(
            @RequestParam UUID publicId,
            @RequestParam Integer roomId
    ) {
        return pairService.reveal(publicId, roomId);
    }

}
