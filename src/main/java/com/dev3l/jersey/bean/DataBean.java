package com.dev3l.jersey.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataBean {
	private String data;

	public DataBean() {}
	
	public DataBean(String data) {
		this.data = data;
	}
	
	/**
	 * @return the data
	 */
	public final String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public final void setData(String data) {
		this.data = data;
	}
}
