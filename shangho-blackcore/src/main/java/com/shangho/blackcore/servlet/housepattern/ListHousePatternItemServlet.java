package com.shangho.blackcore.servlet.housepattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.housepattern.ListHousePatternItemProcess;
import com.shangho.blackcore.api.housepattern.request.ListHousePatternItemRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/housepattern/item/list")
public class ListHousePatternItemServlet extends APIServlet {
	private static final long serialVersionUID = -2129038512970748978L;
	private final static Logger logger = LoggerFactory.getLogger(ListHousePatternItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListHousePatternItemRequest entity = (ListHousePatternItemRequest) APIParser.getInstance()
				.parse(apiRequest, ListHousePatternItemRequest.class);
		return new ListHousePatternItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
