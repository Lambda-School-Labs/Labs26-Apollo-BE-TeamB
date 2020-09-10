package com.lambdaschool.apollo.services;

import com.lambdaschool.apollo.exceptions.ResourceNotFoundException;
import com.lambdaschool.apollo.models.Survey;
import com.lambdaschool.apollo.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "surveyService")
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public Survey findById(long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey " + id + " Not Found"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (surveyRepository.findById(id).isPresent()) {
            surveyRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Survey " + id + " Not Found");
        }
    }

    @Transactional
    @Override
    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }
}