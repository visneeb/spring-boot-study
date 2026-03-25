package com.techup.spring_demo.entity;

// ✅ import JPA annotations สำหรับแม็ปคลาส ↔ ตารางใน DB
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity  // บอก JPA ว่าคลาสนี้คือ Entity ที่ map กับตารางใน DB
@Table(name = "notes") // ระบุชื่อ table ให้ชัดเจน
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) // ห้ามเป็น null
  private String title;

  @Column(columnDefinition = "TEXT") // เนื้อหายาว ใช้ type TEXT
  private String content;

  // ✅ Constructor ว่าง (จำเป็นสำหรับ JPA)
  public Note() {}

  // ✅ Constructor ทุกฟิลด์ (ยกเว้น id ที่ generate อัตโนมัติ)
  public Note(Long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  // ✅ Getter/Setter
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

}