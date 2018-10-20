package com.shangho.blackcore.servlet.housepattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.housepattern.InsertHousePatternItemProcess;
import com.shangho.blackcore.api.housepattern.request.InsertHousePatternItemRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/housepattern/item/insert")
public class InsertHousePatternItemServlet extends APIServlet {
	private static final long serialVersionUID = -3728050249228093339L;
	private final static Logger logger = LoggerFactory.getLogger(InsertHousePatternItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertHousePatternItemRequest entity = (InsertHousePatternItemRequest) APIParser.getInstance()
				.parse(apiRequest, InsertHousePatternItemRequest.class);
		return new InsertHousePatternItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
