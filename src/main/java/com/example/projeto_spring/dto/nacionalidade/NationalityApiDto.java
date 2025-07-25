package com.example.projeto_spring.dto.nacionalidade;

public class NationalityApiDto {
    public Name name;
    public Flags flags;
    public String cca3;
    public String region;

    public static class Name {
        public String common;
    }

    public static class Flags {
        public String png;
    }
}
