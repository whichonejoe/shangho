package com.shangho.blackcore.servlet.customerdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.customerdemand.InsertCustomerDemandProcess;
import com.shangho.blackcore.api.customerdemand.request.InsertCustomerDemandRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/customerdemand/insert")
public class InsertCustomerDemandServlet extends APIServlet {
	private static final long serialVersionUID = -2316228808052381590L;
	private final static Logger logger = LoggerFactory.getLogger(InsertCustomerDemandServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertCustomerDemandRequest entity = (InsertCustomerDemandRequest) APIParser.getInstance()
				.parse(apiRequest, InsertCustomerDemandRequest.class);
		return new InsertCustomerDemandProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
