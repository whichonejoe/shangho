package com.shangho.api.customerdemand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.customerdemand.bean.ListCustomerDemandRequest;
import com.shangho.blackcore.api.customerdemand.response.ListCustomerDemandResponse;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.CustomerDemandManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class ListCustomerDemandProcess extends AbstractAPIProcess {
	private ListCustomerDemandRequest entity;
	private final int step = 1;

	public ListCustomerDemandProcess(ListCustomerDemandRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		final List<ListCustomerDemandResponse> list = CustomerDemandManager.getInstance().list(entity.getStatus(),
				entity.getBudgetmax(), entity.getBudgetminimum(), entity.getSqmax(), entity.getSqminimum(),
				entity.getHouseagemax(), entity.getHouseageminimum(), entity.getCategories(), entity.getNames(),
				entity.getHousepatternitemids(), entity.getLocationitemids(), entity.getSpecialdemanditemids(),
				entity.getDesignateids());
		LogAction.getInstance().debug("step 1/" + step + ":list success.");
		return list;
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
		if (entity.getBudgetmax() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(budget max < 0).");
		}
		if (entity.getBudgetminimum() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(budget minimum < 0).");
		}
		if (entity.getSqmax() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(sq max < 0).");
		}
		if (entity.getSqminimum() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(sq minimum < 0).");
		}
		if (entity.getHouseagemax() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(house age max < 0).");
		}
		if (entity.getHouseageminimum() < 0) {
			throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(house age minimum < 0).");
		}
		if (entity.getNames() == null) {
			entity.setNames(new ArrayList<String>());
		}
		if (entity.getCategories() == null) {
			entity.setCategories(new ArrayList<Integer>());
		}
		if (entity.getHousepatternitemids() == null) {
			entity.setHousepatternitemids(new ArrayList<Integer>());
		}
		if (entity.getLocationitemids() == null) {
			entity.setLocationitemids(new ArrayList<Integer>());
		}
		if (entity.getSpecialdemanditemids() == null) {
			entity.setSpecialdemanditemids(new ArrayList<Integer>());
		}
		if (entity.getDesignateids() == null) {
			entity.setDesignateids(new ArrayList<Integer>());
		}
		if (!StringUtils.isBlank(entity.getStatus())) {
			boolean isPass = false;
			for (final StatusMean statusEntity : StatusMean.values())
				if (entity.getStatus().equals(statusEntity.getValue())) {
					isPass = true;
					break;
				}
			if (!isPass)
				throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not found status).");
		}
	}

}
