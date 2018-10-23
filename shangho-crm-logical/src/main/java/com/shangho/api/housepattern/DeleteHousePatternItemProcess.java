package com.shangho.api.housepattern;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.housepattern.request.DeleteHousePatternItemRequest;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.HousePatternManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class DeleteHousePatternItemProcess extends AbstractAPIProcess {
	private DeleteHousePatternItemRequest entity;
	private final int step = 1;

	public DeleteHousePatternItemProcess(DeleteHousePatternItemRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		HousePatternManager.getInstance().deleteItem(entity.getId());
		LogAction.getInstance().debug("step 1/" + step + ":item delete success.");
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
		if (entity.getId() <= 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(id).");
		}
	}
}
