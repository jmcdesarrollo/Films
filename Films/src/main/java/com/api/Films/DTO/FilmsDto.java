package com.api.Films.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class FilmsDto {
    private String title;
    private Integer year;
    private double duration;
    private String director;
    private String screenwriter;
    private String actor;
    private String music;
    private String fotograph;
    private String sypnosis;
    private MultipartFile poster;
}
