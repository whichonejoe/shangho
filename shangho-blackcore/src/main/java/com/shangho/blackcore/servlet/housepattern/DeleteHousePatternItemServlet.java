package com.shangho.blackcore.servlet.housepattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.housepattern.DeleteHousePatternItemProcess;
import com.shangho.blackcore.api.housepattern.request.DeleteHousePatternItemRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/housepattern/item/delete")
public class DeleteHousePatternItemServlet extends APIServlet {
	private static final long serialVersionUID = 8958706413791884789L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteHousePatternItemServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteHousePatternItemRequest entity = (DeleteHousePatternItemRequest) APIParser.getInstance()
				.parse(apiRequest, DeleteHousePatternItemRequest.class);
		return new DeleteHousePatternItemProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
