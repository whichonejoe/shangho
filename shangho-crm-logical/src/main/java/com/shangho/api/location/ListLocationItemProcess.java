package com.shangho.api.location;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.location.request.ListLocationItemRequest;
import com.shangho.blackcore.api.location.response.ListLocationItemResponse;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.LocationManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class ListLocationItemProcess extends AbstractAPIProcess {
	private ListLocationItemRequest entity;
	private final int step = 1;

	public ListLocationItemProcess(ListLocationItemRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		final List<ListLocationItemResponse> list = LocationManager.getInstance().listItem(entity.getCategories(),
				entity.getRefers(), entity.getStatus(), entity.getNames());
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
		if (entity.getCategories() == null) {
			entity.setCategories(new ArrayList<Integer>());
		}
		if (entity.getRefers() == null) {
			entity.setRefers(new ArrayList<Integer>());
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
