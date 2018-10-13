package com.shangho.blackcore.servlet.specialdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.specialdemand.ListSpecialDemandCategoryProcess;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.blackcore.api.specialdemand.request.ListSpecialDemandCategoryRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/specialdemand/category/list")
public class ListSpecialDemandCategoryServlet extends APIServlet {
	private static final long serialVersionUID = -5054480644593808344L;
	private final static Logger logger = LoggerFactory.getLogger(ListSpecialDemandCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListSpecialDemandCategoryRequest entity = (ListSpecialDemandCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, ListSpecialDemandCategoryRequest.class);
		return new ListSpecialDemandCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
