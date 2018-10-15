package com.shangho.api.object;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shangho.blackcore.api.object.request.ListObjectCategoryRequest;
import com.shangho.blackcore.api.object.response.ListObjectCategoryResponse;
import com.shangho.commom.ObjectTypeMean;
import com.shangho.commom.SQLSatementIntf;
import com.shangho.commom.StatusMean;
import com.shangho.common.abs.AbstractAPIProcess;
import com.shangho.dao.crm.manager.ObjectManager;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;
import com.shangho.utils.status.APIStatus;

public class ListObjectCategoryProcess extends AbstractAPIProcess {
	private ListObjectCategoryRequest entity;
	private final int step = 1;

	public ListObjectCategoryProcess(ListObjectCategoryRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SHException, Exception {
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SHException, Exception {
		final List<ListObjectCategoryResponse> list = ObjectManager.getInstance().listCategory(entity.getStatus(),
				entity.getType(), entity.getNames(), entity.getSortorderby());
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
		if (!StringUtils.isBlank(entity.getType())) {
			boolean isPass = false;
			for (final ObjectTypeMean typeEntity : ObjectTypeMean.values())
				if (entity.getType().equals(typeEntity.getValue())) {
					isPass = true;
					break;
				}
			if (!isPass)
				throw new SHException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal(not found type).");
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
