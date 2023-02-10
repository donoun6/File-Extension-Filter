package com.filename.extension.main.service;

import java.util.List;

import com.filename.extension.main.domain.MainDomain;

public interface MainService {
	List<MainDomain> getExtensionByDefaultCheck(boolean check);
	void updateCheckBoxByextension(boolean check, String extension);
	void addExtension(String extension);
	void deleteExtension(String extension);
	int getCountBydefaultCheckFalse();
}
