package com.shangho.blackcore.servlet.specialdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.specialdemand.UpdateSpecialDemandCategoryProcess;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.blackcore.api.specialdemand.request.UpdateSpecialDemandCategoryRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/specialdemand/category/update")
public class UpdateSpecialDemandCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 4228644567937963303L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateSpecialDemandCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateSpecialDemandCategoryRequest entity = (UpdateSpecialDemandCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, UpdateSpecialDemandCategoryRequest.class);
		return new UpdateSpecialDemandCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
