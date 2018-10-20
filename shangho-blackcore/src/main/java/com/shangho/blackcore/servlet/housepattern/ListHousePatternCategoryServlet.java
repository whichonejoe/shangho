package com.shangho.blackcore.servlet.housepattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.housepattern.ListHousePatternCategoryProcess;
import com.shangho.blackcore.api.housepattern.request.ListHousePatternCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/housepattern/category/list")
public class ListHousePatternCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 6745437030836413781L;
	private final static Logger logger = LoggerFactory.getLogger(ListHousePatternCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListHousePatternCategoryRequest entity = (ListHousePatternCategoryRequest) APIParser.getInstance().parse(apiRequest,
				ListHousePatternCategoryRequest.class);
		return new ListHousePatternCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
