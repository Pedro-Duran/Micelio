package com.puredo.blog.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Value;

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


    public enum Request {;

        @Data
        @Value
        public static class Create implements Title, Content, Author {
            String title;
            String content;
            UserDTO.Response.UsuarioPublico author;
        }

        @Value
        public static class Update implements Id, Title, Content {
            Long id;
            String title;
            String content;
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

        }

        @Value
        public static class PostSummary implements Id, Title {
            Long id;
            String title;
        }
    }
}
