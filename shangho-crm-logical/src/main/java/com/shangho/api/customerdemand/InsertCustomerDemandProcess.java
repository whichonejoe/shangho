package com.shangho.api.customerdemand;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.customerdemand.request.InsertCustomerDemandRequest;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.CustomerDemandManager;
import com.shangho.dao.crm.manager.ObjectManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class InsertCustomerDemandProcess extends AbstractAPIProcess {
	private InsertCustomerDemandRequest entity;
	private final int step = 1;

	public InsertCustomerDemandProcess(InsertCustomerDemandRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		final int ID = CustomerDemandManager.getInstance().insert(entity.getObjectcategoryid(), entity.getStatus(),
				entity.getName(), entity.getBudgetmax(), entity.getBudgetminimum(), entity.getSqmax(),
				entity.getSqmax(), entity.getHouseagemax(), entity.getHouseageminimum(),
				entity.getHousepatternitemids(), entity.getLocationitemids(), entity.getSpecialdemanditemids(),
				entity.getDesignatepathids());
		LogAction.getInstance().debug("step 1/" + step + ":insert success.");
		return ID;
	}

	@Override
	protected void check() throws SHException, Exception {
		if (entity == null) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(token).");
		}
		if (StringUtils.isBlank(entity.getName())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(name).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(status).");
		}
		if (entity.getObjectcategoryid() <= 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(object category id).");
		}
		if (entity.getBudgetmax() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(budget max).");
		}
		if (entity.getBudgetminimum() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(budget minimum).");
		}
		if (entity.getSqmax() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(sq max).");
		}
		if (entity.getSqminimum() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(sq minimum).");
		}
		if (entity.getHouseagemax() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(house age max).");
		}
		if (entity.getHouseageminimum() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(house age minimum).");
		}
		if (entity.getLocationitemids() == null || entity.getLocationitemids().isEmpty()) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(location item ids).");
		}
		if (entity.getDesignatepathids() == null) {
			entity.setDesignatepathids(new ArrayList<Integer>());
		}
		if (entity.getHousepatternitemids() == null) {
			entity.setHousepatternitemids(new ArrayList<Integer>());
		}
		if (entity.getSpecialdemanditemids() == null) {
			entity.setSpecialdemanditemids(new ArrayList<Integer>());
		}
		boolean isPass = false;
		for (final StatusMean statusEntity : StatusMean.values()) {
			if (entity.getStatus().equals(statusEntity.getValue())) {
				isPass = true;
				break;
			}
		}
		if (!isPass) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not find status).");
		}
		if (entity.getName().length() > 50) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(name length 50).");
		}
		if (!ObjectManager.getInstance().isCategoryExisted(entity.getObjectcategoryid())) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not found object category id).");
		}
	}

}
