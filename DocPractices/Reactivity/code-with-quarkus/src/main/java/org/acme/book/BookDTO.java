package org.acme.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    @Column(unique = true)
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    private Integer numPages;
    @NotBlank
    private String authorName;

}
