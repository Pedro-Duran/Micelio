package com.puredo.blog.DTO;


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

    private interface AuthorUsername {
        String getAuthorUsername();
    }

    private interface CreatedAt {
        String getCreatedAt();
    }

    // DTOs para Requisições
    public enum Request {;

        @Value
        public static class Create implements Title, Content, AuthorUsername {
            String title;
            String content;
            String authorUsername;
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
        public static class Public implements Id, Title, Content, AuthorUsername, CreatedAt {
            Long id;
            String title;
            String content;
            String authorUsername;
            String createdAt;
        }
    }
}

