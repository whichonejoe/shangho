package com.shangho.api.specialdemand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.specialdemand.request.ListSpecialDemandItemRequest;
import com.shangho.blackcore.api.specialdemand.response.ListSpecialDemandItemResponse;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.SpecialDemandManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class ListSpecialDemandItemProcess extends AbstractAPIProcess {
	private ListSpecialDemandItemRequest entity;
	private final int step = 1;

	public ListSpecialDemandItemProcess(ListSpecialDemandItemRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		final List<ListSpecialDemandItemResponse> list = SpecialDemandManager.getInstance().listItem(entity.getCategories(),
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
	}

}
