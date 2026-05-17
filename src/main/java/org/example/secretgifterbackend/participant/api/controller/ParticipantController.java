package org.example.secretgifterbackend.participant.api.controller;

import org.example.secretgifterbackend.participant.api.request.CreateParticipantRequest;
import org.example.secretgifterbackend.participant.api.response.ParticipantResponse;
import org.example.secretgifterbackend.participant.service.ParticipantService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ParticipantResponse create(@RequestBody CreateParticipantRequest request) {
        return participantService.createParticipant(request);
    }

    @GetMapping
    public List<ParticipantResponse> getParticipantsByRoomId(@RequestParam Integer roomId) {
        return participantService.getByRoomId(roomId);
    }
    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable Integer id) {
        participantService.deleteParticipant(id);
    }


}
