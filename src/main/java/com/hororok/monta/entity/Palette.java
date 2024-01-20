package com.hororok.monta.entity;

import com.hororok.monta.dto.request.palette.PostPaletteRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Palette extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="palette_id")
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaletteGrade grade; // RARE, EPIK, LEGENDARY

    @NotNull
    @Column(length=100)
    private String name;

    @NotNull
    @Column(length=16)
    private String lightColor;

    @NotNull
    @Column(length=16)
    private String normalColor;

    @NotNull
    @Column(length=16)
    private String darkColor;

    @NotNull
    @Column(length=16)
    private String darkerColor;

    @OneToMany(mappedBy = "palette")
    private List<StudyStreak> studyStreaks = new ArrayList<>();

    public Palette(PostPaletteRequestDto requestDto) {
        this.name = requestDto.getName();
        this.grade = requestDto.getGrade();
        this.lightColor = requestDto.getLightColor();
        this.normalColor = requestDto.getNormalColor();
        this.darkColor = requestDto.getDarkColor();
        this.darkerColor = requestDto.getDarkerColor();
    }

}
