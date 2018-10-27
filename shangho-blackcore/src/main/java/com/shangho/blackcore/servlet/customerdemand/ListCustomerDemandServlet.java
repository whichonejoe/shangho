package com.shangho.blackcore.servlet.customerdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.customerdemand.ListCustomerDemandProcess;
import com.shangho.blackcore.api.customerdemand.bean.ListCustomerDemandRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/customerdemand/list")
public class ListCustomerDemandServlet extends APIServlet {
	private static final long serialVersionUID = 1557898843715617602L;
	private final static Logger logger = LoggerFactory.getLogger(ListCustomerDemandServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListCustomerDemandRequest entity = (ListCustomerDemandRequest) APIParser.getInstance().parse(apiRequest,
				ListCustomerDemandRequest.class);
		return new ListCustomerDemandProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}
}
