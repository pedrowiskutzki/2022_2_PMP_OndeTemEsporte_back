package br.serratec.pmp.esporte.exception;

public class ResourceBadRequestException extends RuntimeException {
	public ResourceBadRequestException(String mensagem) {
		super(mensagem);
	}
}
