package com.shangho.common.abs;

import com.shangho.utils.exception.SHException;

public abstract class AbstractAPIProcess {

	public AbstractAPIProcess() {
		super();
	}

	public final Object execute() throws SHException, Exception {

		init();

		check();

		Object obj = process();

		return obj;

	}

	protected abstract void init() throws SHException, Exception;

	protected abstract Object process() throws SHException, Exception;

	protected abstract void check() throws SHException, Exception;

}