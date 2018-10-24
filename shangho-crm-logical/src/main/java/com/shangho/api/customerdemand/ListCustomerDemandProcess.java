package com.shangho.api.customerdemand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.location.request.ListLocationCategoryRequest;
import com.shangho.blackcore.api.location.response.ListLocationCategoryResponse;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.LocationManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class ListCustomerDemandProcess extends AbstractAPIProcess {
	private ListLocationCategoryRequest entity;
	private final int step = 1;

	public ListCustomerDemandProcess(ListLocationCategoryRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		final List<ListLocationCategoryResponse> list = LocationManager.getInstance().listCategory(entity.getStatus(),
				entity.getNames());
		LogAction.getInstance().debug("step 1/" + step + ":category list success.");
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
		if (entity.getNames() == null) {
			entity.setNames(new ArrayList<String>());
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
