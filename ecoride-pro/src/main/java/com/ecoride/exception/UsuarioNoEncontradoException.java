package com.ecoride.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String idUsuario) {
        super("Usuario No Encontrado: No existe un usuario con ID '" + idUsuario + "'.");
    }
}
