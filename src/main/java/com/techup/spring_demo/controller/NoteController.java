package com.techup.spring_demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techup.spring_demo.dto.NoteResponse;
import com.techup.spring_demo.service.NoteService;
import com.techup.spring_demo.service.SupabaseStorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final SupabaseStorageService supabaseStorageService;

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAll() {
        return ResponseEntity.ok(noteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NoteResponse> create(
            @RequestParam String title,
            @RequestParam String content) {
        return ResponseEntity.ok(noteService.create(title, content));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content) {
        return ResponseEntity.ok(noteService.update(id, title, content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Upload รูปแล้วผูกกับ Note
     @PostMapping("/{id}/upload")
    public ResponseEntity<NoteResponse> uploadForNote(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String url = supabaseStorageService.uploadFile(file);
        NoteResponse updated = noteService.attachFileUrl(id, url);
        return ResponseEntity.ok(updated);
  }
}
