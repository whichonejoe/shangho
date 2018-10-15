package com.shangho.api.object;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.object.request.InsertObjectCategoryRequest;
import com.shangho.commom.ObjectTypeMean;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.ObjectManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class InsertObjectCategoryProcess extends AbstractAPIProcess {
	private InsertObjectCategoryRequest entity;
	private final int step = 1;

	public InsertObjectCategoryProcess(InsertObjectCategoryRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		final int ID = ObjectManager.getInstance().insertCategory(entity.getStatus(), entity.getType(),
				entity.getSort(), entity.getName(), entity.getDescription());
		LogAction.getInstance().debug("step 1/" + step + ":category insert success.");
		return ID;
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
		if (StringUtils.isBlank(entity.getType())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(type).");
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
		isPass = false;
		for (final ObjectTypeMean typeEntity : ObjectTypeMean.values()) {
			if (entity.getType().equals(typeEntity.getValue())) {
				isPass = true;
				break;
			}
		}
		if (!isPass) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not found type).");
		}
		if (entity.getName().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(name length 50).");
		}
		if (entity.getDescription() != null && entity.getDescription().length() > 500) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(description length 500).");
		}
	}

}