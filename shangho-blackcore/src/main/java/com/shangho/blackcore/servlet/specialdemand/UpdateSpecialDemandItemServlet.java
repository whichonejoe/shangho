package com.shangho.blackcore.servlet.specialdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.specialdemand.UpdateSpecialDemandItemProcess;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.blackcore.api.specialdemand.request.UpdateSpecialDemandItemRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/specialdemand/item/update")
public class UpdateSpecialDemandItemServlet extends APIServlet {
	private static final long serialVersionUID = -6242053539602716266L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateSpecialDemandItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateSpecialDemandItemRequest entity = (UpdateSpecialDemandItemRequest) APIParser.getInstance()
				.parse(apiRequest, UpdateSpecialDemandItemRequest.class);
		return new UpdateSpecialDemandItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
