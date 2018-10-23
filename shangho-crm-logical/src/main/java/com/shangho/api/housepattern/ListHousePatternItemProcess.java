package com.shangho.api.housepattern;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.housepattern.request.ListHousePatternItemRequest;
import com.shangho.blackcore.api.housepattern.response.ListHousePatternItemResponse;
import com.shangho.commom.SQLSatementIntf;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.HousePatternManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class ListHousePatternItemProcess extends AbstractAPIProcess {
	private ListHousePatternItemRequest entity;
	private final int step = 1;

	public ListHousePatternItemProcess(ListHousePatternItemRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		final List<ListHousePatternItemResponse> list = HousePatternManager.getInstance().listItem(entity.getStatus(),
				entity.getCategories(), entity.getNames(), entity.getSortorderby());
		LogAction.getInstance().debug("step 1/" + step + ":item list success.");

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

		if (!StringUtils.isBlank(entity.getSortorderby())) {
			boolean isPass = false;
			if (entity.getSortorderby().equals(SQLSatementIntf.ASC))
				isPass = true;
			else if (entity.getSortorderby().equals(SQLSatementIntf.DESC))
				isPass = true;
			if (!isPass)
				throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not found sort order by).");
		}
	}
}
