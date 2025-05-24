package dev.ryan.nobrega.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title:       UtilidadesDesenvolvimento
 * Description:
 *              Esta classe define uma série de operações para simplificar o
 *              trabalho de desenvolvimento.
 *
 * @author 	Ryan Nóbrega Brandão, Ruan Diego Campos da Silva
 * @version 2.0
 */
public class UtilsDev {

	private UtilsDev() {
	    throw new IllegalStateException("Utility class");
	}
	
	/* Caracter usado para separa dia, mês e ano em uma data */
	@SuppressWarnings("unused")
	private static final String SEPARADOR_DATA = "/";

	/** Separador da hora. */
	@SuppressWarnings("unused")
	private static final String SEPARADOR_HORA = ":";

	/** Número de informações sobre a data (dia, mês e ano)*/
	private static final int NUMERO_INFORMACOES = 3;

	/** Número de informações sobre a hora (hora, minutos e segundos)*/
	@SuppressWarnings("unused")
	private static final int NUMERO_INFORMACOES_HORA = NUMERO_INFORMACOES;

	/** Número de informações sobre a hora abreviada (hora, minutos)*/
	@SuppressWarnings("unused")
	private static final int NUMERO_INFORMACOES_HORA_ABREVIADA = 2;

	/** Número de informações sobre a data (dia, mês e ano)*/
	@SuppressWarnings("unused")
	private static final int NUMERO_INFORMACOES_DATA = NUMERO_INFORMACOES;

	/*
	 * Numero de ano para o qual um ano informado com 2 digitos deve ser
	 * inter pretado como cendo do seculo atual ou anterior
	 */
	public static final int ANO_SEPARACAO_ENTRE_SECULOS = 50;

	/** Número de milissegundos em um minuto */
	public static final  long MILISSEGUNDOS_MINUTO = 60000L;

	/** Número de milissegundos em uma hora */
	public static final  long MILISSEGUNDOS_HORA = 3600000L;

	/** Número de milissegundos de um dia */
	public static final  long MILISSEGUNDOS_DIA = 86400000L;

	/** Número de milissegundos de um mês */
	public static final  long MILISSEGUNDOS_MES = 2592000000L;

	/** Constanet para complementar o ano da data Juliana. */
	@SuppressWarnings("unused")
	private static final String COMPLEMENTO_ANO = "20";

	/*
	 * Constantes utilizadas pelo método incrementaData para saber em que
	 * formato a data deve ser incrementada
	 */
	public static final int HORA = Calendar.HOUR_OF_DAY;

	public static final int DIA = Calendar.DAY_OF_MONTH;

	public static final int MES = Calendar.MONTH;

	public static final int ANO = Calendar.YEAR;

	/** Contante que representa o dia de segunda feira na semana */
	public static final int SEGUNDA = Calendar.MONDAY;

	/** Contante que representa o dia de treça feira na semana */
	public static final int TERCA = Calendar.TUESDAY;

	/** Contante que representa o dia de quarta feira na semana */
	public static final int QUARTA = Calendar.WEDNESDAY;

	/** Contante que representa o dia de quinta feira na semana */
	public static final int QUINTA = Calendar.THURSDAY;

	/** Contante que representa o dia de sexta feira na semana */
	public static final int SEXTA = Calendar.FRIDAY;

	/** Contante que representa o dia de sábado feira na semana */
	public static final int SABADO = Calendar.SATURDAY;

	/** Contante que representa o dia de domingo feira na semana */
	public static final int DOMINGO = Calendar.SUNDAY;

	private static final 
	Collection<Integer> MESES_COM_31_DIAS = new ArrayList<>();

	private static final 
	Collection<Integer> MESES_COM_30_DIAS = new ArrayList<>();

	static {

		MESES_COM_31_DIAS.add(Calendar.JANUARY);
		MESES_COM_31_DIAS.add(Calendar.MARCH);
		MESES_COM_31_DIAS.add(Calendar.MAY);
		MESES_COM_31_DIAS.add(Calendar.JULY);
		MESES_COM_31_DIAS.add(Calendar.AUGUST);
		MESES_COM_31_DIAS.add(Calendar.OCTOBER);
		MESES_COM_31_DIAS.add(Calendar.DECEMBER);

		MESES_COM_30_DIAS.add(Calendar.APRIL);
		MESES_COM_30_DIAS.add(Calendar.JUNE);
		MESES_COM_30_DIAS.add(Calendar.SEPTEMBER);
		MESES_COM_30_DIAS.add(Calendar.NOVEMBER);
	} // static    
	
	/**
	 * @implementador Ruan Diego Campos da Silva
	 *
	 * Este método verifica se um valor está preenchido
	 * (valor.trim().length() != 0) retornando true, em caso afirmativo, ou
	 * false, caso contrário.
	 *
	 * @param valor valor a ter seu preenchimento verificado.
	 * @return true, caso o valor seja preenchido, ou false, caso contrário.
	 *
	 */
	public static boolean valorPreenchido(String valor) {

		return ( ( valor != null ) &&  (valor.trim().length() != 0) );

	} // valorPreenchido()

	/**
	 * @implementador Ruan Diego Campos da Silva
	 *
	 * Este método verifica se um valor não é nulo, não é "0" e está preenchido
	 * (valor.trim().length() != 0) retornando true, em caso afirmativo, ou
	 * false, caso contrário.
	 *
	 * @param valor valor a ter seu preenchimento verificado.
	 * @return true, caso o valor preenchido não zero, ou false, caso contrário.
	 *
	 */
	public static boolean valorPreenchidoNoZero(String valor) {

		return ( ( valor != null )
				&&  !(valor.trim().equals("0"))
				&&  (valor.trim().length() != 0));

	} // valorPreenchidoNoZero()

	/**
	 * @Implementador Ruan Diego Campos da Silva							<br>
	 *																		<br>
	 * <b>Objetivo:</b>														<br>
	 *     Este método tem por objetivo verificar se o objeto passado é null<br>
	 * ou vazio.															<br>
	 *     																	<br>
	 * @param valor o valor a ser validado.									<br>
	 * 																		<br>
	 * @return treu caso seja null ou vazio, ou false, caso contrário.		<br>
	 */
	public static boolean ehNullOuVazio(Object valor) {

		return valor == null || valor.toString().trim().equals("");
	}//ehNullOuVazio

	/**
	 * @Implementador Ruan Diego Campos da Silva							<br>
	 *																		<br>
	 * <b>Objetivo:</b>														<br>
	 *     Este método tem por objetivo verificar se o objeto passado é null<br>
	 * ou vazio.															<br>
	 *     																	<br>
	 * @param valor o valor a ser validado.									<br>
	 * 																		<br>
	 * @return treu caso seja null ou vazio, ou false, caso contrário.		<br>
	 */
	public static boolean ehNullOuVazio(@SuppressWarnings("rawtypes") Collection valor) {

		return valor == null || valor.isEmpty();
	}//ehNullOuVazio

	/**
	 * @Implementador Ruan Diego Campos da Silva							<br>
	 *																		<br>
	 * <b>Objetivo:</b>														<br>
	 *     Este método tem por objetivo verificar se o objeto passado é null<br>
	 * ou vazio.															<br>
	 *     																	<br>
	 * @param valor o valor a ser validado.									<br>
	 * 																		<br>
	 * @return treu caso seja null ou vazio, ou false, caso contrário.		<br>
	 */
	public static boolean ehNullOuVazio(@SuppressWarnings("rawtypes") Map valor) {

		return valor == null || valor.isEmpty();
	}//ehNullOuVazio

	/**
     * @Implementador Ruan Diego Campos da Silva							<br>
     *																		<br>
     * <b>Objetivo:</b>														<br>
     *     Este método tem por objetivo verificar seo dia da semana passado	<br>
     * é o mesmo dia da data em questão.									<br>
     *																		<br>
     * @param data a data que representa o dia que queremos verificar.		<br>
     * @param diaDaSemana o dia da semana para averiguar se e o mesmo da 	<br>
     * data.																<br>
     * 																		<br>
     * @return true caso seja igual, ou false, caso contrário.				<br>
     */
    public static boolean verificarDiadaSemana(Date data, Integer diaDaSemana) {
    	
    	Calendar calendario = Calendar.getInstance();
    	calendario.setTime( data );
    	
    	return calendario.get(Calendar.DAY_OF_WEEK) == diaDaSemana;
    }//verificarDiadaSemana
	
    /**
     * @Implementador Ruan Diego Campos da Silva							<br>
     *																		<br>
     * <b>Objetivo:</b>														<br>
     *     Este método tem por objetivo retornar uma data qualquer de acordo<BR>
     * com a máscara especificada                                   		<BR>
     *                                                                      <BR>
     * @param mascara - Máscara para o retorno da data                      <BR>
     * @param data - Data que será formatada                                <BR>
     *                                                                      <BR>
     *   Ano com 4 dígitos - yyyy                                           <BR>
     *   Ano com 2 dígitos - yy                                             <BR>
     *   Mês - MM                                                           <BR>
     *   Dia - dd                                                           <BR>
     *   Hora  (00 - 23) - HH                                               <BR>
     *   Hora (1 - 12) - hh                                                 <BR>
     *   Minuto - mm                                                        <BR>
     *   Segundo - ss                                                       <BR>
     *                                                                      <BR>
     *  Exemplo para máscaras             Retorno                           <BR>
     *     dd-MM-yyyy                    09-11-2001                         <BR>
     *     dd/MM/yyyy                    09/11/2001                         <BR>
     *     yyyy-MM-dd                    2001-11-09                         <BR>
     *     yyyy-MM-dd  HH:mm:ss          2001-11-09 15:25:10                <BR>
     *                                                                      <BR>
     * @return String - String contendo a data de acordo com o formato 		<BR>
     * especificado                                                			<BR>
     */
    public static String obterDataFormato(String mascara, Date data){

        SimpleDateFormat formatoData = new SimpleDateFormat(mascara);
        return formatoData.format(data);
    }//obterDataFormato
    
    /**
     * @Implementador Ruan Diego Campos da Silva							<br>
     *																		<br>
     * <b>Objetivo:</b>														<br>
     *     Este método tem por objetivo retornar uma data qualquer de acordo<BR>
     * com a máscara especificada                                   		<BR>
     *                                                                      <BR>
     * @param mascara - Máscara para o retorno da data                      <BR>
     * @param data - Data que será formatada                                <BR>
     *                                                                      <BR>
     *   Ano com 4 dígitos - yyyy                                           <BR>
     *   Ano com 2 dígitos - yy                                             <BR>
     *   Mês - MM                                                           <BR>
     *   Dia - dd                                                           <BR>
     *   Hora  (00 - 23) - HH                                               <BR>
     *   Hora (1 - 12) - hh                                                 <BR>
     *   Minuto - mm                                                        <BR>
     *   Segundo - ss                                                       <BR>
     *                                                                      <BR>
     *  Exemplo para máscaras             Retorno                           <BR>
     *     dd-MM-yyyy                    09-11-2001                         <BR>
     *     dd/MM/yyyy                    09/11/2001                         <BR>
     *     yyyy-MM-dd                    2001-11-09                         <BR>
     *     yyyy-MM-dd  HH:mm:ss          2001-11-09 15:25:10                <BR>
     *                                                                      <BR>
     * @return String - String contendo a data de acordo com o formato 		<BR>
     * especificado                                                			<BR>
     */
    public static String obterDataFormato(String mascara, java.sql.Date data){

    	return obterDataFormato(mascara, new Date( data.getTime() ) );
    }//obterDataFormato
    
    /**
     * @Implementador Ruan Diego Campos da Silva							<br>
     * 
     * Este método é encarregedo de converter um String em um Date. O formato
     *   especificado deve ser o mesmo que se deseja colocar no segundo
     *   argumento. Abaixo segue alguns formatos e seus respectivos valores
     *   originais                                                          <BR>
     *                                                                      <BR>
     *   formato             valor original                                 <BR>
     *   -------             --------------                                 <BR>
     *   dd/MM/YY               10/03/02                                    <BR>
     *   dd/MM/yy hh:mm:ss      10/03/02 12:35:00                           <BR>
     *                                                                      <BR>
     * @param formato - Formato em que o dado deve ser lido                 <BR>
     * @param dataHora - String contendo a dataHora                         <BR>
     *                                                                      <BR>
     * @return Date - Retorna o valor da dataHora no formato java.util.Date <BR>
     */
    public static Date converterStringParaDate(String formato,
    		String dataHora) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.parse(dataHora);
        
    } // converterStringParaDate()
    
    /**
     * @Implementador Ruan Diego Campos da Silva							<br>
     * 
     * Este método é encarregedo de converter um String em um Date SQL. O 
     *   formato especificado deve ser o mesmo que se deseja colocar no segundo
     *   argumento. Abaixo segue alguns formatos e seus respectivos valores
     *   originais                                                          <BR>
     *                                                                      <BR>
     *   formato             valor original                                 <BR>
     *   -------             --------------                                 <BR>
     *   dd/MM/YY               10/03/02                                    <BR>
     *   dd/MM/yy hh:mm:ss      10/03/02 12:35:00                           <BR>
     *                                                                      <BR>
     * @param formato - formato em que o dado deve ser lido                 <BR>
     * @param dataHora - string contendo a dataHora                         <BR>
     *                                                                      <BR>
     * @return Date - Retorna o valor da dataHora no formato java.sql.Date  <BR>
     *                                                                      <BR>
     */
    public static java.sql.Date converterStringParaSQLDate(String formato,
    		String dataHora) throws ParseException {

        return new java.sql.Date(
        		(UtilsDev.converterStringParaDate(formato, dataHora))
        				.getTime());
        
    } // converterStringParaSQLDate()
    
    /**
     * @implementador Ruan Diego Campos da Silva
     *
     * Este método obtém o formato em extenso da data. Exemplo: para a data
     * 01/02/2003, retornando a String 01 de fevereiro de 2003.
     *
     * @param data a data cujo formato em extenso será retornada.
     * @param formato o formato por extenso a ser utilizado.
     * @return o formato em extenso da data.
     */
    public static String obterExtenso(Date data, String formato) {

    	DateFormat formatoData = 
    			new SimpleDateFormat(formato, new Locale("pt", "BR") );
    	return formatoData.format( data );
    }//obterExtenso()
    
    /**
     * @Implementador Ryan Nóbrega e Ruan Diego								<br>
     *																		<br>
     * <b>Objetivo:</b>														<br>
     *     Este método tem por objetivo obter o nome de um mês a partir do	<br>
     * seu número, levando em conta que janeiro possui o valor 1.			<br>
     *																		<br>
     * @param nrMes o número do mês, começando do valor 1.					<br>
     * 																		<br>
     * @return o nome do mês cujo número foi passado.						<br>
     * @throws Exception 
     */
    public static String obterNomeMes(int nrMes) {

    	SimpleDateFormat sdf = new SimpleDateFormat("MM");
		try {
			return obterExtenso(sdf.parse(""+nrMes), "MMMM");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
        
    }//obterNomeMesAbreviado() 
    
    /**
     * <b>Objetivo:</b>                                                     <br>
     *              Transformar uma sequência de inteiros representando o CPF
     *              em um CPF formatado na forma: nnn.nnn.nnn-nn <br>
     *
     * @param  cpf string representando um CPF no formato puro.
     * @return o CPF no formato nnn.nnn.nnn-nn
     * @throws Exception 
     * @implementador Ryan Nóbrega Brandão
     */
	public static String converterFormatoCpf(String cpf) throws RuntimeException {
		final String STR_EXP_CPF_SEM_MASCARA = "^[0-9]{11}$";
		Pattern pattern = Pattern.compile(STR_EXP_CPF_SEM_MASCARA);
		Matcher matcher = pattern.matcher(cpf);

		if (matcher.matches()) {
			StringBuilder cpfComMascara = new StringBuilder();

			try {
				cpfComMascara.append(cpf.substring(0, 3));
				cpfComMascara.append(".");
				cpfComMascara.append(cpf.substring(3, 6));
				cpfComMascara.append(".");
				cpfComMascara.append(cpf.substring(6, 9));
				cpfComMascara.append("-");
				cpfComMascara.append(cpf.substring(9, 11));
			} catch (StringIndexOutOfBoundsException ex) {
				throw new RuntimeException("ERRO.AO.FORMATAR.CPF");
			}

			return cpfComMascara.toString();
		}

		return cpf;
	} // converterFormatoCnpj
    

    
    /**
     * <b>Objetivo:</b>                                                     <br>
     *             A partir da String que representa o CNPJ, este método aplica
     *             a máscara, caso a mesma seja uma sequência de inteiros e
     *             possua o tamanho do cnpj. <br>
     *
     * @param cnpj a String que representa o CNPJ a ser formatado.
     * @return o cnpj com a máscara de formato.
     * @throws RuntimeException
     * @implementador Ryan Nóbrega Brandão
     */
	public static String converterFormatoCnpj(String cnpj) throws RuntimeException {
		final String STR_EXP_CNPJ_SEM_MASCARA = "^[0-9]{14}$";
		Pattern pattern = Pattern.compile(STR_EXP_CNPJ_SEM_MASCARA);
		Matcher matcher = pattern.matcher(cnpj);

		if (matcher.matches()) {
			try {
				StringBuilder cnpjComMascara = new StringBuilder();

				cnpjComMascara.append(cnpj.substring(0, 2));
				cnpjComMascara.append(".");
				cnpjComMascara.append(cnpj.substring(2, 5));
				cnpjComMascara.append(".");
				cnpjComMascara.append(cnpj.substring(5, 8));
				cnpjComMascara.append("/");
				cnpjComMascara.append(cnpj.substring(8, 12));
				cnpjComMascara.append("-");
				cnpjComMascara.append(cnpj.substring(12, 14));

				return cnpjComMascara.toString();

			} catch (StringIndexOutOfBoundsException ex) {
				throw new RuntimeException("ERRO.AO.FORMATAR.CNPJ");
			}
		}

		return cnpj;
	} // converterFormatoCnpj


	/**
	 * @author Ryan
	 * @param tipo
	 * @param sequence
	 * @return  retorna um nib de uma edificação
	 */
	public static String gerarCodNib(String tipo, String sequence) {
		
		int number;

		if (sequence == null) {
			number = 0;
		} else {
			number = Integer.parseInt(sequence.substring(1));

			// Incrementa o número
			number++;

			// Verifica se o nono dígito é 9
			if (sequence.charAt(9) == '9') {
				// Zera o nono dígito
				StringBuilder sb = new StringBuilder(sequence);
				sb.setCharAt(9, '0');
				sequence = sb.toString();

				// Adiciona 1 ao próximo número à esquerda
				int carry = 1;
				for (int i = 8; i >= 1; i--) {
					int digit = Character.getNumericValue(sequence.charAt(i));
					digit += carry;
					if (digit >= 10) {
						digit = 0;
					} else {
						carry = 0;
					}
					sb.setCharAt(i, Character.forDigit(digit, 10));
					sequence = sb.toString();
					if (carry == 0) {
						break;
					}
				}
			}
		}

		// Formata o número de volta para a sequência original
		String prefixo = null;
		if ("Edificacao".equals(tipo)) {
			prefixo = "A";
		} else if ("Empresa".equals(tipo)) {
			prefixo = "B";
		}
		
		sequence = prefixo + String.format("%09d", number);

		return sequence;
	} // gerarCodNib


	/**
	 * @description Retorna um cpf formatado e sensurado
	 * @author Ryan Nóbrega Brandão
	 * @param cpf
	 * @return  string
	 * @throws RuntimeException
	 */
	public static String converterCPFCensurado(String cpf) throws RuntimeException {
		final String STR_EXP_CPF_SEM_MASCARA = "^[0-9]{11}$";
		Pattern pattern = Pattern.compile(STR_EXP_CPF_SEM_MASCARA);
		Matcher matcher = pattern.matcher(cpf);

		if (matcher.matches()) {
			StringBuilder cpfComMascara = new StringBuilder();

			try {
				cpfComMascara.append(cpf.substring(0, 3));
				cpfComMascara.append(".");
				cpfComMascara.append("**");
				cpfComMascara.append(".");
				cpfComMascara.append(cpf.substring(6, 9));
				cpfComMascara.append("-");
				cpfComMascara.append("**");
			} catch (StringIndexOutOfBoundsException ex) {
				throw new RuntimeException("ERRO.AO.FORMATAR.CPF");
			} // try catch

			return cpfComMascara.toString();
		} // if

		return cpf;
	} // converterCPFSensurado
    
}// UtilidadesDesenvolvimento
