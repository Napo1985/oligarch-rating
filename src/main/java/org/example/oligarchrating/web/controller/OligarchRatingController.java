package org.example.oligarchrating.web.controller;

import org.example.oligarchrating.domain.dto.FinancialAssets;
import org.example.oligarchrating.domain.model.Oligarch;
import org.example.oligarchrating.domain.dto.Person;
import org.example.oligarchrating.domain.service.OligarchRatingService;
import org.example.oligarchrating.web.dto.FinancialAssetsDto;
import org.example.oligarchrating.web.dto.PersonInformationDto;
import org.example.oligarchrating.web.dto.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/oligarch-rating")
public class OligarchRatingController {

    @Autowired
    private OligarchRatingService oligarchRatingService;

    @PostMapping("/evaluate")
    public ResponseEntity<Oligarch> evaluatePerson(@RequestBody RequestDto requestDto) throws ChangeSetPersister.NotFoundException {
        Person person = convertToEntity(requestDto);
        Oligarch savedOligarch = oligarchRatingService.evaluateAndSavePerson(person);
        return ResponseEntity.ok(savedOligarch);
    }

    @GetMapping("/oligarchs")
    public List<Oligarch> getAllOligarchs() {
        return oligarchRatingService.getAllOligarchs();
    }

    @GetMapping("/oligarchs/{id}")
    public ResponseEntity<Oligarch> getOligarchById(@PathVariable Long id) {
        Optional<Oligarch> oligarch = oligarchRatingService.getOligarchById(id);
        return oligarch.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/oligarchs/rank/{id}")
    public ResponseEntity<String> getOligarchRank(@PathVariable Long id) {
        int oligarchRank = oligarchRatingService.getOligarchRank(id);
        return ResponseEntity.ok("Person ID:" + id + " is ranked " + oligarchRank + " in the world’s oligarchs");
    }


    private Person convertToEntity(RequestDto requestDto) {
        Person person = new Person();

        PersonInformationDto personInformation = requestDto.getPersonInformation();
        person.setId(requestDto.getId());
        person.setFirstName(personInformation.getFirstName());
        person.setLastName(personInformation.getLastName());

        FinancialAssetsDto financialAssets = requestDto.getFinancialAssets();
        FinancialAssets assets = new FinancialAssets();
        assets.setCashAmount(financialAssets.getCashAmount());
        assets.setCurrency(financialAssets.getCurrency());
        assets.setBitcoinAmount(financialAssets.getBitcoinAmount());

        person.setFinancialAssets(assets);

        return person;
    }
}
