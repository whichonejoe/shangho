package com.shangho.blackcore.servlet.specialdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.specialdemand.ListSpecialDemandItemProcess;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.blackcore.api.specialdemand.request.ListSpecialDemandItemRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/specialdemand/item/list")
public class ListSpecialDemandItemServlet extends APIServlet {
	private static final long serialVersionUID = 6007742439886327835L;
	private final static Logger logger = LoggerFactory.getLogger(ListSpecialDemandItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListSpecialDemandItemRequest entity = (ListSpecialDemandItemRequest) APIParser.getInstance().parse(apiRequest,
				ListSpecialDemandItemRequest.class);
		return new ListSpecialDemandItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
