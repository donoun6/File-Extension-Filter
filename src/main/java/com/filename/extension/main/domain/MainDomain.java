package com.filename.extension.main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MainDomain {
	private String extension;
	private boolean defaultCheck;
	private boolean checkBox;
	public MainDomain() {
	}
}
