package org.example.oligarchrating.domain.service;

import org.example.oligarchrating.domain.dto.Person;
import org.example.oligarchrating.domain.exception.DuplicationException;
import org.example.oligarchrating.domain.exception.NotFoundException;
import org.example.oligarchrating.domain.model.Oligarch;
import org.example.oligarchrating.domain.repository.OligarchRepository;
import org.example.oligarchrating.infrastructure.rest.ExternalApiServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OligarchRatingService {

    @Autowired
    private OligarchRepository oligarchRepository;

    @Autowired
    private ExternalApiServiceInterface externalApiService;

    public Oligarch evaluateAndSavePerson(Person person){

        BigDecimal cashValue = externalApiService.evaluateCash(person.getFinancialAssets().getCashAmount(), person.getFinancialAssets().getCurrency());
        BigDecimal bitcoinValue = externalApiService.getBitcoinValue();
        BigDecimal oligarchThreshold = externalApiService.getOligarchThreshold();

        BigDecimal totalAssetsValue = cashValue.add(BigDecimal.valueOf(person.getFinancialAssets().getBitcoinAmount()).multiply(bitcoinValue));

        Oligarch oligarch = null;
        if (totalAssetsValue.compareTo(oligarchThreshold) > 0) {
            Optional<Oligarch> resultOligarch = oligarchRepository.findById(person.getId());
            if (resultOligarch.isPresent()) {
                throw new DuplicationException("id:" + person.getId() + " already found");
            }
            oligarch = new Oligarch(person.getId(), person.getFirstName(), person.getLastName(), totalAssetsValue.longValue());
            oligarchRepository.save(oligarch);
        }
        return oligarch;
    }

    public List<Oligarch> getAllOligarchs() {
        return oligarchRepository.findAll();
    }

    public Optional<Oligarch> getOligarchById(Long id) {
        Optional<Oligarch> oligarch = oligarchRepository.findById(id);
        if (oligarch.isEmpty()) {
            throw new NotFoundException("id:" + id + " not found");
        }
        return oligarch;
    }

    public int getOligarchRank(Long id) {
        Oligarch oligarch = oligarchRepository.findById(id).orElseThrow(() ->  new NotFoundException("id:" + id + " not found"));
        List<Oligarch> sortedOligarchs = getSortedOligarchs();
        return findRank(sortedOligarchs, oligarch);
    }

    private List<Oligarch> getSortedOligarchs() {
        return oligarchRepository.findAll().stream()
                .sorted((o1, o2) -> o2.getAssetsValue().compareTo(o1.getAssetsValue()))
                .toList();
    }

    private int findRank(List<Oligarch> sortedOligarchs, Oligarch targetOligarch) {
        int rank = 1;
        for (Oligarch oligarch : sortedOligarchs) {
            if (oligarch.getAssetsValue() > targetOligarch.getAssetsValue()) {
                rank++;
            } else if (oligarch.getAssetsValue().equals(targetOligarch.getAssetsValue())) {
                break;
            }
        }
        return rank;
    }
}
