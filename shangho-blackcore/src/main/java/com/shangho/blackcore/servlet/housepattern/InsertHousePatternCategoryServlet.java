package com.shangho.blackcore.servlet.housepattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.customerdemand.InsertHousePatternCategoryProcess;
import com.shangho.blackcore.api.housepattern.request.InsertHousePatternCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/housepattern/category/insert")
public class InsertHousePatternCategoryServlet extends APIServlet {
	private static final long serialVersionUID = -4246183562508108514L;
	private final static Logger logger = LoggerFactory.getLogger(InsertHousePatternCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertHousePatternCategoryRequest entity = (InsertHousePatternCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, InsertHousePatternCategoryRequest.class);
		return new InsertHousePatternCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
