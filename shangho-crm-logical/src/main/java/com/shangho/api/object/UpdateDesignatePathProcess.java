package com.shangho.api.object;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.designatepath.request.UpdateDesignatePathRequest;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.DesignatePathManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class UpdateDesignatePathProcess extends AbstractAPIProcess {
	private UpdateDesignatePathRequest entity;
	private final int step = 1;

	public UpdateDesignatePathProcess(UpdateDesignatePathRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		DesignatePathManager.getInstance().update(entity.getId(), entity.getStatus(), entity.getCountry(),
				entity.getProvince(), entity.getCity(), entity.getTownship(), entity.getVillage(), entity.getStreet(),
				entity.getName());
		LogAction.getInstance().debug("step 1/" + step + ":update success.");
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
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(status).");
		}
		int nullCount = 0;
		if (!StringUtils.isBlank(entity.getName())) {
			++nullCount;
		}
		if (!StringUtils.isBlank(entity.getCity())) {
			++nullCount;
		}
		if (!StringUtils.isBlank(entity.getCountry())) {
			++nullCount;
		}
		if (!StringUtils.isBlank(entity.getProvince())) {
			++nullCount;
		}
		if (!StringUtils.isBlank(entity.getStreet())) {
			++nullCount;
		}
		if (!StringUtils.isBlank(entity.getTownship())) {
			++nullCount;
		}
		if (!StringUtils.isBlank(entity.getVillage())) {
			++nullCount;
		}
		if (nullCount <= 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(inputs cannot be all empty).");
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
		if (!StringUtils.isBlank(entity.getName()) && entity.getName().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(name length 50).");
		}
		if (!StringUtils.isBlank(entity.getCity()) && entity.getCity().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(city length 50).");
		}
		if (!StringUtils.isBlank(entity.getCountry()) && entity.getCountry().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(country length 50).");
		}
		if (!StringUtils.isBlank(entity.getProvince()) && entity.getProvince().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(province length 50).");
		}
		if (!StringUtils.isBlank(entity.getStreet()) && entity.getStreet().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(street length 50).");
		}
		if (!StringUtils.isBlank(entity.getTownship()) && entity.getTownship().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(township length 50).");
		}
		if (!StringUtils.isBlank(entity.getVillage()) && entity.getVillage().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(village length 50).");
		}
		if (!DesignatePathManager.getInstance().isExisted(entity.getId())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(nof found id).");
		}
	}

}
