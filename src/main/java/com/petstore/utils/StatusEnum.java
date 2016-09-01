package com.petstore.utils;

/**
 * Pet status Enum
 * 
 * @author dinesh.jaju
 *
 */
public enum StatusEnum 
{
	available("available"),

	sold("sold"),

	pending("pending");

	private String value;

	StatusEnum(String value) 
	{
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
