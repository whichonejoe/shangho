package com.shangho.blackcore.servlet.specialdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.specialdemand.InsertSpecialDemandCategoryProcess;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.blackcore.api.specialdemand.request.InsertSpecialDemandCategoryRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/specialdemand/category/insert")
public class InsertSpecialDemandCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 6211348281257732597L;
	private final static Logger logger = LoggerFactory.getLogger(InsertSpecialDemandCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertSpecialDemandCategoryRequest entity = (InsertSpecialDemandCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, InsertSpecialDemandCategoryRequest.class);
		return new InsertSpecialDemandCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
