package com.thales.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' Where id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  @NotNull
  @Length(min = 5, max = 200)
  @Column(length = 200, nullable = false)
  private String name;

  @NotNull
  @Length(max = 20)
  @Pattern(regexp = "back-end|front-end")
  @Column(length = 20, nullable = false)
  private String category;

  @NotNull
  @Length(max = 10)
  @Pattern(regexp = "Ativo|Inativo")
  @Column(length = 10, nullable = false)
  private String status = "Ativo";

}
