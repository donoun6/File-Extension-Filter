package com.filename.extension.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filename.extension.main.dao.MainDao;
import com.filename.extension.main.domain.MainDomain;

@Service
public class MainServiceImpl implements MainService{
	@Autowired
	MainDao mainDao;

	@Override
	public List<MainDomain> getExtensionByDefaultCheck(boolean check) {
		return mainDao.getExtensionByDefaultCheck(check);
	}

	@Override
	public void updateCheckBoxByextension(boolean check,String extension) {
		mainDao.updateCheckBoxByextension(check,extension);
	}

	@Override
	public void addExtension(String extension) {
		mainDao.addExtension(extension);
	}

	@Override
	public void deleteExtension(String extension) {
		mainDao.deleteExtension(extension);
	}

	@Override
	public int getCountBydefaultCheckFalse() {
		return mainDao.getCountBydefaultCheckFalse();
	}

	@Override
	public boolean extensionDuplicatedCheck(String extension) {
		return mainDao.extensionDuplicatedCheck(extension);
	}

}
