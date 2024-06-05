package com.Icaros.models;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlagUserTypeEnum {
	
	USER_MUSICIAN(1, "ROLE_musico"),
	USER_LOVER(2, "ROLE_AM"),
	USER_PRODUCER(3, "ROLE_produtor");
	
	private Integer code;
	private String description;
	
	public static FlagUserTypeEnum toEnum(Integer code) {
		if(Objects.isNull(code)) {
			return null;
		}
		for(FlagUserTypeEnum x : FlagUserTypeEnum.values() ) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid code:"+ code);
	}
	

}
