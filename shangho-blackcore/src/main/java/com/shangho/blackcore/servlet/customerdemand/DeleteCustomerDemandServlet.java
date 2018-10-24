package com.shangho.blackcore.servlet.customerdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.customerdemand.DeleteCustomerDemandProcess;
import com.shangho.blackcore.api.customerdemand.request.DeleteCustomerDemandRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/customerdemand/delete")
public class DeleteCustomerDemandServlet extends APIServlet {
	private static final long serialVersionUID = 1288908961207720171L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteCustomerDemandServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteCustomerDemandRequest entity = (DeleteCustomerDemandRequest) APIParser.getInstance()
				.parse(apiRequest, DeleteCustomerDemandRequest.class);
		return new DeleteCustomerDemandProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
