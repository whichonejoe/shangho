package com.shangho.blackcore.servlet.housepattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.customerdemand.UpdateHousePatternCategoryProcess;
import com.shangho.blackcore.api.housepattern.request.UpdateHousePatternCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/housepattern/category/update")
public class UpdateHousePatternCategoryServlet extends APIServlet {
	private static final long serialVersionUID = -2510049004259513428L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateHousePatternCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateHousePatternCategoryRequest entity = (UpdateHousePatternCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, UpdateHousePatternCategoryRequest.class);
		return new UpdateHousePatternCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
