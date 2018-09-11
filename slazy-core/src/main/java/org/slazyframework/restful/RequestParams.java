package org.slazyframework.restful;

import java.io.Serializable;

public class RequestParams implements Serializable {

	protected String token;
	protected String restCode;
	protected String data;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRestCode() {
		return restCode;
	}

	public void setRestCode(String restCode) {
		this.restCode = restCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestParams [token=");
		builder.append(token);
		builder.append(", restCode=");
		builder.append(restCode);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((restCode == null) ? 0 : restCode.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestParams other = (RequestParams) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (restCode == null) {
			if (other.restCode != null)
				return false;
		} else if (!restCode.equals(other.restCode))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

}
