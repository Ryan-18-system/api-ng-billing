package dev.ryan.nobrega.propertiesMessage;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageBundle {

	public MessageBundle() {
		throw new IllegalStateException("Utility class");
	}
	private static ResourceBundle messageResources;
	private static String defaultMessage;


	static {
		try {
			messageResources = ResourceBundle.getBundle("ApplicationMessage");
		} catch (Exception e) {

			messageResources = null;
		}

		// carrega msg padrao
		defaultMessage = getMessage("message.default");
		if ("".equals(defaultMessage)) {
			defaultMessage = "Ocorreu um erro inesperado. Contacte o administrador do sistema.";
		}
	}
	/**
	 * Recupera a mensagem através da sua chave. Se não a encontrar, utiliza a
	 * mensagem padrão e a retorna.
	 * 
	 * @param key chave da mensagem. Ex: "mensagem.login"
	 * @return String mensagem definida no arquivo properties
	 */
	public static String getMessage(String key) {
		String msg = null;
		try {
			msg = messageResources.getString(key);
		} catch (MissingResourceException mre) {
			msg = defaultMessage;
		} catch (Exception e) {
			messageResources = null;
			msg = defaultMessage;
		}

		// Retornando mensagem
		return (msg == null ? "" : msg);
	}
	/**
	 * Recupera a mensagem através da sua chave. Se não a encontrar, procura por uma
	 * mensagem chamada "message.default" e a retorna. Substitui as chaves com os
	 * parametros passados. não realiza nenhum teste para verificar se o número de
	 * parâmetros coincide com o recebido. Para ocorrer a substituição, a string
	 * deve conter o seguinte padrão no arquivo de properties:
	 * mensagem.login=bem-vindo ao sistema {0}, {1} No lugar de {0} será colocado o
	 * item que está na posição 0 do array, no lugar de {1} o que está na 1, e assim
	 * por diante
	 * 
	 * @param key  chave da mensagem. Ex: "mensagem.login"
	 * @param args prâmetros que serão substituídos na mensagem
	 * @return String mensagem
	 */
	public static String getMessage(String key, String[] args) {
		String msg = getMessage(key);

		if ("".equals(msg)) {
			return "";
		}

		MessageFormat mf = new MessageFormat(msg);
		msg = mf.format(args);
		return (msg != null ? msg : "");
	}

}
