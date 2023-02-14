package com.examplesweb.service;

import java.util.List;

import com.examplesweb.dao.LottoDao;
import com.examplesweb.dto.LottoDto;

public class LottoService {
	
	// 로또 번호를 받아서 필요한 처리를 수행 (Dao에 전달)
	public void registerLotto(LottoDto lottoDto) {
		LottoDao lottoDao = new LottoDao();
		lottoDao.insertLotto(lottoDto);
	}
	
	// Dao의 메서드를 호출해서 조회한 데이터를 반환
	public List<LottoDto> findAllLotto() {
		LottoDao lottoDao = new LottoDao();
		List<LottoDto> lottos = lottoDao.selectAllLotto();
		return lottos;
	}

}
