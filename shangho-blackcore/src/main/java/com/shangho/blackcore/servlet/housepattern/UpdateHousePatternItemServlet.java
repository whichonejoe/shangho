package com.shangho.blackcore.servlet.housepattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.housepattern.UpdateHousePatternItemProcess;
import com.shangho.blackcore.api.housepattern.request.UpdateHousePatternItemRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/housepattern/item/update")
public class UpdateHousePatternItemServlet extends APIServlet {
	private static final long serialVersionUID = -3436107410977345924L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateHousePatternItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateHousePatternItemRequest entity = (UpdateHousePatternItemRequest) APIParser.getInstance()
				.parse(apiRequest, UpdateHousePatternItemRequest.class);
		return new UpdateHousePatternItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
