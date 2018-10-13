package com.shangho.blackcore.servlet.specialdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.specialdemand.DeleteSpecialDemandItemProcess;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.blackcore.api.specialdemand.request.DeleteSpecialDemandItemRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/specialdemand/item/delete")
public class DeleteSpecialDemandItemServlet extends APIServlet {
	private static final long serialVersionUID = 1069834882098404413L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteSpecialDemandItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteSpecialDemandItemRequest entity = (DeleteSpecialDemandItemRequest) APIParser.getInstance().parse(apiRequest,
				DeleteSpecialDemandItemRequest.class);
		return new DeleteSpecialDemandItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
