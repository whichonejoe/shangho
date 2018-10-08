package com.shangho.blackcore.servlet.location;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.location.ListLocationCategoryProcess;
import com.shangho.blackcore.api.location.request.ListLocationCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/location/category/list")
public class ListLoactionCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 2924781217094494138L;
	private final static Logger logger = LoggerFactory.getLogger(ListLoactionCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListLocationCategoryRequest entity = (ListLocationCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, ListLocationCategoryRequest.class);
		return new ListLocationCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
