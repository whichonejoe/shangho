package com.shangho.blackcore.servlet.customerdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.customerdemand.UpdateCustomerDemandProcess;
import com.shangho.blackcore.api.customerdemand.request.UpdateCustomerDemandRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/customerdemand/update")
public class UpdateCustomerDemandServlet extends APIServlet {
	private static final long serialVersionUID = 2157836619461853609L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateCustomerDemandServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateCustomerDemandRequest entity = (UpdateCustomerDemandRequest) APIParser.getInstance()
				.parse(apiRequest, UpdateCustomerDemandRequest.class);
		return new UpdateCustomerDemandProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
