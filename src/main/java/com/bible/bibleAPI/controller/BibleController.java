package com.bible.bibleAPI.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bible.bibleAPI.service.BibleService;

@RestController
@RequestMapping("/api")
public class BibleController {
    @Autowired
    private BibleService bibleService;

    @GetMapping("/sobre")
        public ResponseEntity<?> getSobre() {
        Map<String, String> sobre = new HashMap<>();
        sobre.put("estudante", "Edrik Steiner");
        sobre.put("projeto", "bibleAPI");
        return new ResponseEntity<>(sobre, HttpStatus.OK);
    }

    @GetMapping("/{book}-{chapter}:{verse}")
    public ResponseEntity<?> getBibleVerse(@PathVariable String book, @PathVariable Integer chapter, @PathVariable Integer verse) {
        return bibleService.getBibleVerse(book, chapter, verse);
    }

    @PostMapping("/verse")
    public ResponseEntity<?> postBibleVerse(@RequestBody Map<String, Object> data) {
        String book = (String) data.get("book");
        Integer chapter = (Integer) data.get("chapter");
        Integer verse = (Integer) data.get("verse");
        
        return bibleService.getBibleVerse(book, chapter, verse);
    }

    @GetMapping("/{book}+{chapter}:{verseStart}-{verseEnd}")
    public ResponseEntity<?> getBibleVerseRange(@PathVariable String book, 
                                                @PathVariable Integer chapter, 
                                                @PathVariable Integer verseStart, 
                                                @PathVariable Integer verseEnd) {
        return bibleService.getBibleVerseRange(book, chapter, verseStart, verseEnd);
    }

    @PostMapping("/verse-range")
    public ResponseEntity<?> postBibleVerseRange(@RequestBody Map<String, Object> data) {
        String book = (String) data.get("book");
        Integer chapter = (Integer) data.get("chapter");
        Integer verseStart = (Integer) data.get("verseStart");
        Integer verseEnd = (Integer) data.get("verseEnd");
        
        return bibleService.getBibleVerseRange(book, chapter, verseStart, verseEnd);
    }

    @PostMapping("/multiple-ranges")
    public ResponseEntity<?> postBibleMultipleRanges(@RequestBody Map<String, Object> data) {
        String book = (String) data.get("book");
        String range = (String) data.get("range");
        
        return bibleService.getBibleMultipleRanges(book, range);
    }

    @PostMapping("/translation")
    public ResponseEntity<?> postBibleTranslation(@RequestBody Map<String, Object> data) {
        String book = (String) data.get("book");
        String range = (String) data.get("range");
        String translate = (String) data.get("translate");
        
        return bibleService.getBibleTranslation(book, range, translate);
    }
}