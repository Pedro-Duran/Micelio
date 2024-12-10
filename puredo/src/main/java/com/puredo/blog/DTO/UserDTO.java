package com.puredo.blog.DTO;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Value;

import java.util.List;

public enum UserDTO {;

    // Interfaces para validação e documentação dos campos
    private interface Id {
        Long getId();
    }

    private interface Username {
         String getUsername();
    }

    private interface Password {
         String getPassword();
    }

    @Nullable
    private interface Posts{
        List<com.puredo.blog.model.Post> getPosts();
    }


    // DTOs para Requisições
    public enum Request {;


        @Value
        public static class Create implements Username, Password {
            String username;
            String password;
        }

        @Value
        public static class UpdatePassword implements Id, Password {
            Long id;
            String password;
        }

    }

    // DTOs para Respostas
    public enum Response {;

        @Value
        public static class UsuarioPublico implements Id, Username {
            Long id;
            String username;
            List<com.puredo.blog.model.Post> posts;
        }

        @Value
        public static class UsuarioPrivado implements Id, Username, Password {
            Long id;
            String username;
            String password;
        }
    }
}
