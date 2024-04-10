package com.wrathcodes.restaurant.converter;

import javax.persistence.AttributeConverter;

public class StringToIntConverter implements AttributeConverter<String, Integer> {

	@Override
	public Integer convertToDatabaseColumn(String attribute) {
		try {
			return Integer.parseInt(attribute);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) {
		try {
			return String.valueOf(dbData);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
