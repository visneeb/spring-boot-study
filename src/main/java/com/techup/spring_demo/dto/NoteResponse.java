package com.techup.spring_demo.dto;

public class NoteResponse {
  private Long id;
  private String title;
  private String content;
  private String imageUrl;

  public NoteResponse() {}

  public NoteResponse(Long id, String title, String content, String imageUrl) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  public String getImageUrl() { return imageUrl; }
  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}