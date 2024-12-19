package com.puredo.blog.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Value;

import java.util.List;

public enum PostDTO {;

    // Interfaces para os campos do DTO
    private interface Id {
        Long getId();
    }

    private interface Title {
        String getTitle();
    }

    private interface Content {
        String getContent();
    }


    private interface Author {
        UserDTO.Response.UsuarioPublico getAuthor(); // Objeto complexo
    }

    private interface CreatedAt {
        String getCreatedAt();
    }

    @Nullable
    private interface Links{
        List<Long> getLinks();
    }


    private interface Subject{
        String getSubject();
    }

    public enum Request {;

        @Data
        @Value
        public static class Create implements Title, Content, Author, Subject {
            String title;
            String content;
            UserDTO.Response.UsuarioPublico author;
            List<Long> links;
            String subject;
        }


        @Data
        @Value
        public static class Update implements Id, Title, Content, Links, Subject {
            Long id;
            String title;
            String content;
           List<Long> links;
            String subject;
        }
    }

    // DTOs para Respostas
    public enum Response {;

        @Value
        public static class Post implements Id, Title, Content, Author, CreatedAt {
            Long id;
            String title;
            String content;
            UserDTO.Response.UsuarioPublico author; // Alinhe a resposta ao JSON esperado
            String createdAt;
            List<Long> links;
            String subject;
        }

        @Value
        public static class PostSummary implements Id, Title {
            Long id;
            String title;
        }
    }
}
