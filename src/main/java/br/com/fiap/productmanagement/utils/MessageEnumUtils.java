package br.com.fiap.productmanagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MessageEnumUtils {

  PROCESS_PRODUCT("Processando produtos"),
  PREPARE_PRODUCT_TO_PROCESS_USE_CASE_EXCEPTION("Ocorreu um erro ao tentar preparar produto para processamento"),
  SCHEDULING_JOB_USE_CASE_EXCEPTION("Ocorreu um erro ao tentar realizar o agendamento de inicialização do job"),
  FILE_UPLOAD_INPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar fazer upload do arquivo"),
  FILE_GET_TARGET_LOCATION_INPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar localizar o endereço do arquivo"),
  JOB_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar iniciar o job"),
  PRODUCT_MANAGEMENT_GET_PRODUCTS_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar recuperar os produtos"),
  PRODUCT_MANAGEMENT_GET_PRODUCT_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar recuperar o produto"),
  PRODUCT_MANAGEMENT_REMOVE_PRODUCT_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar remover o produto"),
  PRODUCT_MANAGEMENT_UPDATE_PRODUCT_OUTPUT_PORT_EXCEPTION("Ocorreu um erro ao tentar atualizar o produto");

  public static final String TITLE_PROCESS_PRODUCT = "Solicitação recebida";
  public static final String TIME_DOMAIN_EXCEPTION = "Solicitação recebida";
  public static final String TITLE_PORTS_EXCEPTION =  "Erro de processamento";
  private String message;

}