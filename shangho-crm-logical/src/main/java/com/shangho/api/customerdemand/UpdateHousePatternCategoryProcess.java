package com.shangho.api.customerdemand;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.housepattern.request.UpdateHousePatternCategoryRequest;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.HousePatternManager;
import com.shangho.dao.crm.manager.ObjectManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class UpdateHousePatternCategoryProcess extends AbstractAPIProcess {
	private UpdateHousePatternCategoryRequest entity;
	private final int step = 1;

	public UpdateHousePatternCategoryProcess(UpdateHousePatternCategoryRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		HousePatternManager.getInstance().updateCategory(entity.getId(), entity.getStatus(), entity.getSort(),
				entity.getName());
		LogAction.getInstance().debug("step 1/" + step + ":category update success.");
		return null;
	}

	@Override
	protected void check() throws SHException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal.");
		}
		if (entity.getId() <= 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(id).");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(token).");
		}

		if (entity.getSort() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(sort < 0).");
		}
		if (StringUtils.isBlank(entity.getName())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(name).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(status).");
		}
		boolean isPass = false;
		for (final StatusMean statusEntity : StatusMean.values()) {
			if (entity.getStatus().equals(statusEntity.getValue())) {
				isPass = true;
				break;
			}
		}
		if (!isPass) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not found status).");
		}

		if (entity.getName().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(name length 50).");
		}

		if (!ObjectManager.getInstance().isCategoryExisted(entity.getId())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(nof found id).");
		}
	}

}
