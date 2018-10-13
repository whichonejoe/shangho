package com.shangho.blackcore.servlet.location;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.location.ListLocationItemProcess;
import com.shangho.blackcore.api.location.request.ListLocationItemRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/location/item/list")
public class ListLoactionItemServlet extends APIServlet {
	private static final long serialVersionUID = 2924781217094494138L;
	private final static Logger logger = LoggerFactory.getLogger(ListLoactionItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListLocationItemRequest entity = (ListLocationItemRequest) APIParser.getInstance().parse(apiRequest,
				ListLocationItemRequest.class);
		return new ListLocationItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
