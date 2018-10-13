package com.shangho.blackcore.servlet.specialdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.specialdemand.InsertSpecialDemandItemProcess;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.blackcore.api.specialdemand.request.InsertSpecialDemandItemRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/specialdemand/item/insert")
public class InsertSpecialDemandItemServlet extends APIServlet {
	private static final long serialVersionUID = -8262625411779508456L;
	private final static Logger logger = LoggerFactory.getLogger(InsertSpecialDemandItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertSpecialDemandItemRequest entity = (InsertSpecialDemandItemRequest) APIParser.getInstance().parse(apiRequest,
				InsertSpecialDemandItemRequest.class);
		return new InsertSpecialDemandItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}
}
