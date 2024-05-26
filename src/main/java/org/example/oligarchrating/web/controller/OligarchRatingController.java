package org.example.oligarchrating.web.controller;

import org.example.oligarchrating.domain.entities.FinancialAssets;
import org.example.oligarchrating.domain.entities.OligarchEntity;
import org.example.oligarchrating.domain.entities.PersonEntity;
import org.example.oligarchrating.domain.service.OligarchRatingService;
import org.example.oligarchrating.web.dto.OligarchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oligarch-rating")
public class OligarchRatingController {

    @Autowired
    private OligarchRatingService oligarchRatingService;

    @PostMapping("/oligarch")
    public ResponseEntity<OligarchEntity> oligarch (@RequestBody OligarchRequest oligarchRequest) throws ChangeSetPersister.NotFoundException {
        PersonEntity personEntity = convertToEntity(oligarchRequest);
        OligarchEntity savedOligarch = oligarchRatingService.save(personEntity);
        return ResponseEntity.ok(savedOligarch);
    }

    @GetMapping("/oligarch/all")
    public List<OligarchEntity> getAllOligarchs() {
        return oligarchRatingService.getAllOligarchs();
    }

    @GetMapping("/oligarch/{id}")
    public ResponseEntity<OligarchEntity> getOligarchById(@PathVariable Long id) {
        OligarchEntity oligarch = oligarchRatingService.getOligarchById(id);
        return ResponseEntity.ok(oligarch);
    }

    @GetMapping("/oligarch/rank/{id}")
    public ResponseEntity<String> getOligarchRank(@PathVariable Long id) {
        int oligarchRank = oligarchRatingService.getOligarchRank(id);
        return ResponseEntity.ok("PersonEntity ID:" + id + " is ranked " + oligarchRank + " in the world’s oligarchs");
    }


    private PersonEntity convertToEntity(OligarchRequest oligarchRequest) {
        return new PersonEntity(
                oligarchRequest.getId(),
                oligarchRequest.getPersonInformation().getFirstName(),
                oligarchRequest.getPersonInformation().getLastName(),
                new FinancialAssets(oligarchRequest.getFinancialAssets().getCashAmount(),
                        oligarchRequest.getFinancialAssets().getCurrency(),
                        oligarchRequest.getFinancialAssets().getBitcoinAmount()));

    }
}
