package com.thales.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thales.crudspring.model.Course;
import com.thales.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

  private final CourseRepository courseRepository;

  @GetMapping
  public List<Course> list() {
    return courseRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> findById(@PathVariable Long id) {
    return courseRepository.findById(id)
        .map(course -> ResponseEntity.ok().body(course))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Course> create(@RequestBody Course course) {
    return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
    return courseRepository.findById(id)
        .map(c1 -> {
          c1.setName(course.getName());
          c1.setCategory(course.getCategory());
          var updated = courseRepository.save(c1);
          return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
  }

}
