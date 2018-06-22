package br.com.vipmania.model;

public class PaymentData {

	private String cmd = "_xclick";
	private String business ="lucasferrazsilva@hotmail.com";
	private String returnn = "http://localhost:8080/vipmania/";
	private String cancel ="http://localhost:8080/vipmania/product/list/camisas";
	private String notify_url = "http://localhost:8080/vipmania/cart/finalize-test";
	
	private String charset = "utf-8";
    private String lc = "BR";
    private String country_code = "BR";
    private String currency_code = "BRL";
 
    private String amount = "1.00";
    private String item_name = "Saia";
    private String quantity = "1";


	public PaymentData() {
		// noop
	}


	public String getCmd() {
		return cmd;
	}


	public String getBusiness() {
		return business;
	}


	public String getReturnn() {
		return returnn;
	}


	public String getCancel() {
		return cancel;
	}


	public String getNotify_url() {
		return notify_url;
	}


	public String getCharset() {
		return charset;
	}


	public String getLc() {
		return lc;
	}


	public String getCountry_code() {
		return country_code;
	}


	public String getCurrency_code() {
		return currency_code;
	}


	public String getAmount() {
		return amount;
	}


	public String getItem_name() {
		return item_name;
	}


	public String getQuantity() {
		return quantity;
	}
	
	
}