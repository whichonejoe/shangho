package com.shangho.api.specialdemand;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.specialdemand.request.DeleteSpecialDemandCategoryRequest;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.SpecialDemandManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class DeleteSpecialDemandCategoryProcess extends AbstractAPIProcess {
	private DeleteSpecialDemandCategoryRequest entity;
	private final int step = 1;

	public DeleteSpecialDemandCategoryProcess(DeleteSpecialDemandCategoryRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		SpecialDemandManager.getInstance().deleteCategory(entity.getId());
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
		if (entity.getId() <= 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(id).");
		}
	}

}
