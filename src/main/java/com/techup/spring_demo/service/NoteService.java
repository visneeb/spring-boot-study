package com.techup.spring_demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techup.spring_demo.dto.NoteResponse;
import com.techup.spring_demo.entity.Note;
import com.techup.spring_demo.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {

  private final NoteRepository noteRepository;

  // ✅ Get all notes
  public List<NoteResponse> getAll() {
    return noteRepository.findAll()
        .stream()
        .map(this::toResponse)
        .toList();
  }

  // ✅ Get note by id
  public NoteResponse getById(Long id) {
    Note note = noteRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
    return toResponse(note);
  }

  // ✅ Create note
  public NoteResponse create(String title, String content) {
    Note note = new Note();
    note.setTitle(title);
    note.setContent(content);
    return toResponse(noteRepository.save(note));
  }

  // ✅ Update note
  public NoteResponse update(Long id, String title, String content) {
    Note note = noteRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
    note.setTitle(title);
    note.setContent(content);
    return toResponse(noteRepository.save(note));
  }

  // ✅ Delete note
  public void delete(Long id) {
    if (!noteRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
    }
    noteRepository.deleteById(id);
  }

  // ✅ Attach image URL to note
   public NoteResponse attachFileUrl(Long id, String url) {
    Note note = noteRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));

    note.setImageUrl(url);
    Note saved = noteRepository.save(note);
    return toResponse(saved);
  }

  // ✅ Map entity → DTO
  private NoteResponse toResponse(Note note) {
    return new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getImageUrl());
  }

  
}