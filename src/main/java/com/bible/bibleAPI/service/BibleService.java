package com.bible.bibleAPI.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BibleService {
    private final RestTemplate restTemplate;

    public BibleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> getBibleVerse(String book, Integer chapter, Integer verse) {
        if (book.equals("")) {
            return new ResponseEntity<>("Invalid book", HttpStatus.BAD_REQUEST);
        } else if (chapter == null || chapter == 0) {
            return new ResponseEntity<>("Invalid chapter", HttpStatus.BAD_REQUEST);
        } else if (verse == null || verse == 0) {
            return new ResponseEntity<>("Invalid verse", HttpStatus.BAD_REQUEST);
        } else {
            String url = "https://bible-api.com/" + book + "+" + chapter + ":" + verse;
            String response = restTemplate.getForObject(url, String.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getBibleVerseRange(String book, 
                                                Integer chapter, 
                                                Integer verseStart, 
                                                Integer verseEnd) {
        if (book.equals("")) {
            return new ResponseEntity<>("Invalid book", HttpStatus.BAD_REQUEST);
        } else if (chapter == null || chapter == 0) {
            return new ResponseEntity<>("Invalid chapter", HttpStatus.BAD_REQUEST);
        } else if (verseStart == null || verseStart == 0) {
            return new ResponseEntity<>("Invalid starting verse", HttpStatus.BAD_REQUEST);
        } else if (verseEnd == null || verseEnd == 0) {
            return new ResponseEntity<>("Invalid ending verse", HttpStatus.BAD_REQUEST);
        } else {
            String url = "https://bible-api.com/" + book + "+" + chapter + ":" + verseStart + "-" + verseEnd;
            String response = restTemplate.getForObject(url, String.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getBibleMultipleRanges(String book, String range) {
        if (book.equals("") || range.equals("")) {
            return new ResponseEntity<>("Invalid book or range", HttpStatus.BAD_REQUEST);
        } else {
            String url = "https://bible-api.com/" + book + range;
            String response = restTemplate.getForObject(url, String.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getBibleTranslation(String book, String range, String translate) {
        if (book.equals("") || range.equals("") || translate.equals("")) {
            return new ResponseEntity<>("Invalid book, range or translation", HttpStatus.BAD_REQUEST);
        } else {
            String url = "https://bible-api.com/" + book + "+" + range + "?translation=" + translate;
            String response = restTemplate.getForObject(url, String.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}