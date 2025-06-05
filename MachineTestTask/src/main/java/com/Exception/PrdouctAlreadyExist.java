package com.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrdouctAlreadyExist extends RuntimeException {

	public PrdouctAlreadyExist(String msg) {
		super(msg);
		log.debug("PrdouctAlreadyExist occures :: ");

	}
}
