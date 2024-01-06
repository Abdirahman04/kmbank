package com.abdirahman.kmbank.controller;

import com.abdirahman.kmbank.model.response.StatisticsResponseBody;
import com.abdirahman.kmbank.model.response.UserResponseBody;
import com.abdirahman.kmbank.service.DataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StatisticsController {
    DataAccessService dataAccessService;

    public StatisticsController() {
    }

    @Autowired
    public StatisticsController(DataAccessService dataAccessService) {
        this.dataAccessService = dataAccessService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponseBody> getStatistics() {
        StatisticsResponseBody stats = dataAccessService.getStatisticsData();
        return new ResponseEntity<>(stats, HttpStatusCode.valueOf(200));
    }
}
