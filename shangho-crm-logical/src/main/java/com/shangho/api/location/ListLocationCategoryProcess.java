package com.shangho.api.location;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.location.request.ListLocationCategoryRequest;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.LocationManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class ListLocationCategoryProcess extends AbstractAPIProcess {
	private ListLocationCategoryRequest entity;
	private final int step = 1;

	public ListLocationCategoryProcess(ListLocationCategoryRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		LocationManager.getInstance().list(entity.getStatus(), entity.getNames());
		LogAction.getInstance().debug("step 1/" + step + ":category delete success.");
		return null;
	}

	@Override
	protected void check() throws SHException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(token).");
		}
	}

}
